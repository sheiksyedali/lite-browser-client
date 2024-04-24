package com.ssa.lbcli.scheduler;


import com.ssa.lbcli.process.ProcessManager;
import com.ssa.lbcli.startup.StartUpChecker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;

/**
 * Author: Sheik Syed Ali
 */
public class KillBackgroundTaskScheduler extends Thread {
    private final Logger logger = LogManager.getLogger(KillBackgroundTaskScheduler.class);


    public void run(){
        logger.info("Debug: Kill begins--");
//        ProcessManager.kill();
        CompletableFuture.runAsync(() -> {
            new ProcessManager().kill();
        });
//        ProcessManager.kill();
        logger.info("Debug: Kill ends--");

    }
}
