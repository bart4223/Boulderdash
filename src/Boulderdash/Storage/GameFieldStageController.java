package Boulderdash.Storage;

import Uniwork.Visuals.NGGrid2DDisplayController;
import Uniwork.Visuals.NGStageController;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class GameFieldStageController extends NGStageController {

    protected NGGrid2DDisplayController FDCGrid;

    public Boulderdash Game;

    @FXML
    public Canvas Layer0;

    @FXML
    public Canvas LayerBack;

    @FXML
    public Canvas LayerBender;

    @FXML
    public Canvas LayerFront;

    @Override
    protected void CreateDisplayController() {
        super.CreateDisplayController();
        FDCGrid = new NGGrid2DDisplayController(Layer0);
        FDCGrid.DrawGrid = false;
        FDCGrid.GridColor = Color.DARKGRAY;
        registerDisplayController(FDCGrid);
    }

    @Override
    protected void DoBeforeRenderScene() {
        super.DoBeforeRenderScene();
        FDCGrid.DrawGrid = Game.getShowGameFieldGrid();
        FDCGrid.GridDistance = Game.getGameFieldGridSize();
    }

}
