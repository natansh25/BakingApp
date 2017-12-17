package com.example.natan.project3take1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.natan.project3take1.Adapters.RecipeAdapter;
import com.example.natan.project3take1.AsyncTask.AsyncListner;
import com.example.natan.project3take1.AsyncTask.MyAsyncTask;
import com.example.natan.project3take1.Pojo.Recepie;
import com.example.natan.project3take1.Utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncListner {

    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        URL url = NetworkUtils.buildURl();
        new MyAsyncTask(this).execute(url);

    }

    @Override
    public void returnRecipe(ArrayList<Recepie> recepies) {

        mRecipeAdapter = new RecipeAdapter(recepies);
        mRecyclerView.setAdapter(mRecipeAdapter);
        mRecipeAdapter.notifyDataSetChanged();

    }


}
