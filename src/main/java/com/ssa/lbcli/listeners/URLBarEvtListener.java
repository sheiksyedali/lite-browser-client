package com.ssa.lbcli.listeners;

import com.ssa.lbcli.client.LiteBrowserClient;
import com.ssa.lbcli.window.URLBarPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class URLBarEvtListener implements ActionListener {
    private URLBarPanel urlBarPanel;
    private LiteBrowserClient liteBrowserClient;


    public URLBarEvtListener(URLBarPanel urlBarPanel, LiteBrowserClient liteBrowserClient){
        this.urlBarPanel = urlBarPanel;
        this.liteBrowserClient = liteBrowserClient;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == urlBarPanel.getUrlField()){
            liteBrowserClient.getBrowser().loadURL(((JTextField) e.getSource()).getText());
            System.out.println("Url field");

        } else if(e.getSource() == urlBarPanel.getGoButton()){
            System.out.println("go button");

        } else if(e.getSource() == urlBarPanel.getFinishButton()){
            System.exit(0);
        }
    }
}
