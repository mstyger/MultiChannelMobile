/**
 * @author: Pius Fonseka, Dieter Good, Marcel Styger (BWI A-11)
 * @version 1.0
 * @description: Android App for HWZ Project, 2012
 */

package ch.zh.dipima.multichannelmobile.message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import ch.zh.dipima.multichannelmobile.MainActivity;
import ch.zh.dipima.multichannelmobile.R;
import ch.zh.dipima.multichannelmobile.WriteMessage;
import ch.zh.dipima.multichannelmobile.exceptions.ErrorInMessageException;

public class Email extends Message implements Validatable {
	private String recipient;
	private String subject;
	private String body;
	private String attachment = "";

	public Email(Activity a) {
		super();
		//set the type of this message
		setMsgType(MainActivity.MESSAGE_TYPE_EMAIL);
		this.a = a;
	}

	//method validate neets to be implemented from interface Validatable
	@Override
	public boolean validate() throws ErrorInMessageException {
		EditText recipientView = (EditText) a
				.findViewById(R.id.email_recipient);
		EditText subjectView = (EditText) a.findViewById(R.id.email_subject);
		EditText bodyView = (EditText) a.findViewById(R.id.email_body);
		TextView attachmentView = (TextView) a
				.findViewById(R.id.file_attachment);

		recipient = recipientView.getText().toString();
		subject = subjectView.getText().toString();
		body = bodyView.getText().toString();
		attachment = attachmentView.getText().toString();

		if (recipient.equalsIgnoreCase("")) {
			throw new ErrorInMessageException(
					ErrorInMessageException.ERROR_MISSINGRECIPIENT);
		} else if (subject.equalsIgnoreCase("")) {
			throw new ErrorInMessageException(
					ErrorInMessageException.ERROR_MISSINGSUBJECT);
		} else if (body.equalsIgnoreCase("")) {
			throw new ErrorInMessageException(
					ErrorInMessageException.ERROR_MISSINGBODY);
		} else {
			String expression = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(recipient);
			if (!matcher.matches()) {
				throw new ErrorInMessageException(
						ErrorInMessageException.ERROR_INVALIDADRESS);
			}
			return true;
		}
	}

	//method showErrorMessage neets to be implemented from interface Validatable
	@Override
	public void showErrorMessage(ErrorInMessageException e) {
		Toast.makeText(a.getBaseContext(), e.getErrorMessage(),
				Toast.LENGTH_LONG).show();
	}

	//method drawMessageGUI neets to be implemented from abstract class Message
	@Override
	public void drawMessageGUI() {
		a.setTitle(a.getResources().getString(
				R.string.title_activity_write_email));
		a.setContentView(R.layout.activity_write_email);
	}

	//method sendMessage neets to be implemented from abstract class Message
	@Override
	public void sendMessage() {
		try {
			if (validate()) {
				//set send state to sent after a successful validation
				setSentState(MESSAGE_STATE_SENT);

				final Intent emailIntent = new Intent(
						android.content.Intent.ACTION_SEND);
				emailIntent.setType("plain/text");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
						new String[] { recipient });
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
						subject);
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);

				if (!attachment.equalsIgnoreCase("")) {
					//todo add attachment (in version 1.0 it's only added as a string with the path, not actually as a real file)
				}

				try {
					a.startActivityForResult(
							Intent.createChooser(emailIntent, "Send Mail..."),
							WriteMessage.SEND_REQUEST_CODE);
				} catch (android.content.ActivityNotFoundException ex) {
					Toast.makeText(a, "Kein Emailclient gefunden.",
							Toast.LENGTH_SHORT).show();
				}
				writeLog("#to:" + recipient + "#subject:" + subject + "#msg:"
						+ body + "#attachment:" + attachment);
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
