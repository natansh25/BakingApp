package com.example.natan.project3take1.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.natan.project3take1.MainActivity;

import java.util.ArrayList;

/**
 * Created by natan on 12/16/2017.
 */

public class Recepie implements Parcelable {

private String id;
private String name;
//private ArrayList<Ingredients> mIngredients;
//private ArrayList<Steps> mSteps;
private String servings;
private String image;

    public Recepie(String id, String name, String servings, String image) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.servings);
        dest.writeString(this.image);
    }

    protected Recepie(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.servings = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Recepie> CREATOR = new Parcelable.Creator<Recepie>() {
        @Override
        public Recepie createFromParcel(Parcel source) {
            return new Recepie(source);
        }

        @Override
        public Recepie[] newArray(int size) {
            return new Recepie[size];
        }
    };
}
