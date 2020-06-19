package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.DetailActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    //Constructor
    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    //Inflates a layout from XML and returns the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView= LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    //Populate data into the view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder" + position);
        //Get data into the view at a specific position
        Movie movie= movies.get(position);
        //Bind the movie date in the list
        holder.bind(movie);
    }

    //Return the total amount of items
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle= itemView.findViewById(R.id.tvTitle);
            tvOverview= itemView.findViewById(R.id.tvOverview);
            ivPoster= itemView.findViewById(R.id.ivPoster);
            container= itemView.findViewById(R.id.container);
        }

        //Bind method
        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());

            // Select imageURL based on the orientation
            String imageURL;
            if(context.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
                imageURL= movie.getBackdropPath();
            }else{
                imageURL= movie.getPosterPath();
            }

            Glide.with(context)
                .load(imageURL)
                // loading image
                //.error(R.drawable.noimage)
                .into(ivPoster);

            //Register click listener on the entire container
            container.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //On click navigate to a new activity
                    Intent i = new Intent (context, DetailActivity.class);

                    //Pass in data to the activity
                    //i.putExtra("title", movie.getTitle());

                    //Pass in all the movie details
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);

                    //Indicate whether or not on click functionality works
                    //Toast.makeText(context, movie.getTitle(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
