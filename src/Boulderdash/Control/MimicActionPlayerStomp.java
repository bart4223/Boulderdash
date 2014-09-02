package Boulderdash.Control;

import Boulderdash.Storage.BoulderdashMemoryCellValue;
import Boulderdash.Storage.BoulderdashSpriteBender;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicPeriodicAction;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Storage.NG2DGame;
import Uniplay.Storage.NG2DGamePlayerItem;
import Uniplay.Storage.NGCustomGame;
import Uniplay.Storage.NGCustomGamePlayerItem;

public class MimicActionPlayerStomp extends NGControlMimicPeriodicAction {

    @Override
    protected void DoHandleTick() {
        super.DoHandleTick();
        NG2DGame game = getGame();
        NGGameEngineMemoryManager mm = game.getMemoryManager();
        for (NGCustomGamePlayerItem item : game.getPlayers()) {
            NG2DGamePlayerItem player = (NG2DGamePlayerItem)item;
            NGGameEngineMemoryAddress address = player.getMemoryAddress();
            BoulderdashMemoryCellValue value = (BoulderdashMemoryCellValue)mm.getCellValue(game.getMemoryName(), address);
            BoulderdashSpriteBender bender = (BoulderdashSpriteBender)value.getObject();
            bender.ToggleMode();
            mm.refreshCell(game.getMemoryName(), address);
        }
    }

    @Override
    public NG2DGame getGame() {
        return (NG2DGame)super.getGame();
    }

    public MimicActionPlayerStomp(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName);
    }

}
