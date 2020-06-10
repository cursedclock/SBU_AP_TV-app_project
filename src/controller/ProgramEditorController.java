package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgramEditorController extends Controller{

    @FXML public ChoiceBox<String> programType;
    @FXML public TextField name;
    @FXML public Label hostLabel;
    @FXML public TextField host;
    @FXML public TextField startTime;
    @FXML public TextField endTime;
    @FXML public Label reStartLabel;
    @FXML public TextField reStartTime;
    @FXML public Label reEndLabel;
    @FXML public TextField reEndTime;
    @FXML public Label dateLabel;
    @FXML public TextField date;
    @FXML public Label reDateLabel;
    @FXML public TextField reDate;

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void ok(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }
}
