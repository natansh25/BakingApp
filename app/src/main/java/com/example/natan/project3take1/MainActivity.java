package com.example.natan.project3take1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.natan.project3take1.Adapters.RecipeAdapter;
import com.example.natan.project3take1.AsyncTask.AsyncListner;
import com.example.natan.project3take1.AsyncTask.MyAsyncTask;
import com.example.natan.project3take1.Pojo.Recepie;
import com.example.natan.project3take1.Utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncListner, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;
    private URL url;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // swiping to refresh-------------------------

        mRecyclerView = findViewById(R.id.recyclerView);
        mSwipeRefreshLayout=findViewById(R.id.swip_to_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        url = NetworkUtils.buildURl();
        //checking Internet connection-----------------------------------------------------
        if (isOnline()) {

            new MyAsyncTask(this).execute(url);
        } else {
            Toast.makeText(this, "Check your Internet Connection !!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void returnRecipe(ArrayList<Recepie> recepies) {

        mRecipeAdapter = new RecipeAdapter(recepies);
        mSwipeRefreshLayout.setRefreshing(false);
        mRecyclerView.setAdapter(mRecipeAdapter);
        mRecipeAdapter.notifyDataSetChanged();

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    //Refreshing


    @Override
    public void onRefresh() {
        downloadRecipe();
    }

    private void downloadRecipe() {
        if (isOnline()) {
            mSwipeRefreshLayout.setRefreshing(true);
            new MyAsyncTask(this).execute(url);
            mRecipeAdapter.notifyDataSetChanged();

        } else {
            mSwipeRefreshLayout.setRefreshing(false);

        }


    }
}
