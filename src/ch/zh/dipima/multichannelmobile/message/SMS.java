/**
 * @author: Pius Fonseka, Dieter Good, Marcel Styger (BWI A-11)
 * @version 1.0
 * @description: Android App for HWZ Project, 2012
 */

package ch.zh.dipima.multichannelmobile.message;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.EditText;
import android.widget.Toast;
import ch.zh.dipima.multichannelmobile.MainActivity;
import ch.zh.dipima.multichannelmobile.R;
import ch.zh.dipima.multichannelmobile.WriteMessage;
import ch.zh.dipima.multichannelmobile.exceptions.ErrorInMessageException;

public class SMS extends Message implements Validatable {
	//max length of message
	private static final int MAX_LENGTH = 160;
	
	private String recipient;
	private String body;

	public SMS(Activity a) {
		super();
		//set the type of this message
		setMsgType(MainActivity.MESSAGE_TYPE_SMS);
		this.a = a;
	}
	
	//method validate neets to be implemented from interface Validatable 
	@Override
	public boolean validate() throws ErrorInMessageException {
		EditText recipientView = (EditText) a.findViewById(R.id.sms_recipient);
		EditText bodyView = (EditText) a.findViewById(R.id.sms_body);
		
		recipient = recipientView.getText().toString();
		body = bodyView.getText().toString();
		
		if (body.length() > SMS.MAX_LENGTH) {
			throw new ErrorInMessageException(
					ErrorInMessageException.ERROR_TOOLONG);
		} else if(recipient.equalsIgnoreCase("")) {
			throw new ErrorInMessageException(
					ErrorInMessageException.ERROR_MISSINGRECIPIENT);
		} else if(body.equalsIgnoreCase("")) {
			throw new ErrorInMessageException(
					ErrorInMessageException.ERROR_MISSINGBODY);
		} else {
			return true;
		}
	}
	
	//method showErrorMessage neets to be implemented from interface Validatable
	@Override
	public void showErrorMessage(ErrorInMessageException e) {
		Toast.makeText(a.getBaseContext(), e.getErrorMessage(), Toast.LENGTH_LONG).show();
	}
	
	//method drawMessageGUI neets to be implemented from abstract class Message
	@Override
	public void drawMessageGUI() {
		a.setTitle(a.getResources().getString(R.string.title_activity_write_sms));
		a.setContentView(R.layout.activity_write_sms);
	}

	//method sendMessage neets to be implemented from abstract class Message
	@Override
	public void sendMessage() {
		try {
			if(validate()) {
				//set send state to sent after a successful validation
				setSentState(MESSAGE_STATE_SENT);
				
				Uri smsUri = Uri.parse("smsto:" + recipient);
				final Intent intent = new Intent(Intent.ACTION_VIEW, smsUri);
				intent.putExtra("sms_body", body);
				intent.setData(Uri.parse("smsto:" + recipient));
				intent.putExtra("address", recipient);
				intent.setType("vnd.android-dir/mms-sms");
		        
		        try {
		        	a.startActivityForResult(Intent.createChooser(intent, "Send SMS..."), WriteMessage.SEND_REQUEST_CODE); 
	        	} catch (android.content.ActivityNotFoundException ex) {
	        	   Toast.makeText(a, "Kein SMS-Client gefunden.", Toast.LENGTH_SHORT).show();
	        	}
		        writeLog("#to:" + recipient + "#msg:" + body);
			}
		} catch (ErrorInMessageException e) {
			//throw error and set state to "notsent" after unsucessful validation
			setSentState(MESSAGE_STATE_NOTSENT);
			showErrorMessage(e);
		} finally {
			//feature todo: switch on message state and perform different action
		}
	}
}
