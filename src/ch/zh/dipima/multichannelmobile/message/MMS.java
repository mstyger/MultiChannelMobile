package ch.zh.dipima.multichannelmobile.message;

import ch.zh.dipima.multichannelmobile.MainActivity;
import android.util.Log;

public class MMS extends Message implements Validatable {
	public MMS() {
		setMsgType(MainActivity.MESSAGE_TYPE_MMS);
	}

	@Override
	public void drawMessageFields() {
		Log.d("printr", "Hier kommen dann die MMS Eingabefelder");		
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
}
