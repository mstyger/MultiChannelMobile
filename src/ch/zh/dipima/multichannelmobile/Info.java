package ch.zh.dipima.multichannelmobile;

import android.app.Activity;
import android.os.Bundle;

public class Info extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setTitle(getResources().getString(R.string.title_activity_info));
    }
}
