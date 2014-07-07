package Boulderdash;

import Uniplay.Base.NGUniplayComponent;
import Uniplay.Base.NGUniplayObject;
import Uniwork.Base.NGObjectRequestInvoker;
import Uniwork.Base.NGObjectRequestItem;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StageManager extends NGUniplayComponent {

    protected Integer FGameFieldGridSize;
    protected Boolean FShowGameFieldGrid;
    protected Stage FGameFieldStage;
    protected GameFieldStageController FGameFieldController;
    protected Stage FGameControlStage;
    protected GameControlStageController FGameControlController;

    protected void CreateControlStage(){
        FGameControlStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("GameControlStage.fxml"));
        try {
            lXMLLoader.load();
            FGameControlController = lXMLLoader.getController();
            FGameControlController.Manager = this;
            Parent lRoot = lXMLLoader.getRoot();
            FGameControlStage.setTitle("Boulderdash.Control");
            FGameControlStage.setScene(new Scene(lRoot, 800, 50, Color.LIGHTGRAY));
            FGameControlStage.setResizable(false);
        }
        catch( Exception e) {
            writeError("CreateControlStage", e.getMessage());
        }
    }

    protected void CreateGameFieldStage(){
        FGameFieldStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("GameFieldStage.fxml"));
        try {
            lXMLLoader.load();
            FGameFieldController = lXMLLoader.getController();
            FGameFieldController.Manager = this;
            Parent lRoot = lXMLLoader.getRoot();
            FGameFieldStage.setTitle("Boulderdash.Field");
            FGameFieldStage.setScene(new Scene(lRoot, 800, 800, Color.WHITE));
            FGameFieldStage.setResizable(false);
        }
        catch( Exception e) {
            writeError("CreateGameFieldStage", e.getMessage());
        }
    }

    protected void perfectLayout() {
        FGameControlStage.setX(1400);
        FGameControlStage.setY(150);
        FGameFieldStage.setX(1400);
        FGameFieldStage.setY(250);
    }

    protected int getGameFieldGridSize() {
        return FGameFieldGridSize;
    }

    protected Boolean getShowGameFieldGrid() {
        return FShowGameFieldGrid;
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        CreateControlStage();
        CreateGameFieldStage();
        LoadConfiguration();
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        FGameFieldGridSize = Integer.parseInt(getConfigurationProperty("GameFieldGridSize"));
        FShowGameFieldGrid = Boolean.valueOf(getConfigurationProperty("ShowGameFieldGrid"));
    }

    protected NGObjectRequestInvoker getInvoker() {
        return (NGObjectRequestInvoker)ResolveObject(NGObjectRequestInvoker.class);
    }

    public StageManager(NGUniplayObject aOwner, String aName) {
        super(aOwner, aName);
        FGameFieldGridSize = 16;
        FShowGameFieldGrid = false;
    }

    public void showStages() {
        perfectLayout();
        FGameFieldController.RenderScene();
        FGameControlStage.show();
        FGameFieldStage.show();
    }

    public Canvas getGameFieldCanvas() {
        return FGameFieldController.Layer1;
    }

    // ToDo
    public void Test() {
        getInvoker().Invoke(new NGObjectRequestItem("TestModule", "Test"));
    }

}
