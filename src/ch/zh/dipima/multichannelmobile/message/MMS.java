package ch.zh.dipima.multichannelmobile.message;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;
import ch.zh.dipima.multichannelmobile.MainActivity;
import ch.zh.dipima.multichannelmobile.R;
import ch.zh.dipima.multichannelmobile.exceptions.ErrorInMessageException;

public class MMS extends Message implements Validatable {
	
	public MMS(Activity a) {
		super();
		setMsgType(MainActivity.MESSAGE_TYPE_MMS);
		this.a = a;
	}
	
	@Override
	public boolean validate() throws ErrorInMessageException {
		EditText recipientView = (EditText) a.findViewById(R.id.mms_recipient);
		EditText bodyView = (EditText) a.findViewById(R.id.mms_body);
		
		if(null == recipientView || recipientView.getText().toString().equalsIgnoreCase("")) {
			throw new ErrorInMessageException(
					ErrorInMessageException.ERROR_MISSINGRECIPIENT);
		} else if(null == bodyView || bodyView.getText().toString().equalsIgnoreCase("")) {
			throw new ErrorInMessageException(
					ErrorInMessageException.ERROR_MISSINGBODY);
		} else {
			return true;
		}
	}
	
	@Override
	public void showErrorMessage(ErrorInMessageException e) {
		Toast.makeText(a.getBaseContext(), e.getErrorMessage(), Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void drawMessageGUI() {
		a.setTitle(a.getResources().getString(R.string.title_activity_write_mms));
		a.setContentView(R.layout.activity_write_mms);
	}

	@Override
	public void sendMessage() {
		try {
			if(validate()) {
				//todo send message
				setSentState(MESSAGE_STATE_SENT);
				Toast.makeText(a.getBaseContext(), "MMS erfolgreich versendet.", Toast.LENGTH_LONG).show();
			}
		} catch (ErrorInMessageException e) {
			setSentState(MESSAGE_STATE_NOTSENT);
			showErrorMessage(e);
		} finally {
			writeLog();
		}
	}
}
