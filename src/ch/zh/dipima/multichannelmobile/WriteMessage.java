package ch.zh.dipima.multichannelmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import ch.zh.dipima.multichannelmobile.message.Message;

public class WriteMessage extends Activity {
	private Message msg;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        final Bundle extras = getIntent().getExtras();
		
		if(extras != null) {
			int msgType = extras.getInt(MainActivity.MESSAGE_TYPE);
			msg = Message.factory(msgType);
			
			if(msg != null) {
				msg.drawMessageGUI(this);
			}
		} else {
			Toast.makeText(getApplicationContext(), "Bitte Nachrichtentyp wählen.", Toast.LENGTH_LONG).show();
		}
    }
	
	public void sendMessage(View v) {
		msg.sendMessage(v);
	}

	@Override
	public void onBackPressed() {
		Intent ihome = new Intent().setClass(getApplicationContext(),
				MainActivity.class).setFlags(
				Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(ihome);
		return;
	}
}
