package com.ssa.lbcli.scheduler;


import com.ssa.lbcli.process.ProcessManager;

/**
 * Author: Sheik Syed Ali
 */
public class KillBackgroundTaskScheduler extends Thread {
//    private Logger logger = LogManger.getLogger(KillBackgroundTaskScheduler.class.getName());


    public void run(){
        ProcessManager.kill();

    }
}
