package Boulderdash;

import Uniplay.Kernel.NGGameEngineModule;
import Uniplay.Kernel.NGGameEngineModuleManager;

public class BoulderdashModule extends NGGameEngineModule {

    @Override
    protected void CreateSubComponents() {
        super.CreateSubComponents();
        StageManager manager = new StageManager(this, "StageManager");
        addSubComponent(manager);
        registerEventHandler(new StageManagerEventHandlerKernelStarted(manager));
    }

    @Override
    protected void AfterInitialize() {
        super.AfterInitialize();
        registerObject("Boulderdash.StageManager.Gamefield.Layer1", getStageManager().getGameFieldCanvas());
    }

    protected StageManager getStageManager() {
        return (StageManager)getSubComponent("StageManager");
    }

    public BoulderdashModule(NGGameEngineModuleManager aManager, String aName) {
        super(aManager, aName);
    }

}
