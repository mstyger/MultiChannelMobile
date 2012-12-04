package ch.zh.dipima.multichannelmobile.message;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;
import ch.zh.dipima.multichannelmobile.MainActivity;
import ch.zh.dipima.multichannelmobile.R;

public class SMS extends Message implements Validatable {
	
	public SMS() {
		super();
		setMsgType(MainActivity.MESSAGE_TYPE_SMS);
	}

	@Override
	public void drawMessageGUI(Activity a) {
		a.setTitle(a.getResources().getString(R.string.title_activity_write_sms));
		a.setContentView(R.layout.activity_write_sms);
	}

	@Override
	public boolean validate() {
		//todo: message validieren
		setErrorMessage("Nachricht zu lang.");
		return true;
	}

	@Override
	public void sendMessage(View v) {
		if(validate()) {
			//todo send message
			setSentState(MESSAGE_STATE_SENT);
			Toast.makeText(v.getContext(), "SMS versendet ohne richtig zu prüfen.", Toast.LENGTH_LONG).show();
		} else {
			setSentState(MESSAGE_STATE_NOTSENT);
			// throw error because text not valid
		}
		writeLog();
	}
}
