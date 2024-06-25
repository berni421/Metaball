package com.elbourn.android.metaball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.List;
import java.util.function.IntToDoubleFunction;

public class OptionsMenu extends AppCompatActivity {

    private String TAG = "OptionsMenu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        Context context = getApplicationContext();
        MenuItem introCheckBox = menu.findItem(R.id.menuIntroOff);
        introCheckBox.setChecked(IntroFragment.getIntroCheckBox(context));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuIntroOff:
                setIntroductionOff(item);
                return true;
            case R.id.menuDonate:
                startDonationWebsite();
                return true;
                case R.id.restart:
                restartWebviewFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void setIntroductionOff(MenuItem item) {
        Context context = getApplicationContext();
        Boolean subscriptionsIntroOff = !item.isChecked();
        item.setChecked(subscriptionsIntroOff);
        IntroFragment.setIntroCheckBox(context, subscriptionsIntroOff);
        Log.i(TAG, "subscriptionsIntroOff: " + subscriptionsIntroOff);
    }

    void startDonationWebsite() {
        Log.i(TAG, "start startDonationWebsite");
        Context context = getApplicationContext();
        runOnUiThread(new Runnable() {
            public void run() {
                String msg = "Starting browser to feed the cat ...";
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        });
        String url = "https://www.elbourn.com/feed-the-cat/";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
        Log.i(TAG, "end startDonationWebsite");
    }

    void restartWebviewFragment() {
        Log.i(TAG, "start restartWebviewFragment");
        Fragment nHF = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        Log.i(TAG, "nHf: " + nHF);
        if (nHF == null) return;
        List<Fragment> fs = nHF.getChildFragmentManager().getFragments();
        Log.i(TAG, "fs: " + fs);
        for (Fragment f : fs) {
            Log.i(TAG, "f: " + f);
            if (f.getClass() != WebviewFragment.class) return;
            nHF.getChildFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .remove(f)
                    .commit();
            nHF.getChildFragmentManager().beginTransaction()
                    .add(f, null)
                    .commit();
        }
    }
}