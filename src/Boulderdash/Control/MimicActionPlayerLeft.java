package Boulderdash.Control;

import Boulderdash.Storage.BoulderdashMemoryCellValue;
import Boulderdash.Storage.BoulderdashSpriteAir;
import Boulderdash.Storage.BoulderdashSpriteEarth;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicORBAction;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Storage.*;

public class MimicActionPlayerLeft extends NGControlMimicORBAction {

    @Override
    protected void DoExecute() {
        super.DoExecute();
        NG2DGame game = getGame();
        NGGameEngineMemoryManager mm = game.getMemoryManager();
        for (NGCustomGamePlayerItem item : game.getPlayers()) {
            NG2DGamePlayerItem player = (NG2DGamePlayerItem)item;
            NGGameEngineMemoryAddress playerAddress = player.getMemoryAddress();
            NGGameEngineMemoryAddress playerNewAddress = new NGGameEngineMemoryAddress(playerAddress.getPage(), playerAddress.getBase(), playerAddress.getOffset() - 1);
            BoulderdashMemoryCellValue value = (BoulderdashMemoryCellValue)mm.getCellValue(game.getMemoryName(), playerNewAddress);
            if (value.getObject() instanceof BoulderdashSpriteEarth || value.getObject() instanceof BoulderdashSpriteAir) {
                value = (BoulderdashMemoryCellValue)mm.getCellValue(game.getMemoryName(), playerAddress);
                mm.setCellValueAsObject(game.getMemoryName(), playerNewAddress, value.getObject());
                NG2DGamePlayerPosition pos = player.getPosition();
                game.setPlayerPosition(player, pos.getX() - 1, pos.getY());
                mm.setCellValueAsObject(game.getMemoryName(), playerAddress, new BoulderdashSpriteAir());
            }
        }
    }

    public NG2DGame getGame() {
        return (NG2DGame)super.getGame();
    }

    public MimicActionPlayerLeft(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName);
    }

}
