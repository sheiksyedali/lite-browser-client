package com.ssa.lbcli;

import com.ssa.lbcli.client.LiteBrowserClient;
import com.ssa.lbcli.startup.StartUpChecker;
import com.ssa.lbcli.window.BrowserWindow;

public class LiteBrowserClientApp {
    public static void main(String[] args) throws Exception{
        if(new StartUpChecker().isEligible()){
            LiteBrowserClient liteBrowserClient = new LiteBrowserClient();
            BrowserWindow browserWindow = new BrowserWindow(liteBrowserClient);
        }
    }
}
