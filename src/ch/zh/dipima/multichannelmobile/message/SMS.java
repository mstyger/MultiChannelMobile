package ch.zh.dipima.multichannelmobile.message;

import ch.zh.dipima.multichannelmobile.MainActivity;

public class SMS extends Message implements Validatable {
	public SMS() {
		setMsgType(MainActivity.MESSAGE_TYPE_SMS);
	}

	@Override
	public void drawMessageFields() {
		
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
}
