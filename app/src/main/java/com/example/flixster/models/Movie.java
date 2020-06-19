package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

//Movie Model
@Parcel
public class Movie {

    //Stores information related to the movie that can be obtained from the JSON object.
    int movieID;
    String backdropPath;
    String posterPath;
    String title;
    String overview;
    double rating;
    String date;
    //JSONArray genre;
    //String genres;

    //Empty constructor needed by the parceler library
    public Movie(){

    }

    //Gets the desired information from a JSON object
    public Movie(JSONObject jsonObject) throws JSONException {
        backdropPath= jsonObject.getString("backdrop_path");
        posterPath= jsonObject.getString("poster_path");
        title= jsonObject.getString("title");
        overview= jsonObject.getString("overview");
        rating=jsonObject.getDouble("vote_average");
        date= jsonObject.getString("release_date");
        movieID= jsonObject.getInt("id");
        //genre= jsonObject.getJSONArray("genre_ids");
    }

    //Turns our JSON array into a list of movies
    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {

        //Iterate through the JSON Array and construct a movie for each element.

        List<Movie>movies = new ArrayList<>();
        for(int i=0; i< movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    // Getters to get information

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getBackdropPath(){
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() { return rating; }

    public String getDate() { return date; }

    public int getMovieID() { return movieID; }
}
