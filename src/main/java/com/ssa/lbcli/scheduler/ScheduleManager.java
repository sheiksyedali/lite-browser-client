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
    public ScheduleManager(){
        scheduledExecutorService = Executors.newScheduledThreadPool(THREAD_POOL_SIZE);
    }

    public void schedule(){
        scheduledExecutorService.scheduleWithFixedDelay(new InternetCheckScheduler(), 5, 30, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(new KillBackgroundTaskScheduler(), 5, 30, TimeUnit.SECONDS);
    }


}
