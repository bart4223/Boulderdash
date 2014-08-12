package Boulderdash;

import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicORBAction;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Storage.NG2DGame;
import Uniplay.Storage.NG2DGamePlayerItem;
import Uniplay.Storage.NG2DGamePlayerPosition;
import Uniplay.Storage.NGCustomGame;

public class MimicActionPlayerRight extends NGControlMimicORBAction {

    @Override
    protected void DoExecute() {
        super.DoExecute();
        NG2DGame game = getGame();
        NGGameEngineMemoryManager mm = game.getMemoryManager();
        for (NG2DGamePlayerItem player : game.getPlayers()) {
            NGGameEngineMemoryAddress playerAddress = player.getMemoryAddress();
            NGGameEngineMemoryAddress playerNewAddress = new NGGameEngineMemoryAddress(playerAddress.getPage(), playerAddress.getBase(), playerAddress.getOffset() + 1);
            Integer value = mm.getCellValueAsInteger(game.getMemoryName(), playerNewAddress);
            if (value == BoulderdashConsts.SPRITE_ID_EARTH || value == BoulderdashConsts.SPRITE_ID_AIR) {
                mm.setCellValue(game.getMemoryName(), playerNewAddress, BoulderdashConsts.SPRITE_ID_BENDER_DEFAULT);
                NG2DGamePlayerPosition pos = player.getPosition();
                player.setPosition(pos.getX() + 1, pos.getY());
                mm.setCellValue(game.getMemoryName(), playerAddress, BoulderdashConsts.SPRITE_ID_AIR);
            }
        }
    }

    public NG2DGame getGame() {
        return (NG2DGame)super.getGame();
    }

    public MimicActionPlayerRight(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName);
    }

}
