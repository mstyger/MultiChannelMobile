package ch.zh.dipima.multichannelmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Info extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setTitle(getResources().getString(R.string.title_activity_info));
    }

	@Override
	public void onBackPressed() {
		Intent ihome = new Intent().setClass(getApplicationContext(),
				MainActivity.class).setFlags(
				Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(ihome);
		return;
	}
}
