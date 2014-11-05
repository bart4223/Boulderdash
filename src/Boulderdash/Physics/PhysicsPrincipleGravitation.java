package Boulderdash.Physics;

import Boulderdash.BoulderdashConsts;
import Boulderdash.Graphics.SpriteAir;
import Boulderdash.Graphics.SpriteBender;
import Boulderdash.Graphics.SpriteBoulder;
import Boulderdash.Graphics.SpriteDiamond;
import Boulderdash.Storage.MemoryCellValue;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Kernel.NGGameEngineMemoryObjectCellValue;
import Uniplay.NGGameEngineConstants;
import Uniplay.Physics.*;
import Uniplay.Sound.NGSoundManager;
import Uniplay.Storage.NG2DGame;
import Uniplay.Storage.NG2DGameObject;
import Uniplay.Storage.NGCustomGame;

public class PhysicsPrincipleGravitation extends NG2DNewtonPhysicsPrinciple {

    protected NGSoundManager FSoundManager;

    protected NGSoundManager getSoundManager() {
        if (FSoundManager == null) {
            FSoundManager = (NGSoundManager)ResolveObject(NGGameEngineConstants.CMP_SOUND_MANAGER, NGSoundManager.class);
        }
        return FSoundManager;
    }

    protected void PlayerKilled(NGCustomGame aGame) {
        aGame.DeactivateMimicActions(BoulderdashConsts.MIMIC_ACTION_PLAYER_MOVE);
        aGame.DeactivateMimicAction(BoulderdashConsts.MIMIC_ACTION_PLAYER_STOMP);
        aGame.ActivateMimicAction(BoulderdashConsts.MIMIC_ACTION_PLAYER_KILLED);
    }

    protected Boolean isObjectDestructible(MemoryCellValue aCellValue) {
        return aCellValue.getObject() instanceof SpriteBender;
    }

    protected Boolean isObjectAccessible(MemoryCellValue aCellValue) {
        return aCellValue.getObject() instanceof SpriteAir;
    }

    protected Boolean isObjectSlippery(MemoryCellValue aCellValue) {
        return aCellValue.getObject() instanceof SpriteBoulder || aCellValue.getObject() instanceof SpriteDiamond;
    }

    protected void moveObject(NG2DGameObject aGameObject, NGGameEngineMemoryAddress aObjectAddress, NGGameEngineMemoryAddress aObjectNewAddress) {
        NG2DGame game = (NG2DGame)aGameObject.getGame();
        NGGameEngineMemoryManager mm = game.getMemoryManager();
        NGGameEngineMemoryObjectCellValue cellvalue = (NGGameEngineMemoryObjectCellValue)mm.getCellValue(game.getMemoryName(), aObjectAddress);
        mm.setCellValueAsObject(game.getMemoryName(), aObjectNewAddress, cellvalue.getObject());
        mm.refreshCell(game.getMemoryName(), aObjectNewAddress);
        game.setObjectPosition(aGameObject, aObjectNewAddress.getOffset(), aObjectNewAddress.getBase());
        mm.setCellValueAsObject(game.getMemoryName(), aObjectAddress, new SpriteAir());
        mm.refreshCell(game.getMemoryName(), aObjectAddress);
        NGObjectPhysicsProcessor pp = getPhysicsProcessor();
        pp.addQueue(new NGGameObjectPhysicsAction(FCurrentGOPhysicsAction.getTriggerObject(), aGameObject, new NGPhysicsAction2DMovement(aGameObject.getPosition())));
        PhysicsActionMisc.DetectObjectTouchObject(game, getPhysicsProcessor(), aGameObject, aObjectAddress);
        if (!getSoundManager().IsPlaySound(BoulderdashConsts.SOUND_ROLLING_STONE)) {
            getSoundManager().playSound(BoulderdashConsts.SOUND_ROLLING_STONE, 0.0, 250.0);
        }
    }

    @Override
    protected void DoExecute() {
        super.DoExecute();
        if (FCurrentGOPhysicsAction.getAffectedObject() instanceof NG2DGameObject) {
            NG2DGameObject go = (NG2DGameObject)FCurrentGOPhysicsAction.getAffectedObject();
            NG2DGame game = (NG2DGame)go.getGame();
            NGGameEngineMemoryManager mm = game.getMemoryManager();
            NGGameEngineMemoryAddress objectAddress = go.getMemoryAddress();
            NGGameEngineMemoryAddress objectNewAddress = new NGGameEngineMemoryAddress(objectAddress.getPage(), objectAddress.getBase() + 1, objectAddress.getOffset());
            MemoryCellValue value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), objectNewAddress);
            if (isObjectAccessible(value)) {
                moveObject(go, objectAddress, objectNewAddress);
            } else if (isObjectSlippery(value)) {
                objectNewAddress = new NGGameEngineMemoryAddress(objectAddress.getPage(), objectAddress.getBase(), objectAddress.getOffset() - 1);
                value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), objectNewAddress);
                if (isObjectAccessible(value)) {
                    objectNewAddress = new NGGameEngineMemoryAddress(objectAddress.getPage(), objectAddress.getBase() + 1, objectAddress.getOffset() - 1);
                    value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), objectNewAddress);
                    if (isObjectAccessible(value)) {
                        moveObject(go, objectAddress, objectNewAddress);
                    }
                }
                else {
                    objectNewAddress = new NGGameEngineMemoryAddress(objectAddress.getPage(), objectAddress.getBase(), objectAddress.getOffset() + 1);
                    value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), objectNewAddress);
                    if (isObjectAccessible(value)) {
                        objectNewAddress = new NGGameEngineMemoryAddress(objectAddress.getPage(), objectAddress.getBase() + 1, objectAddress.getOffset() + 1);
                        value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), objectNewAddress);
                        if (isObjectAccessible(value)) {
                            moveObject(go, objectAddress, objectNewAddress);
                        }
                    }
                }
            }
            else if (FCurrentGOPhysicsAction.getPhysicsAction() instanceof NGPhysicsAction2DMovement && isObjectDestructible(value)) {
                PlayerKilled(game);
            }
        }
    }

    public PhysicsPrincipleGravitation() {
        super();
        FSoundManager = null;
    }

    @Override
    public Boolean getAffectsByAction(NGCustomPhysicsAction aPhysicsAction) {
        if (aPhysicsAction instanceof NGPhysicsAction2DMovement || aPhysicsAction instanceof NGPhysicsActionTouch) {
            return true;
        }
        else {
            return super.getAffectsByAction(aPhysicsAction);
        }
    }

}
