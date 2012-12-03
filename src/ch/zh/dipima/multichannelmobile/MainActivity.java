package ch.zh.dipima.multichannelmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    public static final String MESSAGE_TYPE = "msg_type";
    
    public static final int MESSAGE_TYPE_EMAIL = 1;
    public static final int MESSAGE_TYPE_SMS = 2;
    public static final int MESSAGE_TYPE_MMS = 3;
    public static final int MESSAGE_TYPE_PRINT = 4;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getResources().getString(R.string.title_activity_main));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_info:
            	Intent info = new Intent().setClass(getApplicationContext(),
    					Info.class).setFlags(
    					Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
    			startActivity(info);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public void setType(View v) {
    	Intent info = new Intent().setClass(
    			getApplicationContext(),
				WriteMessage.class).setFlags(
				Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
    	
    	switch(v.getId()) {
    	case R.id.btn_msg_email:
    		info.putExtra(MainActivity.MESSAGE_TYPE, MainActivity.MESSAGE_TYPE_EMAIL);
    		break;
    	case R.id.btn_msg_sms:
    		info.putExtra(MainActivity.MESSAGE_TYPE, MainActivity.MESSAGE_TYPE_SMS);
    		break;
    	case R.id.btn_msg_mms:
    		info.putExtra(MainActivity.MESSAGE_TYPE, MainActivity.MESSAGE_TYPE_MMS);
    		break;
    	case R.id.btn_msg_print:
    		info.putExtra(MainActivity.MESSAGE_TYPE, MainActivity.MESSAGE_TYPE_PRINT);
    		break;
    	}
		startActivity(info);
    }
}
