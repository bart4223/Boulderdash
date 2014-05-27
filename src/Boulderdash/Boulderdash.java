package Boulderdash;

import Uniplay.Base.NGUniplayObject;
import Uniplay.NGGameEngine;
import Uniwork.Misc.NGLogEvent;
import Uniwork.Misc.NGLogEventListener;
import Uniwork.Misc.NGStrings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class Boulderdash extends NGUniplayObject implements NGLogEventListener {

    public static final String FMT_DATETIME = "YYYY/MM/dd HH:mm:ss";

    protected NGGameEngine FGameEngine;
    protected Stage FGameFieldStage;
    protected GameFieldController FGameFieldController;
    protected Stage FGameControlStage;
    protected GameControlController FGameControlController;
    protected Stage FGameConsoleStage;
    protected GameConsoleController FGameConsoleController;
    protected Properties FConfiguration;
    protected Boolean ShowConsoleStage;

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
            FGameFieldStage.setX(1800);
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
            FGameControlStage.setScene(new Scene(lRoot, 700, 100, Color.LIGHTGRAY));
            FGameControlStage.setResizable(false);
            FGameControlStage.setX(1030);
            FGameControlStage.setY(250);
        }
        catch( Exception e) {
            e.printStackTrace();
        }
    }

    protected void CreateGameConsoleStage(){
        FGameConsoleStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("GameConsoleStage.fxml"));
        try {
            lXMLLoader.load();
            FGameConsoleController = (GameConsoleController)lXMLLoader.getController();
            FGameConsoleController.Game = this;
            Parent lRoot = lXMLLoader.getRoot();
            FGameConsoleStage.setTitle("Boulderdash-Console");
            FGameConsoleStage.setScene(new Scene(lRoot, 700, 200, Color.LIGHTGRAY));
            FGameConsoleStage.setResizable(false);
            FGameConsoleStage.setX(1030);
            FGameConsoleStage.setY(500);
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
            ShowConsoleStage = Boolean.valueOf(FConfiguration.getProperty("ShowConsoleStage"));
        }
        catch ( Exception e) {
            writeLog(e.getMessage());
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

    protected void writeLog(String aText) {
        FGameConsoleController.addLog(NGStrings.addString(NGStrings.getDateAsString(new Date(), FMT_DATETIME), aText, " "));
    }

    public Boulderdash() {
        super();
        FGameEngine = new NGGameEngine(this);
        FGameEngine.addLogListener(this);
        FConfiguration = new Properties();
        ShowConsoleStage = false;
    }

    public void Initialize() {
        CreateGameControlStage();
        CreateGameFieldStage();
        CreateGameConsoleStage();
        LoadConfiguration();
    }

    public void Show() {
        FGameControlStage.show();
        FGameFieldStage.show();
        if (ShowConsoleStage) {
            FGameConsoleStage.show();
        }
    }

    public void Run() {
        FGameEngine.Startup();
    }

    public void Stop() {
        FGameEngine.Shutdown();
    }

    @Override
    public void handleAddLog(NGLogEvent e) {
        FGameConsoleController.addLog(e.LogEntry.GetFullAsString(FMT_DATETIME, false));
    }

    @Override
    public void handleClearLog() {
        FGameConsoleController.clearLog();
    }

}
