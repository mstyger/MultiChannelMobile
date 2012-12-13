/**
 * @author: Pius Fonseka, Dieter Good, Marcel Styger (BWI A-11)
 * @version 1.0
 * @description: Android App for HWZ Project, 2012
 */

package ch.zh.dipima.multichannelmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import ch.zh.dipima.multichannelmobile.message.Message;

public class WriteMessage extends Activity {
	public static final int PICKFILE_RESULT_CODE = 1;
	public static final int SEND_REQUEST_CODE = 2;
	
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
					"Bitte Nachrichtentyp wählen.", Toast.LENGTH_LONG).show();
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
		super.onActivityResult(requestCode, resultCode, data);
 
		switch (requestCode) {
		case PICKFILE_RESULT_CODE:
			if (resultCode == RESULT_OK) {
				String filePath = data.getData().getPath();
				msg.addAttachment(filePath);
			}
			break;
		case SEND_REQUEST_CODE:
			//leider returnt das system immer 0 als resultCode, egal, ob der benutzer abbricht oder das mail wirklich sendet
			Toast.makeText(this, "Nachricht erfolgreich an Android weitergereicht.", Toast.LENGTH_SHORT).show();
			Intent ihome = new Intent().setClass(getApplicationContext(),
					MainActivity.class).setFlags(
					Intent.FLAG_ACTIVITY_CLEAR_TOP
							| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(ihome);
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
