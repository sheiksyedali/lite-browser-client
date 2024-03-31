package com.ssa.lbcli;

import com.ssa.lbcli.client.LiteBrowserClient;
import com.ssa.lbcli.window.BrowserWindow;

public class LiteBrowserClientApp {
    public static void main(String[] args) throws Exception{
        LiteBrowserClient liteBrowserClient = new LiteBrowserClient();
        BrowserWindow browserWindow = new BrowserWindow(liteBrowserClient);
    }
}
