package Boulderdash;

import Uniplay.Kernel.NGGameEngineEventHandlerKernelInitialized;

public class BoulderdashModuleEventHandlerKernelInitialized extends NGGameEngineEventHandlerKernelInitialized {

    protected BoulderdashModule FModule;

    @Override
    protected void DoHandleEvent() {
        super.DoHandleEvent();
        FModule.getBoulderdash().addTestPlayers();
    }

    public BoulderdashModuleEventHandlerKernelInitialized(BoulderdashModule aModule) {
        super();
        FModule = aModule;
    }

}
