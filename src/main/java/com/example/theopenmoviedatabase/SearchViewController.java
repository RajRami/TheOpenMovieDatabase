package com.example.theopenmoviedatabase;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchViewController implements Initializable {

    @FXML
    private Label headingLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private ListView<Movie> resultView;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private void searchMovies(){
        APIResponse apiResponse = APIUtility.getMoviesFromOMDBAPI(searchTextField.getText());
        resultView.getItems().addAll(apiResponse.getSearch());
    }


    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldMovieSelected, newMovieSelected) -> {
            try {
                imageView.setImage(new Image(newMovieSelected.getPoster()));
            }
            catch (IllegalArgumentException e){
                imageView.setImage(new Image("https://trailerfailure.com/img/images/missingCoverPhoto.jpg"));
            }
        }));
    }
}
