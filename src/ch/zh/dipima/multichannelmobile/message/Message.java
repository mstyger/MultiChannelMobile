package ch.zh.dipima.multichannelmobile.message;

import android.app.Activity;
import android.view.View;
import ch.zh.dipima.multichannelmobile.MainActivity;

public abstract class Message {
	public static final int MESSAGE_STATE_INITIALIZED = 1;
	public static final int MESSAGE_STATE_SENT = 2;
	public static final int MESSAGE_STATE_NOTSENT = 3;
	
	private String errorMessage = "";
	
	private int sendState;
	private int msgType;
	private long msgTime;
	
	public Message() {
		setMsgTime(System.currentTimeMillis());
		setSentState(Message.MESSAGE_STATE_INITIALIZED);
	}
	
	/**
	 * Message Factory to create object
	 */
	
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
	
	// Write log
	protected void writeLog() {
		//TOTO: write date, messagetype and message to logfile
	}
	
	/**
	 * Error Message 
	 */
	
	public String getErrorMessage() {
		return errorMessage;
	}

	protected void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * Message State
	 */
	
	public int getSentState() {
		return sendState;
	}

	protected void setSentState(int sendState) {
		this.sendState = sendState;
	}
	
	/**
	 * Message Type
	 */
	
	public int getMsgType() {
		return msgType;
	}

	protected void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	
	/**
	 * Message Time
	 */
	
	public long getMsgTime() {
		return msgTime;
	}

	protected void setMsgTime(long msgTime) {
		this.msgTime = msgTime;
	}
	
	
	/************* Abstract Methods **************/
	
	// methods to be overriden
	public abstract void drawMessageGUI(Activity a);
	public abstract void sendMessage(View v);
}
