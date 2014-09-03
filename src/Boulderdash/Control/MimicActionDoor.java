package Boulderdash.Control;

import Boulderdash.Storage.*;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicPeriodicAction;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Storage.NG2DGame;
import Uniplay.Storage.NGCustomGame;

public class MimicActionDoor extends NGControlMimicPeriodicAction {

    public enum DoorMode {Open, Close};

    @Override
    protected void DoHandleTick() {
        super.DoHandleTick();
        Boulderdash game = (Boulderdash)getGame();
        NGGameEngineMemoryManager mm = game.getMemoryManager();
        for (BoulderdashDoorItem item : game.getDoors()) {
            switch (Mode) {
                case Open:
                    NGGameEngineMemoryAddress address = item.getMemoryAddress();
                    BoulderdashMemoryCellValue value = (BoulderdashMemoryCellValue)mm.getCellValue(game.getMemoryName(), address);
                    BoulderdashSpriteDoor door = (BoulderdashSpriteDoor)value.getObject();
                    door.Open();
                    if (door.IsOpen()) {
                        Deactivate();
                    }
                    mm.refreshCell(game.getMemoryName(), address);
                    break;
                case Close:
                    // ToDo
                    writeLog("Door.Close");
                    break;
            }
        }
    }

    @Override
    public NG2DGame getGame() {
        return (NG2DGame)super.getGame();
    }

    public MimicActionDoor(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName, Kind.temporary);
        Mode = DoorMode.Open;
    }

    public DoorMode Mode;

}
