/**
 * @author: Pius Fonseka, Dieter Good, Marcel Styger (BWI A-11)
 * @version 1.0
 * @description: Android App for HWZ Project, 2012
 */

package ch.zh.dipima.multichannelmobile.message;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;
import ch.zh.dipima.multichannelmobile.MainActivity;
import ch.zh.dipima.multichannelmobile.R;
import ch.zh.dipima.multichannelmobile.exceptions.ErrorInMessageException;

public class Print extends Message implements Validatable {
	
	private String body;

	public Print(Activity a) {
		super();
		//set the type of this message
		setMsgType(MainActivity.MESSAGE_TYPE_PRINT);
		this.a = a;
	}
	
	//method validate neets to be implemented from interface Validatable
	@Override
	public boolean validate() throws ErrorInMessageException {
		EditText bodyView = (EditText) a.findViewById(R.id.print_body);
		body = bodyView.getText().toString();
		
		if(body.equalsIgnoreCase("")) {
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
		a.setTitle(a.getResources().getString(R.string.title_activity_write_print));
		a.setContentView(R.layout.activity_write_print);
	}

	//method sendMessage neets to be implemented from abstract class Message
	@Override
	public void sendMessage() {
		try {
			if(validate()) {
				//set send state to sent after a successful validation
				//todo really send to printer
				setSentState(MESSAGE_STATE_SENT);
				Toast.makeText(a.getBaseContext(), "Print erfolgreich versendet.", Toast.LENGTH_LONG).show();
				writeLog("#msg:" + body);
				
				Intent ihome = new Intent().setClass(a.getApplicationContext(),
						MainActivity.class).setFlags(
						Intent.FLAG_ACTIVITY_CLEAR_TOP
								| Intent.FLAG_ACTIVITY_SINGLE_TOP);
				a.startActivity(ihome);
			}
		} catch (ErrorInMessageException e) {
			//throw error and set state to "notsent" after unsucessful validation
			setSentState(MESSAGE_STATE_NOTSENT);
			showErrorMessage( e);
		} finally {
			//feature todo: switch on message state and perform different action
		}
	}
}
