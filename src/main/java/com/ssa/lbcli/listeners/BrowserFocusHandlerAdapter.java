package com.ssa.lbcli.listeners;

import com.ssa.lbcli.client.LiteBrowserClient;
import org.cef.browser.CefBrowser;
import org.cef.handler.CefFocusHandlerAdapter;

import java.awt.*;

public class BrowserFocusHandlerAdapter extends CefFocusHandlerAdapter {

    private LiteBrowserClient liteBrowserClient;
    public BrowserFocusHandlerAdapter(LiteBrowserClient liteBrowserClient){
        this.liteBrowserClient = liteBrowserClient;

    }
    @Override
    public void onGotFocus(CefBrowser browser) {
        if (liteBrowserClient.isBrowserFocus()) return;
        liteBrowserClient.setBrowserFocus(true);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
        browser.setFocus(true);
    }

    @Override
    public void onTakeFocus(CefBrowser browser, boolean next) {
        liteBrowserClient.setBrowserFocus(false);
    }

}
