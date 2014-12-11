package Boulderdash.Storage;

import Boulderdash.BoulderdashConsts;
import Boulderdash.Graphics.*;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryIntegerCellValue;
import Uniplay.Kernel.NGGameEngineMemoryObjectCellValue;
import Uniplay.Misc.NGTaskManager;
import Uniplay.NGGameEngineConstants;
import Uniplay.Sound.NGMediaPlayerSoundItem;
import Uniplay.Storage.*;
import Uniwork.Base.NGObjectRequestCaller;
import Uniwork.Base.NGObjectRequestInvoker;
import Uniwork.Base.NGPropertyItem;
import Uniwork.Misc.NGStrings;
import Uniwork.Misc.NGTickEvent;
import javafx.application.Platform;
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

    public static Integer CMinTime = 150;
    public static String CTimeTask = "Boulderdash.GameTime";
    public static String CTimeFlameTask = "Boulderdash.GameTimeFlame";

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
    protected Boolean FNewLevelStarted;
    protected Integer FPoints;
    protected Integer FCurrentTime;
    protected Integer FMaxTime;

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
            FGameControlStage.setScene(new Scene(lRoot, 880, 50, Color.DARKGRAY));
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
            Scene scene = new Scene(lRoot, 880, 880, Color.DARKGRAY);
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
        FGameControlStage.setY(110);
        FGameFieldStage.setX(1400);
        FGameFieldStage.setY(210);
    }

    protected void updateGameControlControls() {
        FGameControlController.updateControls();
    }

    @Override
    protected void BeforeLoadLevel() {
        super.BeforeLoadLevel();
        FDiamonds.clear();
    }

    @Override
    protected void AfterLoadLevel(NG2DLevel aLevel) {
        super.AfterLoadLevel(aLevel);
        if (!FNewLevelStarted) {
            resetPCs();
        }
    }

    @Override
    protected void DoStart() {
        super.DoStart();
        setPoints(0);
    }

    protected void UpdateToBeCollectedDiamondCount() {
        Integer collected = FDiamonds.size() - getCollectedDiamondCount();
        FGameFieldController.setDiamondsCounter(collected);
    }

    @Override
    protected void InternalStartLevel() {
        super.InternalStartLevel();
        FNewLevelStarted = true;
        setLiveIndicator(getPCs().get(0).getCurrentLives());
        UpdateToBeCollectedDiamondCount();
        resetTime();
        StartTimeTask();
    }

    @Override
    protected void DoFinishLevel() {
        super.DoFinishLevel();
        StopTimeTask();
        FNewLevelStarted = false;
        addPoints(1000);
    }

    @Override
    protected void DoFinish() {
        super.DoFinish();
        FNewLevelStarted = false;
    }

    @Override
    protected void DoShowStages() {
        super.DoShowStages();
        perfectLayout();
        FGameFieldController.RenderScene();
        setLiveIndicator(0); // Force second rendering
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
    protected void assignGameObjects(NG2DLevel aLevel) {
        super.assignGameObjects(aLevel);
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

    protected void StopTimeTask() {
        NGTaskManager tm = getTaskManager();
        tm.stopPeriodicTask(CTimeFlameTask);
        tm.stopPeriodicTask(CTimeTask);
    }

    protected void StartTimeTask() {
        StartTimeTask(0);
    }

    protected void StartTimeTask(Integer aDelay) {
        NGTaskManager tm = getTaskManager();
        tm.startPeriodicTask(CTimeTask, aDelay);
        tm.startPeriodicTask(CTimeFlameTask, aDelay);
    }

    protected void resetTime() {
        FCurrentTime = FMaxTime;
        FGameFieldController.resetTimeIndicator();
    }

    protected void checkTime() {
        if (FCurrentTime > 0) {
            FCurrentTime = FCurrentTime - 1;
            Integer length = FCurrentTime * FGameFieldController.getMaxTimeIndicatorFusibleLength() / FMaxTime;
            if (length < FGameFieldController.getCurrentTimeIndicatorFusibleLength()) {
                FGameFieldController.setCurrentTimeIndicatorFusibleLength(length);
            }
        }
        if (FCurrentTime == 0) {
            writeLog("Time expired...");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Restart();
                }
            });
        }
    }

    @Override
    protected void DoHandleTick(NGTickEvent aEvent) {
        super.DoHandleTick(aEvent);
        if (aEvent.Name.equals(CTimeTask)) {
            NGTaskManager tm = getTaskManager();
            tm.stopPeriodicTask(CTimeFlameTask);
            try {
                checkTime();
            } finally {
                tm.startPeriodicTask(CTimeFlameTask);
            }
        } else if (aEvent.Name.equals(CTimeFlameTask)) {
            FGameFieldController.incTimeIndicatorFlame();
        }
    }

    @Override
    protected void DoInitialize() {
        super.DoInitialize();
        NGTaskManager tm = getTaskManager();
        tm.addPeriodicTask(CTimeTask, 100);
        tm.addListener(CTimeTask, this);
        tm.addPeriodicTask(CTimeFlameTask, 10);
        tm.addListener(CTimeFlameTask, this);
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
        FNewLevelStarted = false;
        FPoints = 0;
        FMaxTime = CMinTime;
        FCurrentTime = 0;
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
        addPoints(aDiamond.getValence());
        Integer collected = getCollectedDiamondCount();
        if (FDiamonds.size() == collected) {
            getMimicManager().ActivateMimic(BoulderdashConsts.MIMIC_ACTION_DOOR_OPEN);
        }
        else {
            writeLog(String.format("Diamond collected. %d diamond(s) to be collect...", FDiamonds.size() - collected));
        }
        UpdateToBeCollectedDiamondCount();
    }

    public void setPoints(Integer aValue) {
        FPoints = aValue;
        FGameFieldController.setPointCounter(FPoints);
    }

    public Integer getPoints() {
        return FPoints;
    }

    public void addPoints(Integer aValue) {
        setPoints(getPoints() + aValue);
    }

    public void subPoints(Integer aValue) {
        Integer points = getPoints() - aValue;
        if (points < 0) {
            points = 0;
        }
        setPoints(points);
    }

    public void setLiveIndicator(Integer aValue) {
        FGameFieldController.setLiveIndicator(aValue);
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
        if (!getSoundManager().IsPlayingSound(BoulderdashConsts.SOUND_BENDER_WALK)) {
            getSoundManager().playSound(BoulderdashConsts.SOUND_BENDER_WALK, NGMediaPlayerSoundItem.Mode.several);
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
