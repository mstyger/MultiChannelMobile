package ch.zh.dipima.multichannelmobile.message;

import ch.zh.dipima.multichannelmobile.MainActivity;

public abstract class Message {
	private int msgType;
	
	public static Message factory(int msgType) {
		Message msg = null;
		switch(msgType) {
		case MainActivity.MESSAGE_TYPE_EMAIL:
			msg = new Email();
			break;
		case MainActivity.MESSAGE_TYPE_SMS:
			msg = new SMS();
			break;
		case MainActivity.MESSAGE_TYPE_MMS:
			msg = new MMS();
			break;
		case MainActivity.MESSAGE_TYPE_PRINT:
			msg = new Print();
			break;
		}
		return msg;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	
	public abstract void drawMessageFields();
}
