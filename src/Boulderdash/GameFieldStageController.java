package Boulderdash;

import Uniwork.Visuals.NGGrid2DDisplayController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class GameFieldStageController implements Initializable {

    protected NGGrid2DDisplayController FDCLayerGrid;

    @FXML
    public Canvas Layer0;

    @FXML
    public Canvas LayerGrid;

    public Boulderdash Game;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FDCLayerGrid = new NGGrid2DDisplayController(LayerGrid);
        FDCLayerGrid.GridColor = Color.BLACK;
        FDCLayerGrid.Initialize();
    }

    public void Render() {
        FDCLayerGrid.DrawGrid = Game.getShowGameFieldGrid();
        FDCLayerGrid.GridDistance = Game.getGameFieldGridSize();
        FDCLayerGrid.Render();
    }

}
