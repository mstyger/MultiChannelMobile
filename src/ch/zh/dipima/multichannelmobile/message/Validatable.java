/**
 * @author: Pius Fonseka, Dieter Good, Marcel Styger (BWI A-11)
 * @version 1.0
 * @description: Android App for HWZ Project, 2012
 */

package ch.zh.dipima.multichannelmobile.message;

import ch.zh.dipima.multichannelmobile.exceptions.ErrorInMessageException;

public interface Validatable {
	//every messagetype should validate its fields before sending
	boolean validate() throws ErrorInMessageException;
	//every message should be able to show the error message, when validation failed
	void showErrorMessage(ErrorInMessageException e);
}
