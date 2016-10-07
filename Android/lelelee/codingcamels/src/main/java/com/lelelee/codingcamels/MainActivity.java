package com.lelelee.codingcamels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends Activity {
    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String FIRST_TIME = "my_first_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isFirstTimeUser(this)) {
            Intent intent = new Intent(this, InstallationActivity.class);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_main);
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentLayoutContainer, new LeleleeFragment(), LeleleeFragment.TAG)
                    .commit();
        }
    }

    //first time user
    public static boolean isFirstTimeUser(Context context) {
        SharedPreferences sharedSettings = context.getSharedPreferences(PREFS_NAME, 0);
        return sharedSettings.getBoolean(FIRST_TIME, true);
    }

    public static void setFirstTimeUser(Context context, boolean firstTime) {
        SharedPreferences sharedSettings = context.getSharedPreferences(MainActivity.PREFS_NAME, 0);
        sharedSettings.edit().putBoolean(MainActivity.FIRST_TIME, firstTime).commit();
    }
}