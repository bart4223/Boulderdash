package Boulderdash;

import Uniplay.NGGameEngine;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Main extends Application {

    protected NGGameEngine FGameEngine;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FGameEngine = new NGGameEngine(null);
        Properties configuration = new Properties();
        InputStream is = new FileInputStream("resources/config.bd");
        configuration.load(is);
        FGameEngine.setConfigurationFilename(configuration.getProperty("UniplayConfigurationFilename"));
        FGameEngine.Initialize();
        FGameEngine.Startup();
    }

    @Override
    public void stop() throws Exception {
        FGameEngine.Shutdown();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
