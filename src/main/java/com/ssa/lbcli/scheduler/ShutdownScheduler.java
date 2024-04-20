package com.ssa.lbcli.scheduler;

import com.ssa.lbcli.startup.StartUpChecker;

public class ShutdownScheduler extends Thread{

    public void run(){
        System.exit(0);
    }
}
