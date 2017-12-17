package com.example.natan.project3take1.AsyncTask;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.example.natan.project3take1.Pojo.Recepie;
import com.example.natan.project3take1.Utils.NetworkUtils;

import org.json.JSONException;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by natan on 12/17/2017.
 */

public class MyAsyncTask extends AsyncTask<URL, Void, ArrayList<Recepie>> {

    private AsyncListner mListner;

    public MyAsyncTask(AsyncListner listner) {
        mListner = listner;
    }

    @Override
    protected ArrayList<Recepie> doInBackground(URL... urls) {
        try {
            ArrayList<Recepie> json = NetworkUtils.fetchRecipeData(urls[0]);
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Recepie> recepies) {
        super.onPostExecute(recepies);
        mListner.returnRecipe(recepies);

    }


}
