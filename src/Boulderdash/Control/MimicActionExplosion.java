package Boulderdash.Control;

import Boulderdash.Graphics.*;
import Boulderdash.Storage.ExplosionCenter;
import Boulderdash.Storage.MemoryCellValue;
import Uniplay.Control.NGControlMimicManager;
import Uniplay.Control.NGControlMimicPeriodicAction;
import Uniplay.Kernel.NGGameEngineMemoryAddress;
import Uniplay.Kernel.NGGameEngineMemoryCellValueItem;
import Uniplay.Kernel.NGGameEngineMemoryManager;
import Uniplay.Kernel.NGGameEngineMemoryObjectCellValue;
import Uniplay.Storage.NG2DGame;
import Uniplay.Storage.NG2DGameCharacter;
import Uniplay.Storage.NGCustomGame;
import Uniplay.Storage.NGCustomGameObject;

import java.util.ArrayList;

public class MimicActionExplosion extends NGControlMimicPeriodicAction {

    protected ArrayList<NGGameEngineMemoryCellValueItem> FExplosionCenters;
    protected ExplosionCenter FExplosionCenter;

    protected static Boolean isObjectRupturable(MemoryCellValue aCellValue) {
        return aCellValue.getObject() instanceof SpriteAir || aCellValue.getObject() instanceof SpriteEarth || aCellValue.getObject() instanceof SpriteDiamond || aCellValue.getObject() instanceof SpriteBoulder;
    }

    protected void addExplosionCenter(NGGameEngineMemoryAddress aAddress) {
        NG2DGame game = getGame();
        NGGameEngineMemoryManager mm = game.getMemoryManager();
        MemoryCellValue value = (MemoryCellValue)mm.getCellValue(game.getMemoryName(), aAddress);
        if (value != null && isObjectRupturable(value)) {
            FExplosionCenters.add(new NGGameEngineMemoryCellValueItem(aAddress, new NGGameEngineMemoryObjectCellValue(new SpriteExplosion(FExplosionCenter))));
        }
    }

    @Override
    protected void DoActivate() {
        super.DoActivate();
        FExplosionCenters.clear();
        NG2DGame game = getGame();
        NGGameEngineMemoryManager mm = game.getMemoryManager();
        if (StartObject instanceof NG2DGameCharacter) {
            NG2DGameCharacter gc = (NG2DGameCharacter)StartObject;
            NGGameEngineMemoryAddress address = gc.getMemoryAddress();
            for (int y = 0; y < Intensity; y++) {
                for (int x = 0; x < Intensity; x++) {
                    addExplosionCenter(new NGGameEngineMemoryAddress(address.getPage(), address.getBase() - y, address.getOffset() - x));
                    if (x > 0) {
                        addExplosionCenter(new NGGameEngineMemoryAddress(address.getPage(), address.getBase() - y, address.getOffset() + x));
                    }
                    if (y > 0) {
                        addExplosionCenter(new NGGameEngineMemoryAddress(address.getPage(), address.getBase() + y, address.getOffset() - x));
                        if (x > 0) {
                            addExplosionCenter(new NGGameEngineMemoryAddress(address.getPage(), address.getBase() + y, address.getOffset() + x));
                        }
                    }
                }
            }
            mm.setCellsValue(game.getMemoryName(), FExplosionCenters);
        }
    }

    @Override
    protected void DoHandleTick() {
        super.DoHandleTick();
        NG2DGame game = getGame();
        NGGameEngineMemoryManager mm = game.getMemoryManager();
        FExplosionCenter.Explosion();
        for (NGGameEngineMemoryCellValueItem item : FExplosionCenters) {
            mm.refreshCell(game.getMemoryName(), item.getAddress());
        }
    }

    @Override
    public NG2DGame getGame() {
        return (NG2DGame)super.getGame();
    }

    public MimicActionExplosion(NGControlMimicManager aManager, NGCustomGame aGame, String aName) {
        super(aManager, aGame, aName, Kind.temporary);
        FExplosionCenters = new ArrayList<NGGameEngineMemoryCellValueItem>();
        FExplosionCenter = new ExplosionCenter(aGame);
        StartObject = null;
        Intensity = 3;
    }

    public NGCustomGameObject StartObject;

    public Integer Intensity;

}
