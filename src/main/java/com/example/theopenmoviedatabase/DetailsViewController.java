package com.example.theopenmoviedatabase;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class DetailsViewController {

    @FXML
    private ListView<?> actorsListView;

    @FXML
    private Label genre;

    @FXML
    private Label headingLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label languages;

    @FXML
    private ListView<?> ratingsListView;

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
        System.out.println("The movie ID pass into the controller is: "+imdbID);
    }
}
