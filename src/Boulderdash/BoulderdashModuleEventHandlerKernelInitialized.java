package Boulderdash;

import Uniplay.Kernel.NGGameEngineEventHandlerKernelInitialized;

public class BoulderdashModuleEventHandlerKernelInitialized extends NGGameEngineEventHandlerKernelInitialized {

    protected BoulderdashModule FModule;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        FModule.getBoulderdash().Initialize();
    }

    public BoulderdashModuleEventHandlerKernelInitialized(BoulderdashModule aModule) {
        super();
        FModule = aModule;
    }

}
