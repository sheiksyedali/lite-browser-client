package com.ssa.lbcli.listeners;

import com.ssa.lbcli.client.LiteBrowserClient;
import com.ssa.lbcli.process.ProcessManager;
import com.ssa.lbcli.window.Alert;
import com.ssa.lbcli.window.BrowserWindow;
import com.ssa.lbcli.window.URLBarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;

/**
 * Author: Sheik Syed Ali
 */
public class BrowserFocusListener implements WindowFocusListener {

    private LiteBrowserClient liteBrowserClient;

    private URLBarPanel urlBarPanel;
    private BrowserWindow browserWindow;
    public BrowserFocusListener(LiteBrowserClient liteBrowserClient, URLBarPanel urlBarPanel, BrowserWindow browserWindow){
        this.liteBrowserClient = liteBrowserClient;
        this.urlBarPanel = urlBarPanel;
        this.browserWindow = browserWindow;
    }
    @Override
    public void windowGainedFocus(WindowEvent e) {
    }

    @Override
    public void windowLostFocus(WindowEvent e) {

//        ProcessManager.backToDaddy();

        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
        urlBarPanel.getUrlField().requestFocus();
        urlBarPanel.getUrlField().grabFocus();
        urlBarPanel.getUrlField().setCaretPosition(0);

//        System.out.println("Focus lost");


//        System.out.println("sheik: "+liteBrowserClient.getBrowser().getFocusedFrame().getName());
//        System.out.println("sheik"+liteBrowserClient.getBrowser().getFrameIdentifiers());
//        JFrame lostFocusFrame = (JFrame) e.getSource();
//        lostFocusFrame.toFront();
//
//        JOptionPane.showMessageDialog(null, "You are switched to another window, Application going to shutdown");
        Alert.unauthorizedAlert();

    }
}
