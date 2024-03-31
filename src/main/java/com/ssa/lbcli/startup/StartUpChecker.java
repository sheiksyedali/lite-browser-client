package com.ssa.lbcli.startup;

import javax.swing.*;
import java.net.InetAddress;

public class StartUpChecker {

    public boolean isEligible(){
        boolean temp = disabledInternet() && noVMHost();
        return true;
    }

    public boolean disabledInternet(){
        boolean isInternetDisabled = true;
        try{
            InetAddress googleAddress = InetAddress.getByName("www.google.com");
            boolean isReachable = googleAddress.isReachable(3000); // Timeout in milliseconds
            if(isReachable){
                JOptionPane.showMessageDialog(null, "Internet available");
                isInternetDisabled = false;
            } else {
                JOptionPane.showMessageDialog(null, "Internet not reachable");
            }
            System.out.println("Internet connection available: " + isReachable);

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return isInternetDisabled;
    }

    public boolean noVMHost(){
        boolean noVM = true;
        return noVM;
    }

}
