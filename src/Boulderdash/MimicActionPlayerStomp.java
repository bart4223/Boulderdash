package Boulderdash;

import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicPeriodicAction;
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
            Integer value = mm.getCellValueAsInteger(game.getMemoryName(), player.getMemoryAddress());
            if (value == BoulderdashConsts.SPRITE_ID_BENDER_DEFAULT) {
                mm.setCellValue(game.getMemoryName(), player.getMemoryAddress(), BoulderdashConsts.SPRITE_ID_BENDER_FOOT_UP);
            }
            else {
                mm.setCellValue(game.getMemoryName(), player.getMemoryAddress(), BoulderdashConsts.SPRITE_ID_BENDER_DEFAULT);
            }
        }
    }

    public NG2DGame getGame() {
        return (NG2DGame)super.getGame();
    }

    public MimicActionPlayerStomp(NGControlMimicManager aManager, NGCustomGame aGame, String aName, Integer aInterval) {
        super(aManager, aGame, aName, aInterval);
    }

}
