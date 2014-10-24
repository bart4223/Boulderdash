package Boulderdash.Control;

import Boulderdash.Storage.Boulderdash;
import Boulderdash.Storage.Diamond;
import Boulderdash.Storage.DiamondItem;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicPeriodicAction;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Storage.NG2DGame;
import Uniplay.Storage.NGCustomGame;

public class MimicActionDiamondFlicker extends NGControlMimicPeriodicAction {

    @Override
    protected void DoHandleTick() {
        super.DoHandleTick();
        Boulderdash game = (Boulderdash)getGame();
        for (DiamondItem di : game.getDiamonds()) {
            Diamond diamond = di.getDiamond();
            if (!diamond.getCollected()) {
                diamond.incFlickerIndex();
                NGGameEngineMemoryManager mm = game.getMemoryManager();
                mm.refreshCell(game.getMemoryName(), diamond.getMemoryAddress());
            }
        }
    }

    @Override
    public NG2DGame getGame() {
        return (NG2DGame)super.getGame();
    }

    public MimicActionDiamondFlicker(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName);
    }

}
