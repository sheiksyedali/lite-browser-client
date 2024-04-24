package com.ssa.lbcli.listeners;

import com.ssa.lbcli.process.ProcessManager;


public class ShutdownListener {

    public void shutDown(boolean notify){
//        killAHK();
        if(notify){
            notifyToService();
        }
        System.exit(0);
    }

    private void killAHK(){
        ProcessManager.killProcess("AutoHotKey.exe");
    }

    private void notifyToService(){

    }


}
