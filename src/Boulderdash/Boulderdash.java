package Boulderdash;

import Uniplay.Base.NGUniplayObject;
import Uniplay.NGGameEngine;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Boulderdash extends NGUniplayObject {

    protected NGGameEngine FGameEngine;
    protected Stage FGameFieldStage;
    protected GameFieldController FGameFieldController;
    protected Stage FGameControlStage;
    protected GameControlController FGameControlController;

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
            FGameControlStage.setScene(new Scene(lRoot, 500, 200, Color.LIGHTGRAY));
            FGameControlStage.setResizable(false);
        }
        catch( Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Object DoResolveObject(String aName, Class aClass) {
        Object result = super.DoResolveObject(aName, aClass);
        System.out.println(aName);
        return result;
    }

    public Boulderdash() {
        super();
        FGameEngine = new NGGameEngine(this);
        FGameEngine.setConfigurationFilename("resources/config.ucf");
    }

    public void Initialize() {
        CreateGameControlStage();
        CreateGameFieldStage();
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
