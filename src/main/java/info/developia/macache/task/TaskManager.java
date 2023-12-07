package info.developia.macache.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class TaskManager {
    private final Thread mainThread;
    private final ScheduledExecutorService scheduler;
    private ScheduledFuture<?> schedulerFuture;

    public TaskManager() {
        mainThread = Thread.currentThread();
        scheduler = Executors.newScheduledThreadPool(1);
    }

    public boolean appIsAlive() {
        return mainThread.isAlive();
    }

    public void schedule(Runnable delExpiredKeys, long cacheValidPeriodInMillis) {
        schedulerFuture = scheduler.scheduleAtFixedRate(() -> {
            if (appIsAlive()) {
                delExpiredKeys.run();
            } else {
                shutdown();
            }
        }, 0, cacheValidPeriodInMillis, MILLISECONDS);
    }

    public void shutdown() {
        schedulerFuture.cancel(true);
        scheduler.shutdown();
    }
}
