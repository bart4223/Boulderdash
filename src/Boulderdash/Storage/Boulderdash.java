package Boulderdash.Storage;

import Boulderdash.BoulderdashConsts;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryIntegerCellValue;
import Uniplay.Kernel.NGGameEngineMemoryObjectCellValue;
import Uniplay.NGGameEngineConstants;
import Uniplay.Storage.*;
import Uniwork.Base.NGObjectRequestCaller;
import Uniwork.Base.NGObjectRequestInvoker;
import Uniwork.Base.NGPropertyItem;
import Uniwork.Misc.NGStrings;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Boulderdash extends NG2DGame {

    protected Integer FGameFieldGridSize;
    protected Boolean FShowGameFieldGrid;
    protected Stage FGameFieldStage;
    protected GameFieldStageController FGameFieldController;
    protected Stage FGameControlStage;
    protected GameControlStageController FGameControlController;
    protected NGObjectRequestCaller FCaller;
    protected Boolean FPlaySound;
    protected Integer FDiamondCount;
    protected ArrayList<Door> FDoors;

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

    protected void stopSound() {
        getSoundManager().stopSound(BoulderdashConsts.SOUND_SPLASH_FEAR);
    }

    @Override
    protected void DoBeforeStart() {
        super.DoBeforeStart();
        if (getSoundManager() != null && FPlaySound) {
            stopSound();
        }
        removeAllPCs();
        add2DGamePC(getPlayerManager().getCurrentPlayer());
    }

    @Override
    protected void assignGameObjects() {
        super.assignGameObjects();
        assignDiamondCount();
        assignDoors(getCurrentGameFieldLayer());
    }

    @Override
    protected Class getGameCharacterClass() {
        return Bender.class;
    }

    @Override
    protected Class getMemoryCellValueClass() {
        return MemoryCellValue.class;
    }

    @Override
    protected void assignMemoryCellValueFrom(NGGameEngineMemoryAddress aAddress, NGGameEngineMemoryObjectCellValue aCellValue, Object aObject) {
        if (aObject instanceof NGGameEngineMemoryIntegerCellValue) {
            NGGameEngineMemoryIntegerCellValue cellValue = (NGGameEngineMemoryIntegerCellValue)aObject;
            BoulderdashSprite sprite = getSpriteFrom(aAddress, cellValue.getInteger());
            aCellValue.setObject(sprite);
        }
    }

    protected BoulderdashSprite getSpriteFrom(NGGameEngineMemoryAddress aAddress, Integer aID)  {
        switch (aID) {
            case 0:
                return new SpriteAir();
            case 1:
                return new SpriteBender((Bender)getPCfromAddress(aAddress));
            case 2:
                return new SpriteDiamond();
            case 4:
                return new SpriteBoulder();
            case 5:
                return new SpriteEarth();
            case 7:
                return new SpriteDoor(getDoorfromAddress(aAddress));
            case 12:
                return new SpriteMonster();
            case 14 :case 15 :case 16 :case 17 :case 18 :case 19 :case 20 :case 21 :case 22 :case 23 :case 24 :case 25 :case 26:
                SpriteBrick brick = new SpriteBrick();
                brick.setID(aID);
                return brick;
            case 27:
                return new SpriteBomb();
        }
        return null;
    }

    protected void assignDiamondCount() {
        FDiamondCount = 0;
        for (NG2DGameFieldLayer layer : FCurrentLevel.getGameField().getLayers()) {
            for (NGPropertyItem prop : layer.getProps().getItems()) {
                String obj = NGStrings.getStringPos(prop.getName(), "\\.", 3);
                if (obj.equals("DIAMOND")) {
                    String op = NGStrings.getStringPos(prop.getName(), "\\.", 4);
                    if (op.equals("COUNT")) {
                        FDiamondCount = FDiamondCount + (Integer)prop.getValue();
                    }
                }
            }
        }
        writeLog(String.format("Level [%s] has %d diamond(s).", FCurrentLevel.getCaption(), FDiamondCount));
    }

    protected void assignDoors(NG2DGameFieldLayer aLayer) {
        FDoors.clear();
        for (NGPropertyItem prop : aLayer.getProps().getItems()) {
            String obj = NGStrings.getStringPos(prop.getName(), "\\.", 3);
            if (obj.equals("DOOR")) {
                String op = NGStrings.getStringPos(prop.getName(), "\\.", 4);
                if (op.equals("POSITION")) {
                    NG2DObjectPosition pos = (NG2DObjectPosition)prop.getValue();
                    addDoor(pos, 1);
                }
            }
        }
    }

    public Door getDoorfromAddress(NGGameEngineMemoryAddress aAddress) {
        for (Door door : FDoors) {
            if (door.IsDoorFromAddress(aAddress)) {
                return door;
            }
        }
        return null;
    }

    protected void addDoor(NG2DObjectPosition aPosition, Integer aLayerIndex) {
        Door item = new Door(this, aLayerIndex);
        item.setPosition(aPosition.getX(), aPosition.getY());
        FDoors.add(item);
        writeLog(String.format("Door at (%.1f/%.1f) added.", aPosition.getX(), aPosition.getY()));
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
        FPlaySound = true;
        FCaller = new NGObjectRequestCaller(getInvoker());
        FCaller.setLogManager(aManager.getLogManager());
        CreateControlStage();
        CreateGameFieldStage();
        FDiamondCount = 0;
        FDoors = new ArrayList<Door>();
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

    public void DiamondCollected() {
        FDiamondCount--;
        if (FDiamondCount == 0) {
            getMimicManager().ActivateMimic(BoulderdashConsts.MIMIC_ACTION_DOOR_OPEN);
        }
        else {
            writeLog(String.format("Diamond collected. [%d] diamond(s) to be collect...", FDiamondCount));
        }
    }

    public ArrayList<Door> getDoors() {
        return FDoors;
    }

    public void addTestPlayers() {
        NGPlayerManager pm = getPlayerManager();
        pm.newPlayer(NGPlayer.class, "BART4223", "Bart4223");
        pm.newPlayer(NGPlayer.class, "SEPPI", "Seppi");
        pm.newPlayer(NGPlayer.class, "XMEN", "XMen");
        pm.newPlayer(NGPlayer.class, "SKYGENERATION", "Sky");
        pm.setCurrentPlayer("BART4223");
    }

    // ToDo
    public void Test() {
        FCaller.setObjectName("TestModule");
        FCaller.setObjectMethod("Test");
        FCaller.Invoke();
    }

}
