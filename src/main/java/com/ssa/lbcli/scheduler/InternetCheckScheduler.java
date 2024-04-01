package com.ssa.lbcli.scheduler;

import com.ssa.lbcli.startup.StartUpChecker;


/**
 * Author: Sheik Syed Ali
 */
public class InternetCheckScheduler extends Thread {

    public void run(){
        if(!StartUpChecker.disabledInternet()){
            System.exit(0);
        }
    }
}
