package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import model.Data;
import model.programs.Advertisement;
import model.programs.Rateable;
import model.users.StationManager;

import java.net.URL;
import java.util.ResourceBundle;

public class StationManagerController extends Controller {
    @FXML public ListView<Advertisement> adSelector;
    @FXML public ListView<Rateable> programSelector;
    @FXML public Label welcome;
    @FXML public Button addBtn;
    @FXML public Button removeBtn;
    @FXML public Button replaceBtn;
    @FXML public Button approveBtn;
    @FXML public Button declineBtn;
    StationManager user;

    @FXML
    public void add(ActionEvent actionEvent){
        upDateLists();
    }

    @FXML
    public void replace(ActionEvent actionEvent){
        upDateLists();
    }

    @FXML
    public void remove(ActionEvent actionEvent) {
        try {
            user.removeProgram(programSelector.getSelectionModel().getSelectedItem());
            showMessage("Program removed successfully!");
            upDateLists();
        } catch (RuntimeException e){
            showError("Please select a program.");
        }
    }

    @FXML
    public void approve(ActionEvent actionEvent){
        try {
            user.approveAd(adSelector.getSelectionModel().getSelectedItem());
            upDateLists();
            showMessage("Advertisement added successfully.");
        } catch (RuntimeException e){
            showError(e.getMessage());
        }
    }

    @FXML
    public void decline(ActionEvent actionEvent){
        try {
            if(user.declineAd(adSelector.getSelectionModel().getSelectedItem())) {
                upDateLists();
                showMessage("Ad request removed successfully.");
            }else{
                showError("Please select a request.");
            }
        } catch (NullPointerException e){
            showError("Please select a program.");
        }
    }

    private void upDateLists(){
        adSelector.getItems().setAll(user.getAdRequests());
        programSelector.getItems().setAll(user.getPrograms());
    }

    private  void disable(){
        message.setText("You are not in charge of any channel.");
        message.setTextFill(Color.web("#d00000"));
        message.setVisible(true);

        adSelector.setDisable(true);
        programSelector.setDisable(true);
        addBtn.setDisable(true);
        replaceBtn.setDisable(true);
        removeBtn.setDisable(true);
        approveBtn.setDisable(true);
        declineBtn.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = (StationManager) Data.getInstance().currentUser;
        if(user.station!=null){
            super.initialize(url, resourceBundle);
            welcome.setText("Welcome " + user.getUsername() + "!");
            upDateLists();
        } else{
            disable();
        }
    }
}
