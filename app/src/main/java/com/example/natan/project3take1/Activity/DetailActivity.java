package com.example.natan.project3take1.Activity;


import android.support.v4.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.natan.project3take1.Fragments.FragmentDetailActivity;

import com.example.natan.project3take1.R;


public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_detail);

       // getActionBar().setDisplayHomeAsUpEnabled(true);


        if (MainActivity.isTablet && (getSupportActionBar() != null)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentDetailActivity fragmentDetailActivity = new FragmentDetailActivity();
            fragmentManager.beginTransaction()
                    .add(R.id.steps_details_frame, fragmentDetailActivity)
                    .commit();


        }


    }
}








