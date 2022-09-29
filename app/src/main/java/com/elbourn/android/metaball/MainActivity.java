package com.elbourn.android.metaball;

import android.os.Bundle;
import android.util.Log;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class MainActivity extends OptionsMenu {

    private String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "start onCreate");
        setContentView(R.layout.activity_main);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        Log.i(TAG, "navHostFragment: " + navHostFragment);
        NavController navController = navHostFragment.getNavController();
        Log.i(TAG, "navController: " + navController);
        navController.navigate(R.id.disclaimerFragment);
        Log.i(TAG, "start onCreate");
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.i(TAG, "start onResume");
//
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
//        NavController navController = navHostFragment.getNavController();
//        navController.navigate(R.id.disclaimerFragment);
//
//
//        Log.i(TAG, "end onResume");
//    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
            finishAffinity();
    }
}