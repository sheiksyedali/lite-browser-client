package com.ssa.lbcli.window;

import com.ssa.lbcli.client.LiteBrowserClient;
import com.ssa.lbcli.listeners.KeyListener;
import com.ssa.lbcli.listeners.URLBarEvtListener;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.handler.CefDisplayHandlerAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

/**
 * Author: Sheik Syed Ali
 */
public class URLBarPanel extends JPanel {
    private JTextField urlField;
    private JButton goButton;
    private JButton finishButton;

    private JButton exitButton;

    private LiteBrowserClient liteBrowserClient;

    public URLBarPanel(LiteBrowserClient liteBrowserClient){
        this.liteBrowserClient = liteBrowserClient;
        setLayout(new FlowLayout());
//        setLayout(null);
        //setSize(500, 500);
//        setBackground(Color.BLUE);
//        setMinimumSize(new Dimension(100, 100));
//        setPreferredSize(new Dimension(100, 200));
        buildUI();
    }

    private void buildUI(){
        URLBarEvtListener evtListener = new URLBarEvtListener(this, liteBrowserClient);
        urlField = new JTextField(75);
//      urlField.setSize(100, 100);
//      urlField.setBounds(0, 0, 200, 100);
        urlField.addActionListener(evtListener);
        liteBrowserClient.getCefClient().addDisplayHandler(new CefDisplayHandlerAdapter() {
            @Override
            public void onAddressChange(CefBrowser browser, CefFrame frame, String url) {
                urlField.setText(url);
            }
        });
        urlField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!liteBrowserClient.isBrowserFocus()) return;
                liteBrowserClient.setBrowserFocus(false);
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                urlField.requestFocus();
            }
        });

        urlField.addKeyListener(new KeyListener());


        add(urlField);

        goButton = new JButton("Go");
        goButton.addActionListener(evtListener);
        add(goButton);

        finishButton = new JButton("End Session");
        finishButton.addActionListener(evtListener);
        add(finishButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(evtListener);
        add(exitButton);
    }

    public JButton getFinishButton() {
        return finishButton;
    }

    public JButton getGoButton() {
        return goButton;
    }

    public JTextField getUrlField() {
        return urlField;
    }

    public JButton getExitButton() {
        return exitButton;
    }

}
