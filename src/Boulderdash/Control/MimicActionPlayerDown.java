package Boulderdash.Control;

import Boulderdash.Storage.BoulderdashMemoryCellValue;
import Boulderdash.Storage.BoulderdashSpriteAir;
import Boulderdash.Storage.BoulderdashSpriteEarth;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicORBAction;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Storage.*;

public class MimicActionPlayerDown extends NGControlMimicORBAction {

    @Override
    protected void DoExecute() {
        super.DoExecute();
        NG2DGame game = getGame();
        NGGameEngineMemoryManager mm = game.getMemoryManager();
        for (NGCustomGamePlayerItem item : game.getPlayers()) {
            NG2DGamePlayerItem player = (NG2DGamePlayerItem)item;
            NGGameEngineMemoryAddress playerAddress = player.getMemoryAddress();
            NGGameEngineMemoryAddress playerNewAddress = new NGGameEngineMemoryAddress(playerAddress.getPage(), playerAddress.getBase() + 1, playerAddress.getOffset());
            BoulderdashMemoryCellValue value = (BoulderdashMemoryCellValue)mm.getCellValue(game.getMemoryName(), playerNewAddress);
            if (value.getObject() instanceof BoulderdashSpriteEarth || value.getObject() instanceof BoulderdashSpriteAir) {
                value = (BoulderdashMemoryCellValue)mm.getCellValue(game.getMemoryName(), playerAddress);
                mm.setCellValueAsObject(game.getMemoryName(), playerNewAddress, value.getObject());
                NG2DGamePlayerPosition pos = player.getPosition();
                game.setPlayerPosition(player, pos.getX(), pos.getY() + 1);
                mm.setCellValueAsObject(game.getMemoryName(), playerAddress, new BoulderdashSpriteAir());
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
