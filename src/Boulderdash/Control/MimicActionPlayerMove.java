package Boulderdash.Control;

import Boulderdash.Storage.BoulderdashMemoryCellValue;
import Boulderdash.Storage.BoulderdashSpriteAir;
import Boulderdash.Storage.BoulderdashSpriteEarth;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicORBAction;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Storage.*;

public class MimicActionPlayerMove extends NGControlMimicORBAction {

    public enum Movemode {Up, Down, Left, Right};

    @Override
    protected void DoExecute() {
        super.DoExecute();
        NG2DGame game = getGame();
        NGGameEngineMemoryManager mm = game.getMemoryManager();
        for (NGCustomGamePlayerItem item : game.getPlayers()) {
            NG2DGamePlayerItem player = (NG2DGamePlayerItem)item;
            NGGameEngineMemoryAddress playerAddress = player.getMemoryAddress();
            NGGameEngineMemoryAddress playerNewAddress = null;
            switch (Mode) {
                case Up:
                    playerNewAddress = new NGGameEngineMemoryAddress(playerAddress.getPage(), playerAddress.getBase() - 1, playerAddress.getOffset());
                    break;
                case Down:
                    playerNewAddress = new NGGameEngineMemoryAddress(playerAddress.getPage(), playerAddress.getBase() + 1, playerAddress.getOffset());
                    break;
                case Left:
                    playerNewAddress = new NGGameEngineMemoryAddress(playerAddress.getPage(), playerAddress.getBase(), playerAddress.getOffset() - 1);
                    break;
                case Right:
                    playerNewAddress = new NGGameEngineMemoryAddress(playerAddress.getPage(), playerAddress.getBase(), playerAddress.getOffset() +  1);
                    break;
            }
            BoulderdashMemoryCellValue value = (BoulderdashMemoryCellValue)mm.getCellValue(game.getMemoryName(), playerNewAddress);
            if (value.getObject() instanceof BoulderdashSpriteEarth || value.getObject() instanceof BoulderdashSpriteAir) {
                value = (BoulderdashMemoryCellValue)mm.getCellValue(game.getMemoryName(), playerAddress);
                mm.setCellValueAsObject(game.getMemoryName(), playerNewAddress, value.getObject());
                NG2DGamePlayerPosition pos = player.getPosition();
                switch (Mode) {
                    case Up:
                        game.setPlayerPosition(player, pos.getX(), pos.getY() - 1);
                        break;
                    case Down:
                        game.setPlayerPosition(player, pos.getX(), pos.getY() + 1);
                        break;
                    case Left:
                        game.setPlayerPosition(player, pos.getX() - 1, pos.getY());
                        break;
                    case Right:
                        game.setPlayerPosition(player, pos.getX() + 1, pos.getY());
                        break;
                }
                mm.setCellValueAsObject(game.getMemoryName(), playerAddress, new BoulderdashSpriteAir());
            }
        }
    }

    public NG2DGame getGame() {
        return (NG2DGame)super.getGame();
    }

    public MimicActionPlayerMove(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName);
        Mode = Mode.Up;
    }

    public Movemode Mode;

}
