package ch.zh.dipima.multichannelmobile.message;

import ch.zh.dipima.multichannelmobile.MainActivity;
import android.util.Log;

public class Email extends Message implements Validatable {
	public Email() {
		setMsgType(MainActivity.MESSAGE_TYPE_EMAIL);
	}

	@Override
	public void drawMessageFields() {
		Log.d("printr", "Hier kommen dann die Email Eingabefelder");
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
}
