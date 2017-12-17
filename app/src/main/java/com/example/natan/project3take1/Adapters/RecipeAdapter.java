package com.example.natan.project3take1.Adapters;

import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.natan.project3take1.MainActivity;
import com.example.natan.project3take1.Pojo.Recepie;
import com.example.natan.project3take1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by natan on 12/16/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    ArrayList<Recepie> mRecepies;

    public RecipeAdapter(ArrayList<Recepie> recepies) {
        mRecepies = recepies;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_custom, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Recepie recepie = mRecepies.get(position);
        Context context = holder.img.getContext();

        if(recepie.getImage()==null)

            {  Picasso.with(context)
                .load(recepie.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.img);
            }
        else
        {
            Picasso.with(context)
                    .load(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.img);
        }


        holder.img.setImageResource(R.drawable.ic_launcher_background);

        holder.txt_name.setText(recepie.getName());
        holder.txt_id.setText(recepie.getId());
        holder.txt_serving.setText(recepie.getServings());
        Log.i("serving21", String.valueOf(recepie.getServings()));


        //Movies movie = mMovieList.get(position);
        //holder.enterName.setText(movie.getMovieName());


    }

    @Override
    public int getItemCount() {
        return mRecepies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView txt_id, txt_serving, txt_name;


        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            txt_id = itemView.findViewById(R.id.txt_id);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_serving = itemView.findViewById(R.id.txt_serving);


        }
    }


}





