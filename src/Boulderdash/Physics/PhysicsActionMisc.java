package Boulderdash.Physics;

import Boulderdash.Graphics.BoulderdashSprite;
import Boulderdash.Graphics.SpriteBoulder;
import Boulderdash.Graphics.SpriteDiamond;
import Boulderdash.Storage.MemoryCellValue;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Physics.NGGameObjectPhysicsAction;
import Uniplay.Physics.NGObjectPhysicsProcessor;
import Uniplay.Physics.NGPhysicsActionTouch;
import Uniplay.Storage.NGCustomGame;
import Uniplay.Storage.NGCustomGameObject;

public abstract class PhysicsActionMisc {

    protected static Boolean isObjectTouchable(MemoryCellValue aCellValue) {
        return aCellValue.getObject() instanceof SpriteBoulder || aCellValue.getObject() instanceof SpriteDiamond;
    }

    public PhysicsActionMisc() {

    }

    public static void DetectObjectTouchObject(NGCustomGame aGame, NGObjectPhysicsProcessor aPhysicsProcessor, NGCustomGameObject aTriggerObject, NGGameEngineMemoryAddress aPlayerAddress) {
        NGGameEngineMemoryManager mm = aGame.getMemoryManager();;
        NGGameEngineMemoryAddress objectAddress = new NGGameEngineMemoryAddress(aPlayerAddress.getPage(), aPlayerAddress.getBase() - 1, aPlayerAddress.getOffset() - 1);
        MemoryCellValue value = (MemoryCellValue)mm.getCellValue(aGame.getMemoryName(), objectAddress);
        if (isObjectTouchable(value)) {
            NGCustomGameObject go = ((BoulderdashSprite)value.getObject()).getGameObject();
            aPhysicsProcessor.addQueue(new NGGameObjectPhysicsAction(aTriggerObject, go, new NGPhysicsActionTouch()));
        }
        objectAddress = new NGGameEngineMemoryAddress(aPlayerAddress.getPage(), aPlayerAddress.getBase() - 1, aPlayerAddress.getOffset());
        value = (MemoryCellValue)mm.getCellValue(aGame.getMemoryName(), objectAddress);
        if (isObjectTouchable(value)) {
            NGCustomGameObject go = ((BoulderdashSprite)value.getObject()).getGameObject();
            aPhysicsProcessor.addQueue(new NGGameObjectPhysicsAction(aTriggerObject, go, new NGPhysicsActionTouch()));
        }
        objectAddress = new NGGameEngineMemoryAddress(aPlayerAddress.getPage(), aPlayerAddress.getBase() - 1, aPlayerAddress.getOffset() + 1);
        value = (MemoryCellValue)mm.getCellValue(aGame.getMemoryName(), objectAddress);
        if (isObjectTouchable(value)) {
            NGCustomGameObject go = ((BoulderdashSprite)value.getObject()).getGameObject();
            aPhysicsProcessor.addQueue(new NGGameObjectPhysicsAction(aTriggerObject, go, new NGPhysicsActionTouch()));
        }
        objectAddress = new NGGameEngineMemoryAddress(aPlayerAddress.getPage(), aPlayerAddress.getBase(), aPlayerAddress.getOffset() - 1);
        value = (MemoryCellValue)mm.getCellValue(aGame.getMemoryName(), objectAddress);
        if (isObjectTouchable(value)) {
            NGCustomGameObject go = ((BoulderdashSprite)value.getObject()).getGameObject();
            aPhysicsProcessor.addQueue(new NGGameObjectPhysicsAction(aTriggerObject, go, new NGPhysicsActionTouch()));
        }
        objectAddress = new NGGameEngineMemoryAddress(aPlayerAddress.getPage(), aPlayerAddress.getBase(), aPlayerAddress.getOffset() + 1);
        value = (MemoryCellValue)mm.getCellValue(aGame.getMemoryName(), objectAddress);
        if (isObjectTouchable(value)) {
            NGCustomGameObject go = ((BoulderdashSprite)value.getObject()).getGameObject();
            aPhysicsProcessor.addQueue(new NGGameObjectPhysicsAction(aTriggerObject, go, new NGPhysicsActionTouch()));
        }
    }

}
