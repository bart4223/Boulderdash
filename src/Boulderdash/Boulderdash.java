package Boulderdash;

import Uniplay.Base.NGUniplayObject;
import Uniplay.NGGameEngine;
import Uniwork.Base.NGObjectRequestItem;
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
    protected GameFieldStageController FGameFieldController;
    protected Stage FGameControlStage;
    protected GameControlStageController FGameControlController;
    protected Properties FConfiguration;

    protected void CreateGameControlStage(){
        FGameControlStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("GameControlStage.fxml"));
        try {
            lXMLLoader.load();
            FGameControlController = (GameControlStageController)lXMLLoader.getController();
            FGameControlController.Game = this;
            Parent lRoot = lXMLLoader.getRoot();
            FGameControlStage.setTitle("Boulderdash.Control");
            FGameControlStage.setScene(new Scene(lRoot, 800, 50, Color.LIGHTGRAY));
            FGameControlStage.setResizable(false);
        }
        catch( Exception e) {
            e.printStackTrace();
        }
    }

    protected void CreateGameFieldStage(){
        FGameFieldStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("GameFieldStage.fxml"));
        try {
            lXMLLoader.load();
            FGameFieldController = (GameFieldStageController)lXMLLoader.getController();
            FGameFieldController.Game = this;
            Parent lRoot = lXMLLoader.getRoot();
            FGameFieldStage.setTitle("Boulderdash.Field");
            FGameFieldStage.setScene(new Scene(lRoot, 800, 800, Color.WHITE));
            FGameFieldStage.setResizable(false);
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
            e.printStackTrace();
        }
    }

    protected void perfectLayout() {
        FGameControlStage.setX(1400);
        FGameControlStage.setY(150);
        FGameFieldStage.setX(1400);
        FGameFieldStage.setY(250);
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

    protected int getGameFieldGridSize() {
        return Integer.parseInt(FConfiguration.getProperty("GameFieldGridSize"));
    }

    protected Boolean getShowGameFieldGrid() {
        return Boolean.valueOf(FConfiguration.getProperty("ShowGameFieldGrid"));
    }

    protected void RenderStages() {
        FGameFieldController.Render();
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
        perfectLayout();
    }

    public void Show() {
        FGameControlStage.show();
        FGameFieldStage.show();
        RenderStages();
    }

    public void Run() {
        FGameEngine.Startup();
    }

    public void Stop() {
        FGameEngine.Shutdown();
    }

    // ToDo
    public void Test() {
        NGObjectRequestItem request = new NGObjectRequestItem("TestModule", "Test");
        FGameEngine.Invoke(request);
    }

}
