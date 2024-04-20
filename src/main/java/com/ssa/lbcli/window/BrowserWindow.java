package com.ssa.lbcli.window;

import com.ssa.lbcli.client.LiteBrowserClient;
import com.ssa.lbcli.listeners.BrowserFocusListener;
import org.cef.CefApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Author: Sheik Syed Ali
 */
public class BrowserWindow extends JFrame {
    private LiteBrowserClient liteBrowserClient;
    private URLBarPanel urlBarPanel;
    public BrowserWindow(LiteBrowserClient liteBrowserClient){
        this.liteBrowserClient = liteBrowserClient;
        urlBarPanel = new URLBarPanel(this.liteBrowserClient);
        buildBrowserWindowUI();
        addListeners();
    }

    private void buildBrowserWindowUI(){
        setTitle("Lite Browser Client");
        getContentPane().add(urlBarPanel, BorderLayout.NORTH);
        getContentPane().add(liteBrowserClient.getBrowserUI(), BorderLayout.CENTER);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    private void addListeners(){
        /*Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {
                if (event instanceof WindowEvent) {
                    WindowEvent windowEvent = (WindowEvent) event;
                    if (windowEvent.getID() == WindowEvent.WINDOW_ACTIVATED) {
                        System.out.println("Win changed...");
                        toFront(); // Bring the frame to the front
                        focusUrlBar();
                    }
                }
            }
        }, AWTEvent.WINDOW_EVENT_MASK);*/

        // Disable Alt+F4
//        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
//            @Override
//            public boolean dispatchKeyEvent(KeyEvent e) {
//                if (e.getID() == KeyEvent.KEY_PRESSED) {
//                    // Print the key code and key char
//                    System.out.println("Key Pressed: KeyCode=" + e.getKeyCode() + ", KeyChar=" + e.getKeyChar());
//                }
//                if (e.getKeyCode() == KeyEvent.VK_F4 && (e.getModifiers() & KeyEvent.ALT_MASK) != 0) {
//                    return true; // Consume the Alt+F4 key event
//                } else  if (e.getKeyCode() == KeyEvent.VK_TAB && (e.getModifiers() & KeyEvent.ALT_MASK) != 0) {
//                    return true; // Consume the Alt+F4 key event
//                } else if(e.getKeyCode() == 524){
//                    e.consume();
//                    toFront();
//                    requestFocus();
//                    return true;
//                }
//                return false;
//            }
//        });

        /*addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Un-authorized action: "+e.getKeyCode());
//                e.consume();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });*/

        /*addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CefApp.getInstance().dispose();
                dispose();
            }
        });*/

        addWindowFocusListener(new BrowserFocusListener(liteBrowserClient, urlBarPanel, this));
    }

    @Override
    public void addNotify() {
        super.addNotify();
        System.out.println("Frame initialized and components displayed");
        // Perform actions here
        focusUrlBar();
    }

    public void dispose(){
        CefApp.getInstance().dispose();
        dispose();
    }

    public void focusUrlBar(){
        urlBarPanel.getGoButton().requestFocus();
        urlBarPanel.getGoButton().grabFocus();
        urlBarPanel.getUrlField().setCaretPosition(0);
        urlBarPanel.getUrlField().requestFocusInWindow();
//        urlBarPanel.getUrlField().setText("sheik");
        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
        urlBarPanel.getUrlField().requestFocus();
        urlBarPanel.getUrlField().grabFocus();
    }
}
