package Boulderdash;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    protected Boulderdash FBoulderdash;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FBoulderdash = new Boulderdash();
        FBoulderdash.Initialize();
        FBoulderdash.Show();
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
