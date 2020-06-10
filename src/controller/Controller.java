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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Data;
import model.users.UserAccount;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class Controller implements Initializable{

    @FXML public Label message;
    protected Data data;
    private PauseTransition messageTransition;

    @FXML public void logout(ActionEvent actionEvent) throws IOException{
        Data.getInstance().currentUser = null;
        loadStage(actionEvent,MainController.loginPath);
    }

    public void loadStage(ActionEvent actionEvent, String path) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void loadAccount(ActionEvent actionEvent ,UserAccount user) throws IOException{
        Data.getInstance().currentUser = user;
        loadStage(actionEvent, MainController.getScene());
    }

    public void showMessage(String text){
        message.setTextFill(Color.web("#000000"));
        showText(text);
    }

    public void showError(String text){
        message.setTextFill(Color.web("#d00000"));
        showText(text);
    }

    private void showText(String text){
        message.setText(text);
        message.setVisible(true);
        messageTransition.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = Data.getInstance();
        messageTransition = new PauseTransition(Duration.seconds(2));
        messageTransition.setOnFinished(event ->message.setVisible(false));
    }
}
