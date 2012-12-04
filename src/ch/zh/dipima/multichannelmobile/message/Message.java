package ch.zh.dipima.multichannelmobile.message;

import android.app.Activity;
import android.view.View;
import ch.zh.dipima.multichannelmobile.MainActivity;

public abstract class Message {
	public static final int MESSAGE_STATE_INITIALIZED = 1;
	public static final int MESSAGE_STATE_SENT = 2;
	public static final int MESSAGE_STATE_NOTSENT = 3;
	
	private String errorMessage = "";
	
	protected int sendState;
	protected int msgType;
	private long msgTime;
	
	public Message() {
		setMsgTime(System.currentTimeMillis());
		setSentState(Message.MESSAGE_STATE_INITIALIZED);
	}
	
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
		default:
			System.err.println("Fehler beim Erstellen der Nachricht.");
			break;
		}
		return msg;
	}
	
	protected void writeLog() {
		//TOTO: write date, messagetype and message to logfile
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public int getSentState() {
		return sendState;
	}

	public void setSentState(int sendState) {
		this.sendState = sendState;
	}
	
	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	
	public long getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(long msgTime) {
		this.msgTime = msgTime;
	}
	
	// methods to be overriden
	public abstract void drawMessageGUI(Activity a);
	public abstract void sendMessage(View v);
}
