package com.example.natan.project3take1.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.natan.project3take1.Activity.MainActivity;
import com.example.natan.project3take1.Activity.StepsDetailActivity;
import com.example.natan.project3take1.Adapters.RecipeAdapter;
import com.example.natan.project3take1.AsyncTask.AsyncListner;
import com.example.natan.project3take1.AsyncTask.MyAsyncTask;
import com.example.natan.project3take1.Pojo.Recepie;
import com.example.natan.project3take1.R;
import com.example.natan.project3take1.Utils.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by natan on 12/20/2017.
 */

public class FragmentMain extends Fragment implements AsyncListner, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;
    private URL url;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static ArrayList<Recepie> recipeList;

    public FragmentMain() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        if(isOnline()) {

            mRecyclerView = rootView.findViewById(R.id.recyclerView);
            mSwipeRefreshLayout = rootView.findViewById(R.id.swip_to_refresh);
            mSwipeRefreshLayout.setOnRefreshListener(this);
            RecyclerView.LayoutManager manager;
            if (MainActivity.isTablet) {
                manager = new GridLayoutManager(getActivity(), 2);
            } else {
                manager = new LinearLayoutManager(getActivity());
            }

            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            url = NetworkUtils.buildURl();
            //checking Internet connection-----------------------------------------------------


            new MyAsyncTask(this).execute(url);
            Log.i("tablu21", String.valueOf(MainActivity.isTablet));
        }
        else
        {
            Toast.makeText(getActivity(), "Check Your Internet Connection !!", Toast.LENGTH_SHORT).show();
        }

        return rootView;

    }


    @Override
    public void returnRecipe(ArrayList<Recepie> recepies) {

        mRecipeAdapter = new RecipeAdapter(recepies, new RecipeAdapter.ListItemClickListener() {
            @Override
            public void onListItemClick(Recepie recepie) {
                Intent intent = new Intent(getActivity(), StepsDetailActivity.class);
                intent.putExtra("items", recepie);
                startActivity(intent);


            }
        });

        mSwipeRefreshLayout.setRefreshing(false);
        mRecyclerView.setAdapter(mRecipeAdapter);
        mRecipeAdapter.notifyDataSetChanged();
        recipeList = recepies;


    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onRefresh() {
        downloadRecipe();
    }

    private void downloadRecipe() {

        mSwipeRefreshLayout.setRefreshing(true);
        new MyAsyncTask(this).execute(url);
        mRecipeAdapter.notifyDataSetChanged();


    }
}
