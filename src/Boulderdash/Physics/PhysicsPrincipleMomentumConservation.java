package Boulderdash.Physics;

import Boulderdash.Graphics.SpriteAir;
import Boulderdash.Storage.MemoryCellValue;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Kernel.NGGameEngineMemoryObjectCellValue;
import Uniplay.Physics.NG2DNewtonPhysicsPrinciple;
import Uniplay.Physics.NGCustomPhysicsAction;
import Uniplay.Physics.NGPhysicsAction2DImpuls;
import Uniplay.Storage.NG2DGame;
import Uniplay.Storage.NG2DGameCharacter;
import Uniplay.Storage.NG2DGameObject;

public class PhysicsPrincipleMomentumConservation extends NG2DNewtonPhysicsPrinciple {

    protected Boolean isObjectAccessible(MemoryCellValue aCellValue) {
        return aCellValue.getObject() instanceof SpriteAir;
    }

    @Override
    protected void DoExecute() {
        super.DoExecute();
        if (FCurrentGOPhysicsAction.getPhysicsAction() instanceof NGPhysicsAction2DImpuls) {
            NGPhysicsAction2DImpuls impuls = (NGPhysicsAction2DImpuls)FCurrentGOPhysicsAction.getPhysicsAction();
            if (FCurrentGOPhysicsAction.getAffectedObject() instanceof NG2DGameObject) {
                NG2DGameObject go = (NG2DGameObject)FCurrentGOPhysicsAction.getAffectedObject();
                NG2DGame game = (NG2DGame)go.getGame();
                NGGameEngineMemoryManager mm = game.getMemoryManager();
                NGGameEngineMemoryAddress objectAddress = go.getMemoryAddress();
                double x0 = go.getPosition().getX();
                double y0 = go.getPosition().getY();
                double x1 =  x0 + (int)(impuls.getDirection().getX() * impuls.getAmount());
                double y1 =  y0 + (int)(impuls.getDirection().getY() * impuls.getAmount());
                NGGameEngineMemoryAddress objectNewAddress = new NGGameEngineMemoryAddress(objectAddress.getPage(), (int)y1, (int)x1);
                MemoryCellValue value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), objectNewAddress);
                if (isObjectAccessible(value)) {
                    game.setObjectPosition(go, x1, y1);
                    NGGameEngineMemoryObjectCellValue cellvalue = (NGGameEngineMemoryObjectCellValue)mm.getCellValue(game.getMemoryName(), objectAddress);
                    mm.setCellValueAsObject(game.getMemoryName(), objectNewAddress, cellvalue.getObject());
                    if (FCurrentGOPhysicsAction.getTriggeredObject() instanceof NG2DGameCharacter) {
                        NG2DGameCharacter character = (NG2DGameCharacter)FCurrentGOPhysicsAction.getTriggeredObject();
                        NGGameEngineMemoryAddress playerAddress = character.getMemoryAddress();
                        value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), playerAddress);
                        mm.setCellValueAsObject(game.getMemoryName(), objectAddress, value.getObject());
                        game.setPCPosition(character, x0, y0);
                        mm.setCellValueAsObject(game.getMemoryName(), playerAddress, new SpriteAir());
                    }
                }
            }
        }
    }

    public PhysicsPrincipleMomentumConservation() {
        super();
    }

    @Override
    public Boolean getAffectsByAction(NGCustomPhysicsAction aPhysicsAction) {
        if (aPhysicsAction instanceof NGPhysicsAction2DImpuls) {
            return true;
        }
        else {
            return super.getAffectsByAction(aPhysicsAction);
        }
    }

}
