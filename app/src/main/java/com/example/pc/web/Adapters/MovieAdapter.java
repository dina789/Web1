package com.example.pc.web.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.web.Models.MovieModel;
import com.example.pc.web.R;
import com.squareup.picasso.Picasso;

/**
 * Created by System.Life on 10/1/2017.
 */

public class MovieAdapter extends ArrayAdapter<MovieModel>{


    public MovieAdapter(@NonNull Context context, @NonNull MovieModel[] objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.movie_row,parent,false);
        }

        MovieModel movieModel=getItem(position);
        ImageView image=convertView.findViewById(R.id.imageView);
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w500/"+movieModel.getPoster_path()).into(image);
        TextView title=(TextView) convertView.findViewById(R.id.titleView);
        TextView overView=(TextView) convertView.findViewById(R.id.overView);
        title.setText(movieModel.getTitle());
        overView.setText(movieModel.getOverview());

        return convertView;
    }
}
