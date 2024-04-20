package com.ssa.lbcli.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Author: Sheik Syed Ali
 */
public class ScheduleManager {

    protected final int THREAD_POOL_SIZE = 5;
    private ScheduledExecutorService scheduledExecutorService;

    private static ScheduleManager scheduleManager = null;
    public ScheduleManager(){
        scheduledExecutorService = Executors.newScheduledThreadPool(THREAD_POOL_SIZE);
    }

    public void schedule(){
        scheduledExecutorService.scheduleWithFixedDelay(new InternetCheckScheduler(), 5, 30, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(new KillBackgroundTaskScheduler(), 60, 60, TimeUnit.SECONDS);
    }

    public void shutDown(){
        scheduledExecutorService.schedule(new ShutdownScheduler(),5, TimeUnit.SECONDS);
    }

    public static ScheduleManager getInstance(){
        return (scheduleManager == null) ? new ScheduleManager(): scheduleManager;
    }

}
