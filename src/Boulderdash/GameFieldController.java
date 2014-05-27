package Boulderdash;

import Uniwork.Visuals.NGGrid2DDisplayController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class GameFieldController implements Initializable {

    protected NGGrid2DDisplayController FDCLayer0;

    @FXML
    public Canvas Layer0;

    @FXML
    public Canvas Layer1;

    public Boulderdash Game;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FDCLayer0 = new NGGrid2DDisplayController(Layer0);
        FDCLayer0.GridColor = Color.DARKGRAY;
        FDCLayer0.Initialize();
    }

    public void Render() {
        FDCLayer0.DrawGrid = Game.getShowGameFieldGrid();
        FDCLayer0.GridDistance = Game.getGameFieldGridSize();
        FDCLayer0.Render();
    }

}
