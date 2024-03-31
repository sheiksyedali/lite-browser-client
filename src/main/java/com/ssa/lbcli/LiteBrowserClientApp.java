package com.ssa.lbcli;

import com.ssa.lbcli.client.LiteBrowserClient;
import com.ssa.lbcli.scheduler.ScheduleManager;
import com.ssa.lbcli.startup.StartUpChecker;
import com.ssa.lbcli.window.BrowserWindow;

import javax.swing.*;

/**
 * Author: Sheik Syed Ali
 */
public class LiteBrowserClientApp {
    public static void main(String[] args) throws Exception{

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        if(new StartUpChecker().isEligible()){
            LiteBrowserClient liteBrowserClient = new LiteBrowserClient();
            BrowserWindow browserWindow = new BrowserWindow(liteBrowserClient);

            new ScheduleManager().schedule();
        }
    }
}
