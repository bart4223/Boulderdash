package Boulderdash.Storage;

import Uniplay.Kernel.NGGameEngineMemoryObjectCellValue;

public class MemoryCellValue extends NGGameEngineMemoryObjectCellValue {

    protected CustomSprite getSprite() {
        return (CustomSprite)getObject();
    }

    public MemoryCellValue() {
        super();
    }

    @Override
    public Integer getInteger() {
        return getSprite().getID();
    }

}
