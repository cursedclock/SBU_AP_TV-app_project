package controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Channel;
import model.Data;
import model.users.AdvertisementCompany;
import model.util.Schedule;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class AdvertiserController extends Controller implements Initializable {
    @FXML public ListView<Channel> channelSelector;
    @FXML public Label welcome;
    @FXML public Label balance;
    @FXML public TextField adName;
    @FXML public TextField year;
    @FXML public TextField month;
    @FXML public TextField day;
    @FXML public TextField endhour;
    @FXML public TextField endminute;
    @FXML public TextField starthour;
    @FXML public TextField startminute;
    @FXML public TextField amount;
    AdvertisementCompany user;

    @FXML public void sendRequest(ActionEvent actionEvent){
        try {
            String date = String.format("%s-%s-%s", year.getText(),month.getText(),day.getText());
            String time = starthour.getText()+":"+ startminute.getText();
            String duration = endhour.getText()+":"+ endminute.getText();
            Schedule schedule = new Schedule(date, time, duration);
            user.requestAd(adName.getText(),schedule);
            showMessage("Ad request added successfully!");
            adName.clear();
        } catch (IllegalArgumentException e){
            showError(e.getMessage());
        } catch (NullPointerException e){
            showError("Please select a channel.");
        }
        clearSchedule();
    }

    @FXML public void deposit(){
        try{
            int sum = Integer.parseInt(amount.getText());
            user.deposit(sum);
            showMessage("Deposit successful.");
            loadBalance();
        } catch (NumberFormatException e){
            showError("Please enter a valid number.");
        }
        amount.clear();
    }

    private void loadBalance(){
        balance.setText("Balance: "+user.getBalance());
    }

    private void clearSchedule(){
        year.clear();
        month.clear();
        day.clear();
        starthour.clear();
        startminute.clear();
        endhour.clear();
        endminute.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        user = (AdvertisementCompany)Data.getInstance().currentUser;
        welcome.setText("Welcome "+user.getUsername()+"!");
        loadBalance();
        channelSelector.getItems().addAll(Data.getInstance().allChannels);
        channelSelector.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->user.watching=newValue);
    }
}
