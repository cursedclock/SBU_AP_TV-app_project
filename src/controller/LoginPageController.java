package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Data;
import model.users.AdvertisementCompany;
import model.users.UserAccount;

import java.io.IOException;

public class LoginPageController extends Controller {
    @FXML public TextField username;
    @FXML public TextField password;


    @FXML
    void enterAsViewer(ActionEvent actionEvent) throws IOException {
        loadStage(actionEvent,MainController.viewerPath);
    }

    @FXML
    public void login(ActionEvent actionEvent) throws IOException{
        UserAccount account;
        try {
            account = data.login(username.getText(), password.getText());
            username.clear();
            loadAccount(actionEvent, account);
        } catch (RuntimeException e){
            showError(e.getMessage());
        }
        password.clear();
    }

    @FXML
    public void signup(ActionEvent actionEvent) throws IOException{
        if (username.getText().isEmpty()||password.getText().isEmpty()){
            showError("Please enter a valid username and password.");
        } else if(!Data.getInstance().checkUsernameValidity(username.getText())){
            showError("Username already exists");
        } else{
            AdvertisementCompany newCompany = new AdvertisementCompany(username.getText(),password.getText());
            data.allUsers.add(newCompany);
            loadAccount(actionEvent,newCompany);
        }
    }
}
