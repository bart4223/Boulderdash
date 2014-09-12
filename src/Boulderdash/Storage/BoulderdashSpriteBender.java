package Boulderdash.Storage;

import Uniplay.Graphics.NGDisplayControllerLayerProp;

import java.util.ArrayList;

public class BoulderdashSpriteBender extends BoulderdashCustomSprite {

    public enum Mode {footdown, footup}

    public enum Door {none, open, close}

    public final static Integer ID = 1;
    public final static Integer ID_UP = 3;

    protected Mode FMode;
    protected Door FDoor;

    public BoulderdashSpriteBender() {
        super();
        setMode(Mode.footdown);
        FDoor = Door.none;
    }

    public Mode getMode() {
        return FMode;
    }

    public void ToggleMode() {
        if (FMode == Mode.footdown) {
            setMode(Mode.footup);
        } else {
            setMode(Mode.footdown);
        }
    }

    public void setMode(Mode aMode) {
        FMode = aMode;
        switch (FMode) {
            case footdown:
                FID = ID;
                break;
            case footup:
                FID = ID_UP;
                break;
        }
    }

    public void setDoor(Door aValue) {
        FDoor = aValue;
    }

    public Door getDoor() {
        return FDoor;
    }

    @Override
    public ArrayList<NGDisplayControllerLayerProp> getDisplayControllerPropValues() {
        ArrayList<NGDisplayControllerLayerProp> props = super.getDisplayControllerPropValues();
        if (getDoor() == Door.none) {
            if (getMode() == BoulderdashSpriteBender.Mode.footdown) {
                props.add(new NGDisplayControllerLayerProp("Background", 1));
            } else
                props.add(new NGDisplayControllerLayerProp("Background", 3));
            props.add(new NGDisplayControllerLayerProp("LAYER01", 0));
        } else if (getDoor() == Door.open) {
            props.add(new NGDisplayControllerLayerProp("Background", 6));
            if (getMode() == Mode.footdown) {
                props.add(new NGDisplayControllerLayerProp("LAYER01", 1));
            } else
                props.add(new NGDisplayControllerLayerProp("LAYER01", 3));
        } else {
            props.add(new NGDisplayControllerLayerProp("Background", 7));
            if (getMode() == Mode.footdown) {
                props.add(new NGDisplayControllerLayerProp("LAYER01", 1));
            } else
                props.add(new NGDisplayControllerLayerProp("LAYER01", 3));
        }
        return props;
    }

}
