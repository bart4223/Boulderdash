package Boulderdash.Graphics;

import Uniwork.Visuals.NGDisplayController;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class DisplayControllerNotificationArea extends NGDisplayController {

    protected Color FNotifyBackColor;
    protected Color FNotifyBorderColor;
    protected Double FNotifyWidth;
    protected Double FNotifyBorderWidth;

    @Override
    protected void DoRender() {
        super.DoRender();
        FGC.setFill(FNotifyBackColor);
        FGC.fillRect(0, 0, FWidth, FNotifyWidth);
        FGC.fillRect(0, FHeight - FNotifyWidth, FWidth, FHeight);
        FGC.fillRect(0, FNotifyWidth, FNotifyWidth, FHeight - FNotifyWidth);
        FGC.fillRect(FWidth - FNotifyWidth, FNotifyWidth, FWidth, FHeight - FNotifyWidth);
        FGC.setStroke(FNotifyBorderColor);
        FGC.setLineWidth(FNotifyBorderWidth);
        FGC.strokeRect(0, FHeight - FNotifyWidth + 1, FWidth, FHeight);
        FGC.strokeRect(0, FHeight - FNotifyWidth + 1, FNotifyWidth - 1, FHeight);
        FGC.strokeRect(FWidth - FNotifyWidth + 1, FHeight - FNotifyWidth + 1, FWidth, FHeight);
        FGC.strokeRect(FNotifyWidth - 1, FNotifyWidth - 1, FWidth - 2 * (FNotifyWidth - 1), FHeight - 2 * (FNotifyWidth - 1));
    }

    public DisplayControllerNotificationArea(Canvas aCanvas) {
        this(aCanvas,"");
    }

    public DisplayControllerNotificationArea(Canvas aCanvas, String aName) {
        super(aCanvas, aName);
        FNotifyBackColor = Color.LIGHTSLATEGREY;
        FNotifyBorderColor = Color.BLACK;
        FNotifyWidth = 40.0;
        FNotifyBorderWidth = 2.0;
    }

    public Color getNotifyBackColor() {
        return FNotifyBackColor;
    }

    public Double getNotifyWidth() {
        return FNotifyWidth;
    }

}
