package Boulderdash.Storage;

import Uniplay.Base.NGUniplayObject;

public class DiamondItem extends NGUniplayObject {

    protected Diamond FDiamond;

    public DiamondItem(Diamond aDiamond) {
        super();
        FDiamond = aDiamond;
    }

    public Diamond getDiamond() {
        return FDiamond;
    }

    public Boolean getCollected() {
        return FDiamond.getCollected();
    }

}
