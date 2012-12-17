/**
 * @author: Pius Fonseka, Dieter Good, Marcel Styger (BWI A-11)
 * @version 1.0
 * @description: Android App for HWZ Project, 2012
 */

package ch.zh.dipima.multichannelmobile.exceptions;

@SuppressWarnings("serial")
public class ErrorInMessageException extends Exception {
	//possible errors
	public static final int ERROR_TOOLONG = 1;
	public static final int ERROR_MISSINGSUBJECT = 2;
	public static final int ERROR_MISSINGBODY = 3;
	public static final int ERROR_MISSINGRECIPIENT = 4;
	public static final int ERROR_INVALIDADRESS = 5;

	//actual error code
	private int errorCode = 0;

	//every error thrown has an errorcode, which is added initially
	public ErrorInMessageException(int errorCode) {
		this.errorCode = errorCode;
	}

	//method to get readable errorcode
	public String getErrorMessage() {
		String errorMessage = "";
		switch (errorCode) {
		case ERROR_TOOLONG:
			errorMessage = "Ihre Nachricht ist zu lang!";
			break;
		case ERROR_MISSINGSUBJECT:
			errorMessage = "Titel fehlt!";
			break;
		case ERROR_MISSINGBODY:
			errorMessage = "Nachricht ist leer!";
			break;
		case ERROR_MISSINGRECIPIENT:
			errorMessage = "Empfänger fehlt!";
			break;
		case ERROR_INVALIDADRESS:
			errorMessage = "Ungültiges Format der Email-Adresse!";
			break;

		}
		return errorMessage;
	}
}
