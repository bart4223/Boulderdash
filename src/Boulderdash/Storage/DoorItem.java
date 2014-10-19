package Boulderdash.Storage;

import Uniplay.Base.NGUniplayObject;

public class DoorItem extends NGUniplayObject {

    protected Door FDoor;

    public DoorItem(Door aDoor) {
        super();
        FDoor = aDoor;
    }

    public Door getDoor() {
        return FDoor;
    }

}
