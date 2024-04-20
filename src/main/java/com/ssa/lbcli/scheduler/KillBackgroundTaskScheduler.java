package com.ssa.lbcli.scheduler;


import com.ssa.lbcli.process.ProcessManager;
import com.ssa.lbcli.startup.StartUpChecker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Author: Sheik Syed Ali
 */
public class KillBackgroundTaskScheduler extends Thread {
    private final Logger logger = LogManager.getLogger(KillBackgroundTaskScheduler.class);


    public void run(){
        logger.info("Debug: Kill begins--");
        ProcessManager.kill();
        logger.info("Debug: Kill ends--");

    }
}
