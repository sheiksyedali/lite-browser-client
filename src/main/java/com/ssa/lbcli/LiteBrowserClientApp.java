package com.ssa.lbcli;

import com.ssa.lbcli.client.LiteBrowserClient;
import com.ssa.lbcli.del.GlobalKeyboardHook;
import com.ssa.lbcli.del.GlobalKeyboardHook1;
import com.ssa.lbcli.listeners.PrintScreenListener;
import com.ssa.lbcli.scheduler.ScheduleManager;
import com.ssa.lbcli.startup.StartUpChecker;
import com.ssa.lbcli.window.BrowserWindow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.concurrent.CompletableFuture;

/**
 * Author: Sheik Syed Ali
 */
public class LiteBrowserClientApp {
    private static final Logger logger = LogManager.getLogger(LiteBrowserClientApp.class);
    public static void main(String[] args) throws Exception{

        SwingUtilities.invokeLater(() -> {
            try {

                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                if(new StartUpChecker().isEligible()) {
                    logger.info("Initialize browser ->");
                    LiteBrowserClient liteBrowserClient = new LiteBrowserClient();
                    BrowserWindow browserWindow = new BrowserWindow(liteBrowserClient);
                    browserWindow.focusUrlBar();

                    logger.info("Configure scheduler ->");
                    new ScheduleManager().schedule();

                    CompletableFuture.runAsync(() -> {
                        new PrintScreenListener().enable();
                    });
                } else {
                    System.exit(0);
                }


            } catch (Exception ex){
                ex.printStackTrace();
            }
        });


    }
}
