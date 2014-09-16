package Boulderdash.Storage;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Graphics.NGDisplayControllerLayerProp;
import Boulderdash.BoulderdashConsts;

import java.util.ArrayList;

public abstract class CustomSprite extends NGUniplayObject {

    protected Integer FID;

    protected String getDisplayControllerName() {
        return "DEFAULT";
    }

    protected ArrayList<NGDisplayControllerLayerProp> getDisplayControllerLayerProps() {
        return new ArrayList<NGDisplayControllerLayerProp>();
    }

    public final static Integer ID = BoulderdashConsts.SPRITE_NONE;

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

    @Override
    public Object getProperty(Object aObject, String aName) {
        if (aName.equals("DisplayControllerName")) {
            return getDisplayControllerName();
        }
        else if (aName.equals("DisplayControllerLayerProps")) {
            return getDisplayControllerLayerProps();
        }
        else {
            return super.getProperty(aObject, aName);
        }
    }

}
