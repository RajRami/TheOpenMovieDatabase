package com.example.theopenmoviedatabase;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DetailsViewController {

    @FXML
    private ListView<String> actorsListView;

    @FXML
    private Label genre;

    @FXML
    private Label movieTitle;

    @FXML
    private ImageView imageView;

    @FXML
    private Label languages;

    @FXML
    private ListView<Rating> ratingsListView;

    @FXML
    private Label releaseDate;

    @FXML
    private Label runtime;

    @FXML
    private Label writers;

    /**
     * This method will load a movieID
     */
    public void loadMovieDetails(String imdbID)
    {
        MovieDetails movieDetails = APIUtility.getMovieDetailsFromOMDB(imdbID);
        movieTitle.setText(movieDetails.getTitle());
        releaseDate.setText(movieDetails.getReleaseDate());
        runtime.setText(movieDetails.getRuntime());
        genre.setText(movieDetails.getGenre());
        writers.setText(movieDetails.getWriter());
        languages.setText(movieDetails.getLanguage());
        try {
            imageView.setImage(new Image(movieDetails.getPosterURL()));
        }
        catch (IllegalArgumentException e){
            imageView.setImage(new Image("https://trailerfailure.com/img/images/missingCoverPhoto.jpg"));
        }
        String[] actors = movieDetails.getActors().split(", ");
        actorsListView.getItems().addAll(actors);
        ratingsListView.getItems().addAll(movieDetails.getRatings());
    }
}
