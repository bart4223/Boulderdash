package Boulderdash.Storage;

import Uniplay.Base.NGUniplayObject;

public abstract class BoulderdashCustomSprite extends NGUniplayObject {

    protected Integer FID;

    public final static Integer ID  = -1;

    public BoulderdashCustomSprite() {
        super();
        FID = ID;
    }

    public Integer getID() {
        return FID;
    }

    public void setID(Integer aID) {
        FID = aID;
    }

}
