package com.example.natan.project3take1.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.natan.project3take1.Adapters.IngredientsAdapter;
import com.example.natan.project3take1.Adapters.StepsAdapter;
import com.example.natan.project3take1.Fragments.FragmentDetailActivity;
import com.example.natan.project3take1.Fragments.FragmentStepsDetailActivity;
import com.example.natan.project3take1.Pojo.Ingredients;
import com.example.natan.project3take1.Pojo.Recepie;
import com.example.natan.project3take1.Pojo.Steps;
import com.example.natan.project3take1.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StepsDetailActivity extends AppCompatActivity {

    //BindingViewsWithButterKnif


   /* //
    private IngredientsAdapter mIngredientsAdapter;
    private StepsAdapter mStepsAdapter;

    //List Creation
    public static ArrayList<Steps> stepslist;
    private ArrayList<Ingredients> ingredientsList;
    private int index = 0;
    private RecyclerView mRecyclerView;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


       /* if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (!MainActivity.isTablet) {
                FragmentDetailActivity fragmentDetailActivity = new FragmentDetailActivity();
                fragmentManager.beginTransaction()
                        .add(R.id.steps_details_frame, fragmentDetailActivity)
                        .commit();

                FragmentStepsDetailActivity fragmentStepsDetailActivity = new FragmentStepsDetailActivity();
                fragmentManager.beginTransaction()
                        .replace(R.id.detail_activity_layout, fragmentStepsDetailActivity)
                        .commit();
            } else {*/
                    FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentStepsDetailActivity fragmentStepsDetailActivity = new FragmentStepsDetailActivity();
                fragmentManager.beginTransaction()
                        .add(R.id.detail, fragmentStepsDetailActivity)
                        .commit();

        }
       /* //index =getIntent().getExtras().getInt("items");
        //ingredientsList=recipeList.get(index).getIngredients();
        Recepie recipeList = getIntent().getParcelableExtra("items");

        ingredientsList = recipeList.getIngredients();
        Log.i("tagu21", String.valueOf(recipeList.getIngredients()));
        stepslist = recipeList.getSteps();

        // Ingredients steps------------------------------------------------------
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRecyclerViewIngredient.setLayoutManager(manager);
        mRecyclerViewIngredient.setItemAnimator(new DefaultItemAnimator());
        mIngredientsAdapter = new IngredientsAdapter(ingredientsList);
        mRecyclerViewIngredient.setAdapter(mIngredientsAdapter);
        mIngredientsAdapter.notifyDataSetChanged();

        // Steps steps-------------------------------------------------------------
        RecyclerView.LayoutManager manager2 = new LinearLayoutManager(this);
        mRecyclerViewSteps.setLayoutManager(manager2);
        mRecyclerViewSteps.setItemAnimator(new DefaultItemAnimator());
        mStepsAdapter = new StepsAdapter(stepslist, this);

        mRecyclerViewSteps.setAdapter(mStepsAdapter);
        mStepsAdapter.notifyDataSetChanged();
*/

    }


