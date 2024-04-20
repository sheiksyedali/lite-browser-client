package com.ssa.lbcli.startup;

import com.ssa.lbcli.process.ProcessManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;

/**
 * Author: Sheik Syed Ali
 */
public class StartUpChecker {
    private final Logger logger = LogManager.getLogger(StartUpChecker.class);

    public boolean isEligible(){
        logger.info("Kill process ->");
        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("Kill process asynchronously");
                ProcessManager.kill();
            }
        }).start();

//        logger.info("Disable keys ->");
//        disableKeys();
        logger.info("Validate VM ->");
        noVMHost();
        logger.info("Validate internet ->");
        return disabledInternet();
//        return true;
    }

    public static boolean disabledInternet(){
        boolean isInternetDisabled = true;
        try{
            InetAddress googleAddress = InetAddress.getByName("www.google.com");
            boolean isReachable = googleAddress.isReachable(3000); // Timeout in milliseconds
            if(isReachable){
                JOptionPane.showMessageDialog(null, "Internet available, Going to terminate");
                isInternetDisabled = false;
            } else {
//                JOptionPane.showMessageDialog(null, "Internet not reachable");
            }
            System.out.println("Internet connection available: " + isReachable);

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return isInternetDisabled;
    }

    public boolean noVMHost(){
        boolean noVM = true;
        String osName = System.getProperty("os.name");
        String vmName = System.getProperty("java.vm.name");

        logger.info("OS: " + osName);
        logger.info("VM : " + vmName);
        return noVM;
    }

    private boolean disableKeys(){
        try {
            Runtime.getRuntime().exec("scripts\\AutoHotKey.exe scripts\\script.ahk");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
