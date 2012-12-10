package ch.zh.dipima.multichannelmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import ch.zh.dipima.multichannelmobile.message.Message;

public class WriteMessage extends Activity {
	private static final int PICKFILE_RESULT_CODE = 1;
	private Message msg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// get params from previous activity
		final Bundle extras = getIntent().getExtras();

		if (extras != null) {
			int msgType = extras.getInt(MainActivity.MESSAGE_TYPE);

			// create message object
			msg = Message.factory(msgType, this);

			if (msg != null) {
				msg.drawMessageGUI();
			}
		} else {
			Toast.makeText(getApplicationContext(),
					"Bitte Nachrichtentyp w�hlen.", Toast.LENGTH_LONG).show();
		}
	}

	public void sendMessage(View v) {
		msg.sendMessage();
	}

	public void findAttachment(View v) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("file/*");
		startActivityForResult(intent, PICKFILE_RESULT_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case PICKFILE_RESULT_CODE:
			if (resultCode == RESULT_OK) {
				String filePath = data.getData().getPath();
				msg.addAttachment(filePath);
			}
			break;
		}
	}

	@Override
	public void onBackPressed() {
		Intent ihome = new Intent().setClass(getApplicationContext(),
				MainActivity.class).setFlags(
				Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(ihome);
		return;
	}
}
