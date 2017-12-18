package com.example.natan.project3take1.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.natan.project3take1.Pojo.Ingredients;
import com.example.natan.project3take1.Pojo.Recepie;
import com.example.natan.project3take1.Pojo.Steps;
import com.example.natan.project3take1.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by natan on 12/17/2017.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.MyViewHolder> {

    ArrayList<Steps> mSteps;


    final private ListItemClickListener mListItemClickListener;


    //Interface

    public interface ListItemClickListener {

        void onListItemClick(Steps steps);

    }

    public StepsAdapter(ArrayList<Steps> steps, ListItemClickListener listItemClickListener) {
        mSteps = steps;
        mListItemClickListener = listItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.steps_custom, parent, false);

        return new StepsAdapter.MyViewHolder(itemView);






    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Steps steps= mSteps.get(position);
        holder.txt_stepId.setText(steps.getId());
        Log.i("xxx",steps.getId());
        holder.txt_stepDescription.setText(steps.getShortDescription());
        Log.i("xxx",steps.getShortDescription());
        holder.bind(mSteps.get(position), mListItemClickListener);

    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_step_id)
        TextView txt_stepId;
        @BindView(R.id.txt_step_description)
        TextView txt_stepDescription;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void bind(final Steps steps, final StepsAdapter.ListItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onListItemClick(steps);
                }
            });
        }
    }


}
