package Boulderdash;

import Uniplay.Base.NGUniplayObject;
import Uniplay.NGGameEngine;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Boulderdash extends NGUniplayObject {

    protected NGGameEngine FGameEngine;
    protected Stage FGameFieldStage;
    protected Stage FGameControlStage;
    protected GameFieldController FGameFieldController;
    protected GameControlController FGameControlController;
    protected Properties FConfiguration;

    protected void CreateGameFieldStage(){
        FGameFieldStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("GameFieldStage.fxml"));
        try {
            lXMLLoader.load();
            FGameFieldController = (GameFieldController)lXMLLoader.getController();
            FGameFieldController.Game = this;
            Parent lRoot = lXMLLoader.getRoot();
            FGameFieldStage.setTitle("Boulderdash-Field");
            FGameFieldStage.setScene(new Scene(lRoot, 500, 500, Color.WHITE));
            FGameFieldStage.setResizable(false);
            FGameFieldStage.setX(1750);
            FGameFieldStage.setY(250);
        }
        catch( Exception e) {
            e.printStackTrace();
        }
    }

    protected void CreateGameControlStage(){
        FGameControlStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("GameControlStage.fxml"));
        try {
            lXMLLoader.load();
            FGameControlController = (GameControlController)lXMLLoader.getController();
            FGameControlController.Game = this;
            Parent lRoot = lXMLLoader.getRoot();
            FGameControlStage.setTitle("Boulderdash-Control");
            FGameControlStage.setScene(new Scene(lRoot, 500, 100, Color.LIGHTGRAY));
            FGameControlStage.setResizable(false);
        }
        catch( Exception e) {
            e.printStackTrace();
        }
    }

    protected void LoadConfiguration() {
        try {
            InputStream is = new FileInputStream("resources/config.bd");
            FConfiguration.load(is);
            FGameEngine.setConfigurationFilename(FConfiguration.getProperty("UniplayConfigurationFilename"));
        }
        catch ( Exception e) {
            // ToDo
            //writeLog(e.getMessage());
        }
    }

    @Override
    protected Object DoResolveObject(String aName, Class aClass) {
        Object result = super.DoResolveObject(aName, aClass);
        if (result == null) {
            if (aName.equals("GameFieldController.Layer0") && aClass.isAssignableFrom(FGameFieldController.Layer0.getClass())) {
                return FGameFieldController.Layer0;
            }
        }
        return result;
    }

    public Boulderdash() {
        super();
        FGameEngine = new NGGameEngine(this);
        FConfiguration = new Properties();
    }

    public void Initialize() {
        CreateGameControlStage();
        CreateGameFieldStage();
        LoadConfiguration();
    }

    public void Show() {
        FGameControlStage.show();
        FGameFieldStage.show();
    }

    public void Run() {
        FGameEngine.Startup();
    }

    public void Stop() {
        FGameEngine.Shutdown();
    }

}
