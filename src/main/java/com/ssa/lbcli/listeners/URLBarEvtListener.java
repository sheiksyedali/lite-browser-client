package com.ssa.lbcli.listeners;

import com.ssa.lbcli.client.LiteBrowserClient;
import com.ssa.lbcli.window.URLBarPanel;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Sheik Syed Ali
 */
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
            loadAndDisable();
        } else if(e.getSource() == urlBarPanel.getGoButton()){
            loadAndDisable();
        } else if(e.getSource() == urlBarPanel.getFinishButton()){
            new ShutdownListener().shutDown(true);
        } else if(e.getSource() == urlBarPanel.getExitButton()){
            new ShutdownListener().shutDown(false);
        }
    }

    private void loadAndDisable(){
        liteBrowserClient.getBrowser().loadURL(urlBarPanel.getUrlField().getText());
        urlBarPanel.getUrlField().setEditable(false);
        urlBarPanel.getGoButton().setEnabled(false);
    }

}
