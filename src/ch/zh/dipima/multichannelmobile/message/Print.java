package ch.zh.dipima.multichannelmobile.message;

import android.app.Activity;
import android.view.View;
import ch.zh.dipima.multichannelmobile.MainActivity;
import ch.zh.dipima.multichannelmobile.R;
import ch.zh.dipima.multichannelmobile.exceptions.ErrorInMessageException;

public class Print extends Message implements Validatable {
	
	public Print() {
		super();
		setMsgType(MainActivity.MESSAGE_TYPE_PRINT);
	}

	@Override
	public void drawMessageGUI(Activity a) {
		a.setTitle(a.getResources().getString(R.string.title_activity_write_print));
		a.setContentView(R.layout.activity_write_print);
	}

	@Override
	public boolean validate() {
		//todo: message validieren
		return false;
	}

	@Override
	public void sendMessage(View v) {
		if(validate()) {
			//send message
		} else {
			// throw error because text not valid
		}
	}

	@Override
	public void showErrorMessage(ErrorInMessageException e) {
		// TODO Auto-generated method stub
		
	}
}
