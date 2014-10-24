package Boulderdash.Graphics;

import Uniwork.Visuals.NGDisplayControllerLayerItem;
import Uniwork.Visuals.NGImageDisplayController;
import javafx.scene.canvas.Canvas;

public class DisplayControllerDiamond extends NGImageDisplayController {

    @Override
    protected String resolveImageName(NGDisplayControllerLayerItem aItem, String aImageName) {
        if (aItem.getName().equals("Front")) {
            return String.format(aImageName, aItem.ImageNumber, DiamondIndex);
        }
        else {
            return super.resolveImageName(aItem, aImageName);
        }
    }

    public DisplayControllerDiamond(Canvas aCanvas, String aName) {
        this(aCanvas, aName, "");
    }

    public DisplayControllerDiamond(Canvas aCanvas, String aName, String aImagename) {
        super(aCanvas, aName, aImagename);
        DiamondIndex = 0;
    }

    public Integer DiamondIndex;

}
