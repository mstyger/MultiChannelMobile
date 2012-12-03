package ch.zh.dipima.multichannelmobile;

import android.app.Activity;
import android.os.Bundle;
import ch.zh.dipima.multichannelmobile.message.Message;

public class WriteMessage extends Activity {
	private Message msg;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_message);
        
        final Bundle extras = getIntent().getExtras();
		
		if(extras != null) {
			int msgType = extras.getInt(MainActivity.MESSAGE_TYPE);
			msg = Message.factory(msgType);
			
			switch(msg.getMsgType()) {
			case MainActivity.MESSAGE_TYPE_EMAIL:
				setTitle(getResources().getString(R.string.title_activity_write_email));
				break;
			case MainActivity.MESSAGE_TYPE_SMS:
				setTitle(getResources().getString(R.string.title_activity_write_sms));
				break;
			case MainActivity.MESSAGE_TYPE_MMS:
				setTitle(getResources().getString(R.string.title_activity_write_mms));
				break;
			case MainActivity.MESSAGE_TYPE_PRINT:
				setTitle(getResources().getString(R.string.title_activity_write_print));
				break;
			}
			
			msg.drawMessageFields();
		}
    }
}
