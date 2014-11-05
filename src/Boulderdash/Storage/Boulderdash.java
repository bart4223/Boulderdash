package Boulderdash.Storage;

import Boulderdash.BoulderdashConsts;
import Boulderdash.Graphics.*;
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
    protected ArrayList<DoorItem> FDoors;
    protected ArrayList<DiamondItem> FDiamonds;

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
            FGameControlStage.setScene(new Scene(lRoot, 800, 50, Color.DARKGRAY));
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
            FGameFieldStage.setTitle("Boulderdash.GameField");
            Scene scene = new Scene(lRoot, 800, 800, Color.DARKGRAY);
            FGameFieldStage.setScene(scene);
            FGameFieldStage.setResizable(false);
            scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
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
                DiamondItem item = addDiamond(aAddress);
                item.getDiamond().setLayer(1);
                item.getDiamond().setInEarth(true);
                return new SpriteDiamond(item.getDiamond());
            case 4:
                Boulder boulder = new Boulder(this);
                boulder.setPosition(aAddress.getOffset(), aAddress.getBase());
                boulder.setLayer(1);
                boulder.setInEarth(true);
                return new SpriteBoulder(boulder);
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

    protected DiamondItem addDiamond(NGGameEngineMemoryAddress aAddress) {
        Diamond diamond = new Diamond(this);
        diamond.setPosition(aAddress.getOffset(), aAddress.getBase());
        DiamondItem item = new DiamondItem(diamond);
        FDiamonds.add(item);
        return item;
    }

    protected Integer getCollectedDiamondCount() {
        Integer res = 0;
        for (DiamondItem item : FDiamonds) {
            if (item.getCollected()) {
                res++;
            }
        }
        return res;
    }

    protected void assignDoors(NG2DGameFieldLayer aLayer) {
        FDoors.clear();
        for (NGPropertyItem prop : aLayer.getProps().getItems()) {
            String obj = NGStrings.getStringPos(prop.getName(), "\\.", 3);
            if (obj.equals("DOOR")) {
                String op = NGStrings.getStringPos(prop.getName(), "\\.", 4);
                if (op.equals("POSITION")) {
                    NG2DObjectPosition pos = (NG2DObjectPosition)prop.getValue();
                    Door door = addDoor(pos);
                    door.setLayer(1);
                }
            }
        }
    }

    public Door getDoorfromAddress(NGGameEngineMemoryAddress aAddress) {
        for (DoorItem item : FDoors) {
            if (item.getDoor().IsFromAddress(aAddress)) {
                return item.getDoor();
            }
        }
        return null;
    }

    protected Door addDoor(NG2DObjectPosition aPosition) {
        Door door = new Door(this);
        door.setPosition(aPosition.getX(), aPosition.getY());
        FDoors.add(new DoorItem(door));
        writeLog(String.format("Door at (%.1f/%.1f) added.", aPosition.getX(), aPosition.getY()));
        return door;
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
        FDoors = new ArrayList<DoorItem>();
        FDiamonds = new ArrayList<DiamondItem>();
    }

    public Canvas getGameFieldLayerBack() {
        return FGameFieldController.LayerBack;
    }

    public Canvas getGameFieldLayerBender() {
        return FGameFieldController.LayerBender;
    }

    public Canvas getGameFieldLayerFront() {
        return FGameFieldController.LayerFront;
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

    public void setDiamondCollected(Diamond aDiamond) {
        aDiamond.setCollected(true);
        Integer collected = getCollectedDiamondCount();
        if (FDiamonds.size() == collected) {
            getMimicManager().ActivateMimic(BoulderdashConsts.MIMIC_ACTION_DOOR_OPEN);
        }
        else {
            writeLog(String.format("Diamond collected. %d diamond(s) to be collect...", FDiamonds.size() - collected));
        }
    }

    public ArrayList<DoorItem> getDoors() {
        return FDoors;
    }

    public ArrayList<DiamondItem> getDiamonds() {
        return FDiamonds;
    }

    @Override
    public void setPCPosition(NG2DGameCharacter aPlayerItem, double aX, double aY) {
        super.setPCPosition(aPlayerItem, aX, aY);
        if (!getSoundManager().IsPlaySound(BoulderdashConsts.SOUND_BENDER_WALK)) {
            getSoundManager().playSound(BoulderdashConsts.SOUND_BENDER_WALK, 0.0, 100.0);
        }
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
