package Boulderdash;

import Uniplay.Storage.NG2DGame;
import Uniplay.Storage.NGGameManager;
import Uniwork.Base.NGObjectRequestInvoker;
import Uniwork.Base.NGObjectRequestItem;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Boulderdash extends NG2DGame {

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
            FGameControlController.Game = this;
            FGameControlController.Initialize();
            Parent lRoot = lXMLLoader.getRoot();
            FGameControlStage.setTitle("Boulderdash.Control");
            FGameControlStage.setScene(new Scene(lRoot, 800, 50, Color.LIGHTGRAY));
            FGameControlStage.setResizable(false);
        }
        catch (Exception e) {
            writeError("CreateControlStage", e.getMessage());
        }
    }

    protected void CreateGameFieldStage(){
        FGameFieldStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("GameFieldStage.fxml"));
        try {
            lXMLLoader.load();
            FGameFieldController = lXMLLoader.getController();
            FGameFieldController.Game = this;
            FGameFieldController.Initialize();
            Parent lRoot = lXMLLoader.getRoot();
            FGameFieldStage.setTitle("Boulderdash.Field");
            FGameFieldStage.setScene(new Scene(lRoot, 800, 800, Color.WHITE));
            FGameFieldStage.setResizable(false);
        }
        catch (Exception e) {
            writeError("CreateGameFieldStage", e.getMessage());
        }
    }

    protected void perfectLayout() {
        FGameControlStage.setX(1400);
        FGameControlStage.setY(150);
        FGameFieldStage.setX(1400);
        FGameFieldStage.setY(250);
    }

    protected void updateGameControlControls() {
        FGameControlController.updateControls();
    }

    @Override
    protected void DoShowStages() {
        super.DoShowStages();
        perfectLayout();
        FGameFieldController.RenderScene();
        updateGameControlControls();
        FGameControlStage.show();
        FGameFieldStage.show();
    }

    protected NGObjectRequestInvoker getInvoker() {
        return (NGObjectRequestInvoker)ResolveObject(NGObjectRequestInvoker.class);
    }

    @Override
    protected void DoBeforeStart() {
        super.DoBeforeStart();
        removeAllPlayers();
        addPlayer(getPlayerManager().getCurrentPlayer());
    }

    public Boulderdash(NGGameManager aManager, String aName) {
        super(aManager, aName);
        FGameFieldGridSize = 16;
        FShowGameFieldGrid = false;
        CreateControlStage();
        CreateGameFieldStage();
    }

    public Canvas getGameFieldCanvas() {
        return FGameFieldController.Layer1;
    }

    public void setGameFieldGridSize(Integer aValue) {
        FGameFieldGridSize = aValue;
    }

    public Integer getGameFieldGridSize() {
        return FGameFieldGridSize;
    }

    public void setShowGameFieldGrid(Boolean aValue) {
        FShowGameFieldGrid = aValue;
    }

    public Boolean getShowGameFieldGrid() {
        return FShowGameFieldGrid;
    }

    // ToDo
    public void Test() {
        getInvoker().Invoke(new NGObjectRequestItem("TestModule", "Test"));
    }

}
