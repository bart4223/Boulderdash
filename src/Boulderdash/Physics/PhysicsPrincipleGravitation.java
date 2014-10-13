package Boulderdash.Physics;

import Boulderdash.Graphics.SpriteAir;
import Boulderdash.Storage.MemoryCellValue;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Kernel.NGGameEngineMemoryObjectCellValue;
import Uniplay.Physics.*;
import Uniplay.Storage.NG2DGame;
import Uniplay.Storage.NG2DGameObject;

public class PhysicsPrincipleGravitation extends NG2DNewtonPhysicsPrinciple {

    protected Boolean isObjectAccessible(MemoryCellValue aCellValue) {
        return aCellValue.getObject() instanceof SpriteAir;
    }

    @Override
    protected void DoExecute() {
        super.DoExecute();
        if (FCurrentGOPhysicsAction.getPhysicsAction() instanceof NGPhysicsAction2DMovement) {
            if (FCurrentGOPhysicsAction.getAffectedObject() instanceof NG2DGameObject) {
                NG2DGameObject go = (NG2DGameObject)FCurrentGOPhysicsAction.getAffectedObject();
                NG2DGame game = (NG2DGame)go.getGame();
                NGGameEngineMemoryManager mm = game.getMemoryManager();
                NGGameEngineMemoryAddress objectAddress = go.getMemoryAddress();
                NGGameEngineMemoryAddress objectNewAddress = new NGGameEngineMemoryAddress(objectAddress.getPage(), objectAddress.getBase() + 1, objectAddress.getOffset());
                MemoryCellValue value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), objectNewAddress);
                if (isObjectAccessible(value)) {
                    NGGameEngineMemoryObjectCellValue cellvalue = (NGGameEngineMemoryObjectCellValue)mm.getCellValue(game.getMemoryName(), objectAddress);
                    mm.setCellValueAsObject(game.getMemoryName(), objectNewAddress, cellvalue.getObject());
                    mm.refreshCell(game.getMemoryName(), objectNewAddress);
                    game.setObjectPosition(go, objectNewAddress.getOffset(), objectNewAddress.getBase());
                    mm.setCellValueAsObject(game.getMemoryName(), objectAddress, new SpriteAir());
                    mm.refreshCell(game.getMemoryName(), objectAddress);
                    NGObjectPhysicsProcessor pp = getPhysicsProcessor();
                    pp.addQueue(new NGGameObjectPhysicsAction(FCurrentGOPhysicsAction.getTriggerObject(), go, new NGPhysicsAction2DMovement(go.getPosition())));
                }
            }
        }
    }

    public PhysicsPrincipleGravitation() {
        super();
    }

    @Override
    public Boolean getAffectsByAction(NGCustomPhysicsAction aPhysicsAction) {
        if (aPhysicsAction instanceof NGPhysicsAction2DMovement) {
            return true;
        }
        else {
            return super.getAffectsByAction(aPhysicsAction);
        }
    }

}
