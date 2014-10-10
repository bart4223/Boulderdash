package Boulderdash.Physics;

import Uniplay.Physics.NG2DNewtonPhysicsPrinciple;
import Uniplay.Physics.NGCustomPhysicsAction;
import Uniplay.Physics.NGPhysicsAction2DMovement;

public class PhysicsPrincipleGravitation extends NG2DNewtonPhysicsPrinciple {

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
