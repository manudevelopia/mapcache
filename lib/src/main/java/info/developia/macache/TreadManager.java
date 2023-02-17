package info.developia.macache;

public class TreadManager {
    private final Thread mainThread;

    public TreadManager() {
        mainThread = Thread.currentThread();
    }

    public boolean appIsAlive() {
        return mainThread.isAlive();
    }
}
