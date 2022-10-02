package com.example.theopenmoviedatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
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
    private Label numberOfMovies;
    @FXML
    private Button searchButton;
    @FXML
    private Button getDetails;
    @FXML
    private TextField searchTextField;

    @FXML
    private void searchMovies(){
        APIResponse apiResponse = APIUtility.getMoviesFromOMDBAPI(searchTextField.getText());
        resultView.getItems().clear();
        resultView.getItems().addAll(apiResponse.getSearch());
        numberOfMovies.setText("Number of Movies: " + resultView.getItems().size());
    }


    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getDetails.setVisible(false);
        resultView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldMovieSelected, newMovieSelected) -> {
            getDetails.setVisible(true);
            try {
                imageView.setImage(new Image(newMovieSelected.getPoster()));
            }
            catch (IllegalArgumentException e){
                imageView.setImage(new Image("https://trailerfailure.com/img/images/missingCoverPhoto.jpg"));
            }
        }));
    }

    /**
     * This method will pass an imdbID to the DetailsView controller
     */
    @FXML
    private void getDetails(ActionEvent event) throws IOException {
        String imdbID = resultView.getSelectionModel().getSelectedItem().getImdbID();
        System.out.println("Movie ID: "+imdbID);
        SceneChanger.changeScenes(event, "details-view.fxml",imdbID);
    }
}
