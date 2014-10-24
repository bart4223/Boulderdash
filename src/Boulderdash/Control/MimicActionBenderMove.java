package Boulderdash.Control;

import Boulderdash.Graphics.*;
import Boulderdash.Physics.PhysicsActionMisc;
import Boulderdash.Storage.*;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicORBAction;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Physics.NGGameObjectPhysicsAction;
import Uniplay.Physics.NGObjectPhysicsProcessor;
import Uniplay.Physics.NGPhysicsAction2DImpuls;
import Uniplay.Storage.*;
import Boulderdash.BoulderdashConsts;
import Uniwork.Graphics.NGVector2D;

public class MimicActionBenderMove extends NGControlMimicORBAction {

    public enum Movemode {Up, Down, Left, Right};

    protected static Boolean isObjectRemovably(MemoryCellValue aCellValue) {
        return aCellValue.getObject() instanceof SpriteEarth || aCellValue.getObject() instanceof SpriteAir ||
            aCellValue.getObject() instanceof SpriteDiamond;
    }

    protected static Boolean isObjectAccessible(MemoryCellValue aCellValue) {
        return aCellValue.getObject() instanceof SpriteDoor;
    }

    protected static Boolean isObjectMoveable(MemoryCellValue aCellValue) {
        return aCellValue.getObject() instanceof SpriteBoulder;
    }

    protected void setNewCharacterPosition(NG2DGameCharacter aCharacter) {
        NG2DGame game = getGame();
        NG2DGameCharacterPosition pos = aCharacter.getPosition();
        switch (Mode) {
            case Up:
                game.setPCPosition(aCharacter, pos.getX(), pos.getY() - 1);
                break;
            case Down:
                game.setPCPosition(aCharacter, pos.getX(), pos.getY() + 1);
                break;
            case Left:
                game.setPCPosition(aCharacter, pos.getX() - 1, pos.getY());
                break;
            case Right:
                game.setPCPosition(aCharacter, pos.getX() + 1, pos.getY());
                break;
        }
    }

    @Override
    protected void DoExecute() {
        super.DoExecute();
        NG2DGame game = getGame();
        NGGameEngineMemoryManager mm = game.getMemoryManager();
        for (NGCustomGameCharacter pc : game.getPCs()) {
            NG2DGameCharacter character = (NG2DGameCharacter)pc;
            NGGameEngineMemoryAddress playerAddress = character.getMemoryAddress();
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
            MemoryCellValue value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), playerNewAddress);
            if (value.getObject() instanceof SpriteDiamond) {
                Boulderdash bd = (Boulderdash)getGame();
                SpriteDiamond diamond = (SpriteDiamond)value.getObject();
                bd.setDiamondCollected(diamond.getDiamond());
            } else if (value.getObject() instanceof SpriteDoor) {
                SpriteDoor door = (SpriteDoor)value.getObject();
                door.setBender((Bender)character);
            }
            if (isObjectRemovably(value)) {
                value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), playerAddress);
                if (value.getObject() instanceof SpriteDoor) {
                    SpriteDoor door = (SpriteDoor)value.getObject();
                    door.setBender(null);
                    setNewCharacterPosition(character);
                    mm.setCellValueAsObject(game.getMemoryName(), playerNewAddress, new SpriteBender((Bender)pc));
                }
                else {
                    mm.setCellValueAsObject(game.getMemoryName(), playerNewAddress, value.getObject());
                    setNewCharacterPosition(character);
                    mm.setCellValueAsObject(game.getMemoryName(), playerAddress, new SpriteAir());
                }
                PhysicsActionMisc.DetectObjectTouchObject(game, getPhysicsProcessor(), character, playerAddress);
            }
            else if (isObjectAccessible(value)) {
                setNewCharacterPosition(character);
                mm.setCellValueAsObject(game.getMemoryName(), playerAddress, new SpriteAir());
                if (value.getObject() instanceof SpriteDoor) {
                    SpriteDoor door = (SpriteDoor) value.getObject();
                    if (door.IsOpen()) {
                        door.getBender().setInDoor(true);
                        FManager.DeactivateMimics(BoulderdashConsts.MIMIC_TYPE_PLAYER_MOVE);
                        FManager.ActivateMimic(BoulderdashConsts.MIMIC_ACTION_DOOR_CLOSE);
                    }
                }
            }
            else if (isObjectMoveable(value)) {
                if (value.getObject() instanceof SpriteBoulder) {
                    NGObjectPhysicsProcessor pp = getPhysicsProcessor();
                    Boulder boulder = ((SpriteBoulder)(value.getObject())).getBoulder();
                    switch (Mode) {
                        case Right:
                            pp.addQueue(new NGGameObjectPhysicsAction(character, boulder, new NGPhysicsAction2DImpuls(1.0, new NGVector2D(1.0, 0.0))));
                            break;
                        case Left:
                            pp.addQueue(new NGGameObjectPhysicsAction(character, boulder, new NGPhysicsAction2DImpuls(1.0, new NGVector2D(-1.0, 0.0))));
                            break;
                    }
                }
            }
        }
    }

    public NG2DGame getGame() {
        return (NG2DGame)super.getGame();
    }

    public MimicActionBenderMove(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName);
        Mode = Movemode.Up;
    }

    public Movemode Mode;

}
