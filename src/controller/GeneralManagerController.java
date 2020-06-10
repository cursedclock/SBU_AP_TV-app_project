package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Channel;
import model.Data;
import model.programs.Advertisement;
import model.programs.Rateable;
import model.users.GeneralManager;
import model.users.StationManager;
import model.util.AdCalculator;
import java.net.URL;
import java.util.ResourceBundle;

public class GeneralManagerController extends Controller{

    @FXML public ChoiceBox<Channel> channelSelector;
    @FXML public ChoiceBox<StationManager> managerSelector;
    @FXML public ChoiceBox<AdCalculator> calculatorSelector;
    @FXML public ListView<Rateable> programSelector;
    @FXML public ListView<Advertisement> adSelector;
    @FXML public TextField channelName;
    @FXML public TextField managerUsername;
    @FXML public PasswordField managerPassword;
    @FXML public Label welcome;
    GeneralManager user;

    @FXML void add(ActionEvent event) {

    }

    @FXML void replace(ActionEvent event) {

    }

    @FXML void remove(ActionEvent event) {
        try {
            user.removeProgram(channelSelector.getValue(),programSelector.getSelectionModel().getSelectedItem());
            showMessage("Program removed successfully!");
            updateChannelSelection();
        } catch (NullPointerException e){
            showError("Please select a channel.");
        } catch (RuntimeException e){
            showError(e.getMessage());
        }
    }

    @FXML void addChannel(ActionEvent event) {
        if (channelName.getText().isEmpty()){
            showError("Please enter a channel name.");
        } else if(calculatorSelector.getValue()==null) {
            showError("Please select an ad fee calculator.");
        } else {
            user.addChannel(channelName.getText(), calculatorSelector.getValue());
            channelName.clear();
            loadChannels();
            showMessage("Channel added successfully");
        }
    }

    @FXML void addManager(ActionEvent event) {
        if (managerPassword.getText().isEmpty() || managerPassword.getText().isEmpty()){
            showError("Please enter a valid username and password.");
            return;
        }
        try{
            user.addManager(managerUsername.getText(),managerPassword.getText());
            showMessage("Manager added successfully.");
            loadManagers();
        } catch (RuntimeException e){
            showMessage(e.getMessage());
        }
        managerPassword.clear();
        managerUsername.clear();
    }

    @FXML void approve(ActionEvent event) {
        try {
            user.approveAd(channelSelector.getValue(),adSelector.getSelectionModel().getSelectedItem());
            updateChannelSelection();
            showMessage("Advertisement approved successfully.");
        } catch (NullPointerException e){
            showError("Please select a channel.");
        } catch (RuntimeException e){
            showError(e.getMessage());
        }
    }

    @FXML void decline(ActionEvent event) {
        try {
            if(user.declineAd(channelSelector.getValue(),adSelector.getSelectionModel().getSelectedItem())) {
                updateChannelSelection();
                showMessage("Ad request removed successfully.");
            } else{
                showError("Please select a request.");
            }
        } catch (NullPointerException e){
            showError("Please select a channel.");
        }
    }

    @FXML void setManager(ActionEvent event) {
        try {
            user.setManagement(channelSelector.getValue(), managerSelector.getValue());
            showMessage("New manager set successfully.");
        } catch (NullPointerException e){
            showError(e.getMessage());
        }
    }

    private void loadManagers(){
        managerSelector.getItems().setAll(user.getManagers());
    }

    private void updateChannelSelection(){
        if (channelSelector.getValue()!=null) {
            adSelector.getItems().setAll(user.getAdRequests(channelSelector.getValue()));
            programSelector.getItems().setAll(channelSelector.getValue().getRateables());
        } else {
            adSelector.getItems().clear();
            programSelector.getItems().clear();
        }
    }

    private void loadChannels(){
        channelSelector.getItems().setAll(data.allChannels);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        user = (GeneralManager)Data.getInstance().currentUser;
        welcome.setText("Welcome "+user.getUsername()+"!");
        loadManagers();
        loadChannels();
        channelSelector.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->updateChannelSelection());
        calculatorSelector.getItems().setAll(data.adCalculators);
    }
}
