package Boulderdash.Control;

import Boulderdash.Storage.BoulderdashSpriteAir;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicORBAction;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Storage.*;

public class MimicActionPlayerUp extends NGControlMimicORBAction {

    @Override
    protected void DoExecute() {
        super.DoExecute();
        NG2DGame game = getGame();
        NGGameEngineMemoryManager mm = game.getMemoryManager();
        for (NGCustomGamePlayerItem item : game.getPlayers()) {
            NG2DGamePlayerItem player = (NG2DGamePlayerItem)item;
            NGGameEngineMemoryAddress playerAddress = player.getMemoryAddress();
            NGGameEngineMemoryAddress playerNewAddress = new NGGameEngineMemoryAddress(playerAddress.getPage(), playerAddress.getBase() - 1, playerAddress.getOffset());
            Integer value = mm.getCellValueAsInteger(game.getMemoryName(), playerNewAddress);
            if (value == Boulderdash.BoulderdashConsts.SPRITE_ID_EARTH || value == BoulderdashSpriteAir.ID) {
                mm.setCellValue(game.getMemoryName(), playerNewAddress, Boulderdash.BoulderdashConsts.SPRITE_ID_BENDER_DEFAULT);
                NG2DGamePlayerPosition pos = player.getPosition();
                game.setPlayerPosition(player, pos.getX(), pos.getY() - 1);
                mm.setCellValue(game.getMemoryName(), playerAddress, BoulderdashSpriteAir.ID);
            }
        }
    }

    public NG2DGame getGame() {
        return (NG2DGame)super.getGame();
    }

    public MimicActionPlayerUp(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName);
    }

}
