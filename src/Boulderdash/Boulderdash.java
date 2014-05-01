package Boulderdash;

import Uniplay.Base.NGGameEngine;
import Uniwork.Base.NGObject;

public class Boulderdash extends NGObject {

    protected NGGameEngine FGameEngine;

    public Boulderdash() {
        super();
        FGameEngine = new NGGameEngine(this);
        FGameEngine.setDebugLevel(1);
    }

    public void Run() {
        FGameEngine.Initialize();
        FGameEngine.Run();
    }

    public void Stop() {
        FGameEngine.Stop();
        FGameEngine.Finalize();
    }

}
