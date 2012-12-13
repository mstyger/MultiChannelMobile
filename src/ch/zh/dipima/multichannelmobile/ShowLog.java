/**
 * @author: Pius Fonseka, Dieter Good, Marcel Styger (BWI A-11)
 * @version 1.0
 * @description: Android App for HWZ Project, 2012
 */

package ch.zh.dipima.multichannelmobile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowLog extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log);
		setTitle(getResources().getString(R.string.title_activity_log));

		displayLog();
	}

	@Override
	public void onBackPressed() {
		Intent ihome = new Intent().setClass(getApplicationContext(),
				MainActivity.class).setFlags(
				Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(ihome);
		return;
	}

	private void displayLog() {
		String logMessages = "";
		FileInputStream fIn;
		try {
			fIn = openFileInput("messagelog.txt");
			InputStreamReader isr = new InputStreamReader(fIn);

			int data = isr.read();
			while (data != -1) {
				char theChar = (char) data;
				data = isr.read();
				logMessages += theChar;
			}
			((TextView) findViewById(R.id.id_log_container)).setText(logMessages);
			isr.close();
		} catch (FileNotFoundException e) {
			// show log in android log cat
			Log.d("printr", "Fehler: " + e.getMessage());
		} catch (IOException e) {
			// show log in android log cat
			Log.d("printr", "Fehler: " + e.getMessage());
		}
	}
}
