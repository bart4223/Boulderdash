package Boulderdash.Storage;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Graphics.NGDisplayControllerLayerProp;

import java.util.ArrayList;

public abstract class CustomSprite extends NGUniplayObject {

    protected Integer FID;

    public final static Integer ID  = -1;

    public CustomSprite() {
        super();
        FID = ID;
    }

    public Integer getID() {
        return FID;
    }

    public void setID(Integer aID) {
        FID = aID;
    }

    public ArrayList<NGDisplayControllerLayerProp> getDisplayControllerPropValues() {
        return new ArrayList<NGDisplayControllerLayerProp>();
    }

    @Override
    public Object getProperty(Object aObject, String aName) {
        if (aName.equals("DisplayControllerName")) {
            return "DEFAULT";
        }
        else {
            return super.getProperty(aObject, aName);
        }
    }

}
