package com.ssa.lbcli.listeners;

import org.cef.browser.CefBrowser;
import org.cef.handler.CefFocusHandlerAdapter;

import java.awt.*;

public class BrowserFocusHandlerAdapter extends CefFocusHandlerAdapter {
    private boolean browserFocus_ = false;

    public BrowserFocusHandlerAdapter(boolean browserFocus_){
        this.browserFocus_ = browserFocus_;
    }
    @Override
    public void onGotFocus(CefBrowser browser) {
        if (browserFocus_) return;
        browserFocus_ = true;
        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
        browser.setFocus(true);
    }

    @Override
    public void onTakeFocus(CefBrowser browser, boolean next) {
        browserFocus_ = false;
    }

}
