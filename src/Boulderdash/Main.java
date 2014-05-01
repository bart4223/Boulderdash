package Boulderdash;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    protected Boulderdash FBoulderdash;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Boulderdash");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
        FBoulderdash = new Boulderdash();
        FBoulderdash.Run();
    }

    @Override
    public void stop() throws Exception {
        FBoulderdash.Stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
