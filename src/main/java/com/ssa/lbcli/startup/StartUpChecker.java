package com.ssa.lbcli.startup;

import javax.swing.*;
import java.net.InetAddress;

/**
 * Author: Sheik Syed Ali
 */
public class StartUpChecker {

    public boolean isEligible(){
        //disabledInternet();
        noVMHost();
        //change this later to check both
        return disabledInternet();
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

        System.out.println("OS: " + osName);
        System.out.println("VM : " + vmName);
        return noVM;
    }

}
