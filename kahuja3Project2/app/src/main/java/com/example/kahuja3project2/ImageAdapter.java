package com.example.kahuja3project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    ArrayList<String> movieName;
    ArrayList<Integer> movieImage;
    ArrayList<String> movieYear;

    public ImageAdapter(@NonNull Context context, @NonNull ArrayList<String> movieNames, ArrayList<Integer> movieImages,ArrayList<String> movieYears) {
        this.context=context;
        this.movieName = movieNames;
        this.movieImage = movieImages;
        this.movieYear = movieYears;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.movielayout,parent,false);
            ImageView moviePicture = (ImageView) convertView.findViewById(R.id.movieImage);
            TextView movieName1 = (TextView) convertView.findViewById(R.id.movieName);
            TextView movieYear1 = (TextView) convertView.findViewById(R.id.movieyear);
            movieName1.setText(movieName.get(position));
            moviePicture.setImageResource(movieImage.get(position));
            movieYear1.setText(movieYear.get(position));
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return movieName.size();
    }

    @Override
    public Object getItem(int i) {
        return movieName.get(i);
    }

    @Override
    public long getItemId(int i) {
        return movieImage.get(i);
    }
}
