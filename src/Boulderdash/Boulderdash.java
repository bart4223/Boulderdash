package Boulderdash;

import Uniplay.Base.NGUniplayObject;
import Uniplay.NG2DGameEngine;

public class Boulderdash extends NGUniplayObject {

    protected NG2DGameEngine FGameEngine;

    public Boulderdash() {
        super();
        FGameEngine = new NG2DGameEngine(this);
        //FGameEngine.setDebugLevel(10);
    }

    public void Run() {
        FGameEngine.Startup();
    }

    public void Stop() {
        FGameEngine.Shutdown();
    }

}
