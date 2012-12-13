package ch.zh.dipima.multichannelmobile.exceptions;

@SuppressWarnings("serial")
public class ErrorInMessageException extends Exception {
	public static final int ERROR_TOOLONG = 1;
	public static final int ERROR_MISSINGSUBJECT = 2;
	public static final int ERROR_MISSINGBODY = 3;
	public static final int ERROR_MISSINGRECIPIENT = 4;
	public static final int ERROR_INVALIDADRESS = 5;

	private int errorCode = 0;

	public ErrorInMessageException(int errorCode) {
		this.errorCode = errorCode;
	}

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
