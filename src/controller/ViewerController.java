package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Channel;
import model.programs.Rateable;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewerController extends Controller {
    @FXML public ChoiceBox<Channel> channelSelector;
    @FXML public ListView<Rateable> programList;
    @FXML public TextField rating;

    @FXML
    public void rate(ActionEvent actionEvent){
        try{
            int ratingNum = Integer.parseInt(rating.getText());
            programList.getSelectionModel().getSelectedItem().rate(ratingNum);
            showMessage("You rated "+programList.getSelectionModel().getSelectedItem()+" "+ ratingNum+"/100.");
        } catch (NumberFormatException p){
            showError("Please enter a valid number.");
        } catch (NullPointerException e){
            showError("No program selected.");
        } catch (IllegalArgumentException e){
            showError(e.getMessage());
        }
    }

    private void updateSelectedChannel(){
        programList.getItems().setAll(channelSelector.getValue().getRateables());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        channelSelector.getItems().addAll(data.allChannels);
        channelSelector.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->updateSelectedChannel());
    }
}
