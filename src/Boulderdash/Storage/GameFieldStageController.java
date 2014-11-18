package Boulderdash.Storage;

import Boulderdash.Graphics.DisplayControllerNotificationArea;
import Uniwork.Visuals.NGGrid2DDisplayController;
import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class GameFieldStageController extends NGStageController {

    protected NGGrid2DDisplayController FDCGrid;
    protected DisplayControllerNotificationArea FDCNotificationArea;

    public Boulderdash Game;

    @FXML
    public Canvas LayerNotifyBack;

    @FXML
    public Canvas LayerGrid;

    @FXML
    public Canvas LayerBack;

    @FXML
    public Canvas LayerBender;

    @FXML
    public Canvas LayerFront;

    @Override
    protected void CreateDisplayController() {
        super.CreateDisplayController();
        FDCGrid = new NGGrid2DDisplayController(LayerGrid);
        FDCGrid.DrawGrid = false;
        FDCGrid.GridColor = Color.DARKGRAY;
        registerDisplayController(FDCGrid);
        FDCNotificationArea = new DisplayControllerNotificationArea(LayerNotifyBack);
        registerDisplayController(FDCNotificationArea);
    }

    @Override
    protected void DoBeforeRenderScene() {
        super.DoBeforeRenderScene();
        FDCGrid.DrawGrid = Game.getShowGameFieldGrid();
        FDCGrid.GridDistance = Game.getGameFieldGridSize();
    }

}
