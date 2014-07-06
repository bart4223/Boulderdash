package Boulderdash;

import Uniplay.Kernel.NGGameEngineEventHandlerKernelStarted;

public class StageManagerEventHandlerKernelStarted extends NGGameEngineEventHandlerKernelStarted {

    protected StageManager FManager;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        FManager.showStages();
    }

    public StageManagerEventHandlerKernelStarted(StageManager aManager) {
        super();
        FManager = aManager;
    }

}
