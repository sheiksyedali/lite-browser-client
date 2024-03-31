package com.ssa.lbcli.scheduler;

import com.ssa.lbcli.log.LogManger;

import java.util.logging.Logger;

/**
 * Author: Sheik Syed Ali
 */
public class InternetCheckScheduler extends Thread {
//    private Logger logger = LogManger.getLogger(InternetCheckScheduler.class.getName());

    public void run(){
        for(int i=0;i<5;i++){
            System.out.println("Internet check: "+i);
//            logger.info("Internet check: "+i);

            try{
                Thread.sleep(1000);
            } catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
}
