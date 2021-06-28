package com.lelelee.codingcamels;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Sami Eljabali on 9/17/14.
 */
public class InstallationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_installation);

        WelcomePageFragment fragment = new WelcomePageFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentLayoutContainer_installation, fragment)
                .addToBackStack(WelcomePageFragment.TAG)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        }
        super.onBackPressed();
    }
}
