package com.ssa.lbcli.listeners;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.handler.CefLifeSpanHandlerAdapter;

public class BrowserLifeSpanHandler extends CefLifeSpanHandlerAdapter {
    @Override
    public boolean onBeforePopup(CefBrowser browser, CefFrame frame, String target_url, String target_frame_name) {
        System.out.println("Pop up before");

        return super.onBeforePopup(browser, frame, target_url, target_frame_name);
    }

    @Override
    public void onAfterCreated(CefBrowser browser) {
        System.out.println("Pop up created");

        super.onAfterCreated(browser);
    }

    @Override
    public void onAfterParentChanged(CefBrowser browser) {
        System.out.println("Pop up parent changed");

        super.onAfterParentChanged(browser);
    }

    @Override
    public boolean doClose(CefBrowser browser) {
        System.out.println("Pop up close");

        return super.doClose(browser);
    }

    @Override
    public void onBeforeClose(CefBrowser browser) {
        System.out.println("Pop up before close");

        super.onBeforeClose(browser);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
