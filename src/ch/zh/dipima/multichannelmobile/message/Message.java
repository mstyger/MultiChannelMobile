package ch.zh.dipima.multichannelmobile.message;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
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
		switch (msgType) {
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
	@SuppressLint("WorldReadableFiles")
	protected void writeLog(String msg) {
		try {
			/*
			 * We have to use the openFileOutput()-method the ActivityContext
			 * provides, to protect your file from others and this is done for
			 * security-reasons. We chose MODE_WORLD_READABLE, because we have
			 * nothing to hide in our file
			 */
			
			FileOutputStream fOut = a.openFileOutput("messagelog.txt", Activity.MODE_APPEND);
			OutputStreamWriter osw = new OutputStreamWriter(fOut);
			
			String msgType = "";
			switch(getMsgType()) {
			case MainActivity.MESSAGE_TYPE_SMS:
				msgType = "SMS";
				break;
			case MainActivity.MESSAGE_TYPE_MMS:
				msgType = "MMS";
				break;
			case MainActivity.MESSAGE_TYPE_EMAIL:
				msgType = "Email";
				break;
			case MainActivity.MESSAGE_TYPE_PRINT:
				msgType = "Print";
				break;
			}
			
			String time = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss").format(getMsgTime());
			// Write the string to the file
			osw.write("Typ: " + msgType + "\nTime: " + time + "\nNachricht: " + msg + "\n---------------------\n");

			/*
			 * ensure that everything is really written out and close
			 */
			osw.flush();
			osw.close();
		} catch (IOException ioe) {
			//show log in android log cat
			Log.d("printr", "Fehler: " + ioe.getMessage());
		}
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

	// add attachment
	public void addAttachment(String filePath) {
		TextView fileField = (TextView) a.findViewById(R.id.file_attachment);
		if (fileField != null) {
			fileField.setText(filePath);
		}
	}

	/************* Abstract Methods **************/

	// methods to be overriden
	public abstract void drawMessageGUI();

	public abstract void sendMessage();
}
