package Boulderdash.Storage;

import Boulderdash.Graphics.DisplayControllerNotificationArea;
import Uniwork.Visuals.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class GameFieldStageController extends NGStageController {

    protected NGGrid2DDisplayController FDCGrid;
    protected DisplayControllerNotificationArea FDCNotificationArea;
    protected NGImageDisplayController FDCBenderLeft;
    protected NGImageDisplayController FDCBenderRight;
    protected NGImageDisplayController FDCDiamond;
    protected NGMultiDigitNumberDisplayManager FDCPointsCounter;
    protected NGMultiDigitNumberDisplayManager FDCDiamondsCounter;

    public Boulderdash Game;

    @FXML
    public Canvas LayerNotifyBack;

    @FXML
    public Canvas LayerNotify;

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
        FDCBenderLeft = new NGImageDisplayController(LayerNotify, "BenderLeft", "resources/sprites/id_1.png");
        FDCBenderLeft.setPosition(4, LayerNotify.getHeight() - FDCNotificationArea.getNotifyWidth() + 4);
        registerDisplayController(FDCBenderLeft);
        FDCBenderRight = new NGImageDisplayController(LayerNotify, "BenderRight", "resources/sprites/id_1.png");
        FDCBenderRight.setPosition(LayerNotify.getWidth() - FDCNotificationArea.getNotifyWidth() + 4, LayerNotify.getHeight() - FDCNotificationArea.getNotifyWidth() + 4);
        registerDisplayController(FDCBenderRight);
        FDCPointsCounter = new NGMultiDigitNumberDisplayManager("Uniwork.Visuals.NGRetroNumberDisplayController", LayerNotify, 6);
        FDCPointsCounter.setPixelSize(4);
        FDCPointsCounter.setBackgroundColor(FDCNotificationArea.getNotifyBackColor());
        FDCPointsCounter.setPosition((FDCNotificationArea.getNotifyWidth() + 8) / 4, (LayerNotify.getHeight() - FDCNotificationArea.getNotifyWidth() + 4) / 4);
        registerDisplayController(FDCPointsCounter);
        FDCDiamond = new NGImageDisplayController(LayerNotify, "Diamond", "resources/sprites/id_2.png");
        FDCDiamond.setPosition(FDCNotificationArea.getNotifyWidth() + 328, LayerNotify.getHeight() - FDCNotificationArea.getNotifyWidth() + 4);
        registerDisplayController(FDCDiamond);
        FDCDiamondsCounter = new NGMultiDigitNumberDisplayManager("Uniwork.Visuals.NGRetroNumberDisplayController", LayerNotify, 2);
        FDCDiamondsCounter.setPixelSize(4);
        FDCDiamondsCounter.setBackgroundColor(FDCNotificationArea.getNotifyBackColor());
        FDCDiamondsCounter.setPosition((FDCNotificationArea.getNotifyWidth() + 360) / 4, (LayerNotify.getHeight() - FDCNotificationArea.getNotifyWidth() + 4) / 4);
        registerDisplayController(FDCDiamondsCounter);
    }

    @Override
    protected void DoBeforeRenderScene() {
        super.DoBeforeRenderScene();
        FDCGrid.DrawGrid = Game.getShowGameFieldGrid();
        FDCGrid.GridDistance = Game.getGameFieldGridSize();
    }

    public void setPointCounter(Integer aValue) {
        FDCPointsCounter.Count = aValue;
        FDCPointsCounter.Render();
    }

    public void setDiamondsCounter(Integer aValue) {
        FDCDiamondsCounter.Count = aValue;
        FDCDiamondsCounter.Render();
    }

}
