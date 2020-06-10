package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Data;
import model.users.AdvertisementCompany;
import model.users.GeneralManager;
import model.users.StationManager;
import model.users.UserAccount;

public class MainController extends Application {

    public static final String loginPath = "../view/login.fxml";
    public static final String advertiserPath = "../view/advertiser.fxml";
    public static final String viewerPath = "../view/viewer.fxml";
    public static final String stationManagerPath = "../view/stationManager.fxml";
    public static final String generalManagerPath = "../view/generalManager.fxml";
    public static final String programEditorPath = "../view/programEditor.fxml";
    public static void main(String[] args) {
        launch(args);
    }

    public static String getScene(){
        UserAccount user = Data.getInstance().currentUser;
        if (user instanceof AdvertisementCompany) return advertiserPath;
        if (user instanceof GeneralManager) return generalManagerPath;
        if (user instanceof StationManager) return stationManagerPath;
        return loginPath;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Tv");
        Parent root = FXMLLoader.load(getClass().getResource(loginPath));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
