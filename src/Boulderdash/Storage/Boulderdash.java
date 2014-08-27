package Boulderdash.Storage;

import Boulderdash.BoulderdashConsts;
import Boulderdash.Control.*;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGCustomControlMimic;
import Uniplay.NGGameEngineConstants;
import Uniplay.Sound.NGMediaPlayerSoundItem;
import Uniplay.Sound.NGSoundManager;
import Uniplay.Storage.NG2DGame;
import Uniplay.Storage.NGGameManager;
import Uniwork.Base.NGObjectRequestCaller;
import Uniwork.Base.NGObjectRequestInvoker;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Boulderdash extends NG2DGame {

    protected Integer FGameFieldGridSize;
    protected Boolean FShowGameFieldGrid;
    protected Stage FGameFieldStage;
    protected GameFieldStageController FGameFieldController;
    protected Stage FGameControlStage;
    protected GameControlStageController FGameControlController;
    protected NGObjectRequestCaller FCaller;
    protected Boolean FPlaySound;

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
            Scene scene = new Scene(lRoot, 800, 800, Color.LIGHTGRAY);
            FGameFieldStage.setScene(scene);
            FGameFieldStage.setResizable(false);
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    handleKeyPressed(keyEvent);
                }
            });
        }
        catch (Exception e) {
            writeError("CreateGameFieldStage", e.getMessage());
        }
    }

    protected void handleKeyPressed(KeyEvent e) {
        switch (e.getCode()) {
            case DOWN:
                if (FState == State.Started) {
                    PlayerDown();
                }
                break;
            case UP:
                if (FState == State.Started) {
                    PlayerUp();
                }
                break;
            case LEFT:
                if (FState == State.Started) {
                    PlayerLeft();
                }
                break;
            case RIGHT:
                if (FState == State.Started) {
                    PlayerRight();
                }
                break;
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

    protected void playSound() {
        getSoundManager().playSound(BoulderdashConsts.SOUND_SPLASH_FEAR, NGMediaPlayerSoundItem.Mode.repetitive);
    }

    protected void stopSound() {
        getSoundManager().stopSound(BoulderdashConsts.SOUND_SPLASH_FEAR);
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        NGSoundManager soundManager = getSoundManager();
        if (soundManager != null) {
            soundManager.addSound(BoulderdashConsts.SOUND_SPLASH_FEAR, "resources/sound/fear.mp3");
        }
    }

    @Override
    protected void DoBeforeStart() {
        super.DoBeforeStart();
        if (getSoundManager() != null && FPlaySound) {
            stopSound();
        }
        removeAllPlayers();
        add2DGamePlayer(getPlayerManager().getCurrentPlayer());
    }

    @Override
    protected void DoAfterStart() {
        super.DoAfterStart();
        if (getSoundManager() != null && FPlaySound) {
            playSound();
        }
    }

    @Override
    protected Class getMemoryCellValueClass() {
        return BoulderdashMemoryCellValue.class;
    }

    @Override
    protected void registerMimicActions() {
        super.registerMimicActions();
        NGCustomControlMimic mimic;
        NGControlMimicManager manager = getMimicManager();
        // Player.Stomp
        mimic = new MimicActionPlayerStomp(manager, this, BoulderdashConsts.MIMIC_ACTION_PLAYER_STOMP, 50);
        manager.addMimic(mimic);
        // Player.Down
        mimic = new MimicActionPlayerDown(manager, this, BoulderdashConsts.MIMIC_ACTION_PLAYER_DOWN);
        manager.addMimic(mimic);
        // Player.Up
        mimic = new MimicActionPlayerUp(manager, this, BoulderdashConsts.MIMIC_ACTION_PLAYER_UP);
        manager.addMimic(mimic);
        // Player.Left
        mimic = new MimicActionPlayerLeft(manager, this, BoulderdashConsts.MIMIC_ACTION_PLAYER_LEFT);
        manager.addMimic(mimic);
        // Player.Right
        mimic = new MimicActionPlayerRight(manager, this, BoulderdashConsts.MIMIC_ACTION_PLAYER_RIGHT);
        manager.addMimic(mimic);
    }

    protected void PlayerDown() {
        FCaller.setObjectName(String.format(NGGameEngineConstants.MIMIC_OBJECTREQUEST_ACTION_TEMPLATE, BoulderdashConsts.MIMIC_ACTION_PLAYER_DOWN));
        FCaller.setObjectMethod(NGGameEngineConstants.MIMIC_OBJECTREQUESTMETHOD_DEFAULT);
        FCaller.Invoke();
    }

    protected void PlayerUp() {
        FCaller.setObjectName(String.format(NGGameEngineConstants.MIMIC_OBJECTREQUEST_ACTION_TEMPLATE, BoulderdashConsts.MIMIC_ACTION_PLAYER_UP));
        FCaller.setObjectMethod(NGGameEngineConstants.MIMIC_OBJECTREQUESTMETHOD_DEFAULT);
        FCaller.Invoke();
    }

    protected void PlayerLeft() {
        FCaller.setObjectName(String.format(NGGameEngineConstants.MIMIC_OBJECTREQUEST_ACTION_TEMPLATE, BoulderdashConsts.MIMIC_ACTION_PLAYER_LEFT));
        FCaller.setObjectMethod(NGGameEngineConstants.MIMIC_OBJECTREQUESTMETHOD_DEFAULT);
        FCaller.Invoke();
    }

    protected void PlayerRight() {
        FCaller.setObjectName(String.format(NGGameEngineConstants.MIMIC_OBJECTREQUEST_ACTION_TEMPLATE, BoulderdashConsts.MIMIC_ACTION_PLAYER_RIGHT));
        FCaller.setObjectMethod(NGGameEngineConstants.MIMIC_OBJECTREQUESTMETHOD_DEFAULT);
        FCaller.Invoke();
    }

    public Boulderdash(NGGameManager aManager, String aName) {
        super(aManager, aName);
        FGameFieldGridSize = 16;
        FShowGameFieldGrid = false;
        FPlaySound = false;
        FCaller = new NGObjectRequestCaller(getInvoker());
        FCaller.setLogManager(aManager.getLogManager());
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
        FCaller.setObjectName("TestModule");
        FCaller.setObjectMethod("Test");
        FCaller.Invoke();
    }

}
