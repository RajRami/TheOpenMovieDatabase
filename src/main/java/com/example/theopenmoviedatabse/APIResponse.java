package com.example.theopenmoviedatabse;

import com.google.gson.annotations.SerializedName;

public class APIResponse {

    //@SerializedName is the name that is shown in the JSON file, we can name our variable as per naming convention
    @SerializedName("Search")
    private Movie[] search;

    private String totalResults;

    @SerializedName("Response")
    private String response;

    //Getters
    public Movie[] getSearch() {
        return search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return response;
    }
}
