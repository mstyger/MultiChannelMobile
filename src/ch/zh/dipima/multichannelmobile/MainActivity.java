/**
 * @author: Pius Fonseka, Dieter Good, Marcel Styger (BWI A-11)
 * @version 1.0
 * @description: Android App for HWZ Project, 2012
 */

package ch.zh.dipima.multichannelmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    //key for intent
	public static final String MESSAGE_TYPE = "msg_type";
    
	//possible message types
    public static final int MESSAGE_TYPE_EMAIL = 1;
    public static final int MESSAGE_TYPE_SMS = 2;
    public static final int MESSAGE_TYPE_MMS = 3;
    public static final int MESSAGE_TYPE_PRINT = 4;

    //this method is called, when activity starts
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout & title
        setContentView(R.layout.activity_main);
        setTitle(getResources().getString(R.string.title_activity_main));
    }

	//this method is called, when user clicks on menu button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    //this method is called, when user selects a menu item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_info:
            	//start new activity to show infos
            	Intent info = new Intent().setClass(getApplicationContext(),
    					Info.class).setFlags(
    					Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
    			startActivity(info);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    //this method is called, when user selcts a messagetpye.
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
    
    //this user is called, when user wants to see log
    public void showLog(View v) {
    	Intent info = new Intent().setClass(
    			getApplicationContext(),
				ShowLog.class).setFlags(
				Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
    	
		startActivity(info);
    }
}
