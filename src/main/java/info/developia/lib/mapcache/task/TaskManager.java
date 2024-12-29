package info.developia.lib.mapcache.task;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskManager implements AutoCloseable {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final ExecutorService taskExecutor = Executors.newVirtualThreadPerTaskExecutor();
    private final Thread mainThread = Thread.currentThread();
    private static TaskManager taskManager;

    private TaskManager() {
    }

    public static TaskManager getInstance() {
        if (taskManager == null) {
            taskManager = new TaskManager();
        }
        return taskManager;
    }

    public void schedule(Runnable task, Duration interval) {
        scheduler.schedule(() -> taskExecutor.execute(() -> {
            if (mainThread.isAlive()) task.run();
            else close();
        }), interval.toMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void close() {
        scheduler.shutdown();
    }
}
