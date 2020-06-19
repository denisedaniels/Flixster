package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//Movie Model
public class Movie {

    //Stores information related to the movie that can be obtained from the JSON object.
    String posterPath;
    String title;
    String overview;

    //Gets the desired information from a JSON object
    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath= jsonObject.getString("poster_path");
        title= jsonObject.getString("title");
        overview= jsonObject.getString("overview");
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

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
