package com.example.natan.project3take1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.natan.project3take1.Utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        URL url = NetworkUtils.buildURl();
        new AsynClass().execute(url);


    }


    class AsynClass extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            try {
                String json = NetworkUtils.getResponseFromHttpUrl(urls[0]);
                Log.i("json21", json);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }


}
