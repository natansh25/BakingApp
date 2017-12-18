package com.example.natan.project3take1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.natan.project3take1.Adapters.IngredientsAdapter;
import com.example.natan.project3take1.Pojo.Ingredients;
import com.example.natan.project3take1.Pojo.Recepie;
import com.example.natan.project3take1.Pojo.Steps;
import com.example.natan.project3take1.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;



public class StepsDetailActivity extends AppCompatActivity {

        //BindingViewsWithButterKnif

        @BindView(R.id.ingredient_list)
    RecyclerView mRecyclerViewIngredient;

    //
    private IngredientsAdapter mIngredientsAdapter;

    //List Creation
    private ArrayList<Steps> stepslist;
    private ArrayList<Ingredients> ingredientsList;
    private int index=0;
    private RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_detail);
        mRecyclerView=findViewById(R.id.ingredient_list);
        ButterKnife.bind(this);
        //index =getIntent().getExtras().getInt("items");
        //ingredientsList=recipeList.get(index).getIngredients();
        Recepie recipeList=getIntent().getParcelableExtra("items");
        ingredientsList=recipeList.getIngredients();
        Log.i("tagu21", String.valueOf(recipeList.getIngredients()));







        if(ingredientsList!=null) {
            RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
            mRecyclerViewIngredient.setLayoutManager(manager);
            mRecyclerViewIngredient.setItemAnimator(new DefaultItemAnimator());
            mIngredientsAdapter = new IngredientsAdapter(ingredientsList);
            mRecyclerViewIngredient.setAdapter(mIngredientsAdapter);
            mIngredientsAdapter.notifyDataSetChanged();
        }
        else
        {
            Toast.makeText(this, "Null list", Toast.LENGTH_SHORT).show();
        }

    }
}
