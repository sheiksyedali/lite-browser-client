package com.ssa.lbcli.window;

import com.ssa.lbcli.client.LiteBrowserClient;
import org.cef.CefApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.InetAddress;

public class BrowserWindow extends JFrame {
    private LiteBrowserClient liteBrowserClient;
    public BrowserWindow(LiteBrowserClient liteBrowserClient){
        this.liteBrowserClient = liteBrowserClient;
        buildBrowserWindowUI();
        addListeners();
    }

    private void buildBrowserWindowUI(){
//        getContentPane().add(liteBrowserClient.getAddress_(), BorderLayout.NORTH);
        getContentPane().add(new URLBarPanel(liteBrowserClient), BorderLayout.NORTH);
        getContentPane().add(liteBrowserClient.getBrowserUI(), BorderLayout.CENTER);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    private void addListeners(){
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {
                if (event instanceof WindowEvent) {
                    WindowEvent windowEvent = (WindowEvent) event;
                    if (windowEvent.getID() == WindowEvent.WINDOW_ACTIVATED) {
                        toFront(); // Bring the frame to the front
                    }
                }
            }
        }, AWTEvent.WINDOW_EVENT_MASK);

        // Disable Alt+F4
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F4 && (e.getModifiers() & KeyEvent.ALT_MASK) != 0) {
                    return true; // Consume the Alt+F4 key event
                } else  if (e.getKeyCode() == KeyEvent.VK_TAB && (e.getModifiers() & KeyEvent.ALT_MASK) != 0) {
                    return true; // Consume the Alt+F4 key event
                } else if(e.getKeyCode() == 524){
                    return true;
                }
                return false;
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CefApp.getInstance().dispose();
                dispose();
            }
        });

        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                System.out.println("Focus gained");
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
//                System.out.println("Focus lost");

//                System.out.println("sheik: "+liteBrowserClient.getBrowser().getFocusedFrame().getName());
//                System.out.println("sheik"+liteBrowserClient.getBrowser().getFrameIdentifiers());
//                JFrame lostFocusFrame = (JFrame) e.getSource();
//                lostFocusFrame.toFront();
//
//                JOptionPane.showMessageDialog(null, "Window lost");
            }
        });




    }

    public void dispose(){
        CefApp.getInstance().dispose();
        dispose();
    }
}
