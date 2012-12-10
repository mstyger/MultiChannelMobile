package ch.zh.dipima.multichannelmobile.message;

import android.app.Activity;
import android.widget.TextView;
import ch.zh.dipima.multichannelmobile.MainActivity;
import ch.zh.dipima.multichannelmobile.R;

public abstract class Message {
	public static final int MESSAGE_STATE_INITIALIZED = 1;
	public static final int MESSAGE_STATE_SENT = 2;
	public static final int MESSAGE_STATE_NOTSENT = 3;
	
	protected Activity a;
	
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
	
	public static Message factory(int msgType, Activity a) {
		Message msg = null;
		switch(msgType) {
		case MainActivity.MESSAGE_TYPE_EMAIL:
			msg = new Email(a);
			break;
		case MainActivity.MESSAGE_TYPE_SMS:
			msg = new SMS(a);
			break;
		case MainActivity.MESSAGE_TYPE_MMS:
			msg = new MMS(a);
			break;
		case MainActivity.MESSAGE_TYPE_PRINT:
			msg = new Print(a);
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
	
	//add attachment
	public void addAttachment(String filePath) {
		TextView fileField = (TextView)a.findViewById(R.id.file_attachment);
		if(fileField != null) {
			fileField.setText(filePath);
		}
	}
	
	/************* Abstract Methods **************/
	
	// methods to be overriden
	public abstract void drawMessageGUI();
	public abstract void sendMessage();
}
