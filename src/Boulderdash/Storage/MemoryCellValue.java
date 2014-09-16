package Boulderdash.Storage;

import Boulderdash.Graphics.BoulderdashSprite;
import Uniplay.Kernel.NGGameEngineMemoryObjectCellValue;

public class MemoryCellValue extends NGGameEngineMemoryObjectCellValue {

    protected BoulderdashSprite getSprite() {
        return (BoulderdashSprite)getObject();
    }

    public MemoryCellValue() {
        super();
    }

    @Override
    public Integer getInteger() {
        return getSprite().getID();
    }

}
