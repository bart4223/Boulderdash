package Boulderdash.Control;

import Boulderdash.Storage.*;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicPeriodicAction;
import Uniplay.Storage.NG2DGame;
import Uniplay.Storage.NGCustomGame;

public class MimicActionDoor extends NGControlMimicPeriodicAction {

    public enum DoorMode {Open, Close};

    @Override
    protected void DoHandleTick() {
        super.DoHandleTick();
        Boulderdash game = (Boulderdash)getGame();
        for (Door door : game.getDoors()) {
            switch (Mode) {
                case Open:
                    if (!door.IsOpen()) {
                        door.Open();
                    }
                    else {
                        Deactivate();
                    }
                    break;
                case Close:
                    if (!door.IsClose()) {
                        door.Close();
                    }
                    else {
                        Deactivate();
                    }
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
