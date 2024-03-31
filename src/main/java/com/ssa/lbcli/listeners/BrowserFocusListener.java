package com.ssa.lbcli.listeners;

import com.ssa.lbcli.client.LiteBrowserClient;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * Author: Sheik Syed Ali
 */
public class BrowserFocusListener implements WindowFocusListener {

    private LiteBrowserClient liteBrowserClient;
    public BrowserFocusListener(LiteBrowserClient liteBrowserClient){
        this.liteBrowserClient = liteBrowserClient;
    }
    @Override
    public void windowGainedFocus(WindowEvent e) {
        System.out.println("Focus gained");
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        System.out.println("Focus lost");

        System.out.println("sheik: "+liteBrowserClient.getBrowser().getFocusedFrame().getName());
        System.out.println("sheik"+liteBrowserClient.getBrowser().getFrameIdentifiers());
        JFrame lostFocusFrame = (JFrame) e.getSource();
        lostFocusFrame.toFront();

        JOptionPane.showMessageDialog(null, "You are switched to another window, Application going to shutdown");

    }
}
