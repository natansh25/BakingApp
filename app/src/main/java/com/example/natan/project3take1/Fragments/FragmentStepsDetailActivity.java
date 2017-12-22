package com.example.natan.project3take1.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.natan.project3take1.Activity.DetailActivity;
import com.example.natan.project3take1.Activity.MainActivity;
import com.example.natan.project3take1.Activity.StepsDetailActivity;
import com.example.natan.project3take1.Adapters.IngredientsAdapter;
import com.example.natan.project3take1.Adapters.StepsAdapter;
import com.example.natan.project3take1.Pojo.Ingredients;
import com.example.natan.project3take1.Pojo.Recepie;
import com.example.natan.project3take1.Pojo.Steps;
import com.example.natan.project3take1.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by natan on 12/20/2017.
 */

public class FragmentStepsDetailActivity extends Fragment implements StepsAdapter.ListItemClickListener {


    //BindingViewsWithButterKnif

    @BindView(R.id.ingredient_list)
    RecyclerView mRecyclerViewIngredient;

    @BindView(R.id.recipe_details_steps)
    RecyclerView mRecyclerViewSteps;

    //
    private IngredientsAdapter mIngredientsAdapter;
    private StepsAdapter mStepsAdapter;

    //List Creation
    public static ArrayList<Steps> stepslist;
    private ArrayList<Ingredients> ingredientsList;


    public FragmentStepsDetailActivity() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);


        ButterKnife.bind(this, rootView);
        Recepie recipeList = getActivity().getIntent().getParcelableExtra("items");

        ingredientsList = recipeList.getIngredients();
        Log.i("tagu21", String.valueOf(recipeList.getIngredients()));
        stepslist = recipeList.getSteps();

        // Ingredients steps------------------------------------------------------
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerViewIngredient.setLayoutManager(manager);
        mRecyclerViewIngredient.setItemAnimator(new DefaultItemAnimator());
        mIngredientsAdapter = new IngredientsAdapter(ingredientsList);
        mRecyclerViewIngredient.setAdapter(mIngredientsAdapter);
        mIngredientsAdapter.notifyDataSetChanged();

        // Steps steps-------------------------------------------------------------
        RecyclerView.LayoutManager manager2 = new LinearLayoutManager(getActivity());
        mRecyclerViewSteps.setLayoutManager(manager2);
        mRecyclerViewSteps.setItemAnimator(new DefaultItemAnimator());
        mStepsAdapter = new StepsAdapter(stepslist, this);

        mRecyclerViewSteps.setAdapter(mStepsAdapter);
        mStepsAdapter.notifyDataSetChanged();


        return rootView;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        if (!MainActivity.isTablet) {

            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putParcelableArrayListExtra("stepsi", stepslist);
            intent.putExtra("position", clickedItemIndex);
            startActivity(intent);
        } else {

            Bundle bundle=new Bundle();
            bundle.putInt("index",clickedItemIndex);
            bundle.putParcelableArrayList("bundle",stepslist);
            FragmentDetailActivity fragmentDetailActivityb=new FragmentDetailActivity();
            fragmentDetailActivityb.setArguments(bundle);

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentDetailActivity fragmentDetailActivity = new FragmentDetailActivity();
            fragmentDetailActivity.index = clickedItemIndex;
            fragmentManager.beginTransaction()
                    .replace(R.id.steps_details_frame, fragmentDetailActivity)
                    .commit();


        }

    }
}
