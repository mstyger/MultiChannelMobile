package ch.zh.dipima.multichannelmobile.message;

import ch.zh.dipima.multichannelmobile.MainActivity;
import android.util.Log;

public class Print extends Message implements Validatable {
	public Print() {
		setMsgType(MainActivity.MESSAGE_TYPE_PRINT);
	}

	@Override
	public void drawMessageFields() {
		Log.d("printr", "Hier kommen dann die print Eingabefelder");
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
}
