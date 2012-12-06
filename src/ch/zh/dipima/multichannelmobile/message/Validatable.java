package ch.zh.dipima.multichannelmobile.message;

import ch.zh.dipima.multichannelmobile.exceptions.ErrorInMessageException;

public interface Validatable {
	public boolean validate();
	public void showErrorMessage(ErrorInMessageException e);
}
