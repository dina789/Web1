package com.example.pc.web;

import android.app.NotificationChannel;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pc.web.Models.MovieModel;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent=getIntent();

        MovieModel movieModel=(MovieModel) getIntent().getSerializableExtra("movieModel");

        TextView textView=(TextView) findViewById(R.id.textView);
        TextView textView2=(TextView) findViewById(R.id.textView2);
        ImageView imageView=(ImageView) findViewById(R.id.imageView2);
        RatingBar ratingBar=(RatingBar) findViewById(R.id.ratingBar);


        textView.setText(movieModel.getTitle());
        textView2.setText(movieModel.getOverview());
        float rate=(float) movieModel.getVote_average();
        rate=rate/2;
        ratingBar.setRating(rate);
        Picasso.with(getBaseContext()).load("http://image.tmdb.org/t/p/w500/"+movieModel.getPoster_path()).into(imageView);


        //((TextView) findViewById(R.id.text)).setText(item.getDescription());
    }
}
