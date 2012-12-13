/**
 * @author: Pius Fonseka, Dieter Good, Marcel Styger (BWI A-11)
 * @version 1.0
 * @description: Android App for HWZ Project, 2012
 */

package ch.zh.dipima.multichannelmobile.message;

import ch.zh.dipima.multichannelmobile.exceptions.ErrorInMessageException;

public interface Validatable {
	boolean validate() throws ErrorInMessageException;
	void showErrorMessage(ErrorInMessageException e);
}
