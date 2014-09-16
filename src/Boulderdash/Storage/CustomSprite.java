package Boulderdash.Storage;

import Uniplay.Base.NGUniplayObject;
import Uniplay.Graphics.NGDisplayControllerLayerProp;
import Boulderdash.BoulderdashConsts;
import Uniplay.NGGameEngineConstants;

import java.util.ArrayList;

public abstract class CustomSprite extends NGUniplayObject {

    protected Integer FID;

    protected String getRenderEngineName() {
        return "DEFAULT";
    }

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
        if (aName.equals(NGGameEngineConstants.PROP_GRAPHIC_RENDERENGINE_NAME)) {
            return getRenderEngineName();
        }
        else if (aName.equals(NGGameEngineConstants.PROP_GRAPHIC_DISPLAYCONTROLLER_NAME)) {
            return getDisplayControllerName();
        }
        else if (aName.equals(NGGameEngineConstants.PROP_GRAPHIC_DISPLAYCONTROLLER_LAYER_PROPS)) {
            return getDisplayControllerLayerProps();
        }
        else {
            return super.getProperty(aObject, aName);
        }
    }

}
