package ch.zh.dipima.multichannelmobile.message;

import ch.zh.dipima.multichannelmobile.exceptions.ErrorInMessageException;

public interface Validatable {
	boolean validate() throws ErrorInMessageException;
	void showErrorMessage(ErrorInMessageException e);
}
