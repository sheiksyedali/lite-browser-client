package com.ssa.lbcli.window;

import com.ssa.lbcli.scheduler.ScheduleManager;

import javax.swing.*;

public class Alert extends JOptionPane {


    public static void unauthorizedAlert(){
        ScheduleManager.getInstance().shutDown();
        showMessageDialog(null, "Unauthorized action detected, Application going to shutdown");
//        System.out.println("alert");
    }


}
