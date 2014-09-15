package Boulderdash.Control;

import Boulderdash.Storage.*;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicORBAction;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Storage.*;

public class MimicActionPlayerMove extends NGControlMimicORBAction {

    public enum Movemode {Up, Down, Left, Right};

    protected Boolean isObjectRemovably(BoulderdashMemoryCellValue aCellValue) {
        return aCellValue.getObject() instanceof SpriteEarth || aCellValue.getObject() instanceof SpriteAir ||
            aCellValue.getObject() instanceof SpriteDiamond;
    }

    @Override
    protected void DoExecute() {
        super.DoExecute();
        NG2DGame game = getGame();
        NGGameEngineMemoryManager mm = game.getMemoryManager();
        for (NGCustomGameCharacter pc : game.getPCs()) {
            NG2DGameCharacter player = (NG2DGameCharacter)pc;
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
            if (value.getObject() instanceof SpriteDiamond) {
                Boulderdash bd = (Boulderdash)getGame();
                bd.DiamondCollected();
            } else if (value.getObject() instanceof SpriteDoor) {
                SpriteDoor door = (SpriteDoor)value.getObject();
                if (door.IsOpen()) {
                    getGame().FinishLevel();
                    return;
                }
            }
            if (isObjectRemovably(value)) {
                value = (BoulderdashMemoryCellValue)mm.getCellValue(game.getMemoryName(), playerAddress);
                mm.setCellValueAsObject(game.getMemoryName(), playerNewAddress, value.getObject());
                NG2DGameCharacterPosition pos = player.getPosition();
                switch (Mode) {
                    case Up:
                        game.setPCPosition(player, pos.getX(), pos.getY() - 1);
                        break;
                    case Down:
                        game.setPCPosition(player, pos.getX(), pos.getY() + 1);
                        break;
                    case Left:
                        game.setPCPosition(player, pos.getX() - 1, pos.getY());
                        break;
                    case Right:
                        game.setPCPosition(player, pos.getX() + 1, pos.getY());
                        break;
                }
                mm.setCellValueAsObject(game.getMemoryName(), playerAddress, new SpriteAir());
            }
        }
    }

    public NG2DGame getGame() {
        return (NG2DGame)super.getGame();
    }

    public MimicActionPlayerMove(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName);
        Mode = Movemode.Up;
    }

    public Movemode Mode;

}
