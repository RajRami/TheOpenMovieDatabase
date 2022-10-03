package com.example.theopenmoviedatabase;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class APIUtility {

    /**
     * We will use GSON to parse JSON object
     * This method will read the file and parse out the JSON object to java object
     */
    public static APIResponse getMoviesFromJSONFile(String fileName){

        //Create a GSON object to parse JSON object
        Gson gson = new Gson();
        APIResponse response = null;

        try(
                //open the .json file from local disk
                FileReader fileReader = new FileReader(fileName);
                JsonReader jsonReader = new JsonReader(fileReader);
                ){
            //Convert JSON object to APIResponse object
            response = gson.fromJson(jsonReader, APIResponse.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    /**
     * This method will call the OMDB API and write to a JSON file
     */
    public static APIResponse getMoviesFromAPIAndWriteToFile(String searchTerm){

        searchTerm = searchTerm.replace(" ", "%20");
        String APIKey = "5a933b07";
        String uri = "http://www.omdbapi.com/?apikey=" + APIKey + "&s=" + searchTerm;

        //Creating an HttpClient object
        HttpClient httpClient = HttpClient.newHttpClient();

        //Creating an HttpRequest object
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        try{
            //This will call the API and write the result to the file called "javaFetchedApi"
            HttpResponse<Path> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofFile(Paths.get("javaFetchedApi.json")));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return getMoviesFromJSONFile("javaFetchedApi.json");
    }

    /**
     * This method will call the OMDB API and parse out the JSON object without writing to a file system
     */
    public static APIResponse getMoviesFromOMDBAPI(String searchTerm){

        searchTerm = searchTerm.replace(" ", "%20");
        String APIKey = "5a933b07";
        String uri = "http://www.omdbapi.com/?apikey=" + APIKey + "&s=" + searchTerm;

        //Creating an HttpClient object
        HttpClient httpClient = HttpClient.newHttpClient();

        //Creating an HttpRequest object
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        try{
            //This will call the API and write the result to the file called "javaFetchedApi"
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            return gson.fromJson(httpResponse.body(), APIResponse.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Thus method will return a MovieDetails object for one particular movie based on imdbID
     */
    public static MovieDetails getMovieDetailsFromOMDB(String imdbID){

        String APIKey = "5a933b07";
        String uri = "http://www.omdbapi.com/?apikey=" + APIKey + "&i=" + imdbID;

        //Creating an HttpClient object
        HttpClient httpClient = HttpClient.newHttpClient();

        //Creating an HttpRequest object
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        try{
            //This will call the API and write the result to the file called "javaFetchedApi"
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            return gson.fromJson(httpResponse.body(), MovieDetails.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
