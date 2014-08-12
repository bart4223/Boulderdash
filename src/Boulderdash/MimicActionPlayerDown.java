package Boulderdash;

import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicORBAction;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Storage.NG2DGame;
import Uniplay.Storage.NG2DGamePlayerItem;
import Uniplay.Storage.NG2DGamePlayerPosition;
import Uniplay.Storage.NGCustomGame;

public class MimicActionPlayerDown extends NGControlMimicORBAction {

    @Override
    protected void DoExecute() {
        super.DoExecute();
        NG2DGame game = getGame();
        NGGameEngineMemoryManager mm = game.getMemoryManager();
        for (NG2DGamePlayerItem player : game.getPlayers()) {
            NGGameEngineMemoryAddress playerAddress = player.getMemoryAddress();
            NGGameEngineMemoryAddress playerNewAddress = new NGGameEngineMemoryAddress(playerAddress.getPage(), playerAddress.getBase() + 1, playerAddress.getOffset());
            Integer value = mm.getCellValueAsInteger(game.getMemoryName(), playerNewAddress);
            if (value == BoulderdashConsts.SPRITE_ID_EARTH) {
                mm.setCellValue(game.getMemoryName(), playerNewAddress, BoulderdashConsts.SPRITE_ID_BENDER_DEFAULT);
                NG2DGamePlayerPosition pos = player.getPosition();
                player.setPosition(pos.getX(), pos.getY() + 1);
                mm.setCellValue(game.getMemoryName(), playerAddress, BoulderdashConsts.SPRITE_ID_AIR);
            }
        }
    }

    public NG2DGame getGame() {
        return (NG2DGame)super.getGame();
    }

    public MimicActionPlayerDown(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName);
    }

}
