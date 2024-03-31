package com.ssa.lbcli.client;

import com.ssa.lbcli.listeners.BrowserFocusHandlerAdapter;
import com.ssa.lbcli.listeners.BrowserLifeSpanHandler;
import me.friwi.jcefmaven.CefAppBuilder;
import me.friwi.jcefmaven.CefInitializationException;
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter;
import me.friwi.jcefmaven.UnsupportedPlatformException;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefMessageRouter;
import java.awt.*;
import java.io.IOException;

/**
 * Author: Sheik Syed Ali
 */
public class LiteBrowserClient {

    private static final long serialVersionUID = -5570653778104813836L;
//    private JTextField address_;
    private CefApp cefApp_;
    private CefClient client_;
    private CefBrowser browser_;
    private Component browerUI_;
    private boolean browserFocus_ = true;

    private boolean useOSR = false;
    private boolean isTransparent = false;
    private String startURL = "https://uapuat.lntedutech.com/";


    public LiteBrowserClient() throws UnsupportedPlatformException, CefInitializationException, IOException, InterruptedException{
        init();
    }

    private void init() throws UnsupportedPlatformException, CefInitializationException, IOException, InterruptedException{
        CefAppBuilder builder = new CefAppBuilder();
        builder.getCefSettings().windowless_rendering_enabled = useOSR;
        builder.setAppHandler(new MavenCefAppHandlerAdapter() {
            @Override
            public void stateHasChanged(org.cef.CefApp.CefAppState state) {
                if (state == CefApp.CefAppState.TERMINATED)
                    System.exit(0);
            }

        });

        builder.addJcefArgs(new String[]{
                "--enable-media-stream"
        });
//        builder.addJcefArgs(new String[]{
//                "--enable-media-stream",
//                "--user-agent=\"hello sheik\""
//
//        });
        cefApp_ = builder.build();
        client_ = cefApp_.createClient();
        CefMessageRouter msgRouter = CefMessageRouter.create();
        client_.addMessageRouter(msgRouter);


        client_.addLifeSpanHandler(new BrowserLifeSpanHandler());


        browser_ = client_.createBrowser(startURL, useOSR, isTransparent);

//        String javascriptCode = "var constraints = { video: { width: { ideal: 1920 }, height: { ideal: 1080 }, frameRate: { ideal: 30 } } };"
//                + "navigator.mediaDevices.getUserMedia(constraints)"
//                + ".then(function(stream) { /* Handle success */ })"
//                + ".catch(function(error) { /* Handle error */ });";
//
//        browser_.executeJavaScript(javascriptCode, null, 0);
        browerUI_ = browser_.getUIComponent();

//        address_ = new JTextField(startURL, 100);
//        address_.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                browser_.loadURL(address_.getText());
//            }
//        });

//        client_.addDisplayHandler(new CefDisplayHandlerAdapter() {
//            @Override
//            public void onAddressChange(CefBrowser browser, CefFrame frame, String url) {
//                address_.setText(url);
//            }
//        });

        // Clear focus from the browser when the address field gains focus.
//        address_.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                if (!browserFocus_) return;
//                browserFocus_ = false;
//                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
//                address_.requestFocus();
//            }
//        });

        // Clear focus from the address field when the browser gains focus.
        client_.addFocusHandler(new BrowserFocusHandlerAdapter(this));

    }

    public Component getBrowserUI(){
        return browerUI_;
    }

    public CefBrowser getBrowser(){
        return browser_;
    }

    public CefClient getCefClient(){
        return client_;
    }

    public boolean isBrowserFocus(){
        return browserFocus_;
    }

    public void setBrowserFocus(boolean focus){
        browserFocus_ = focus;
    }

//    public JTextField getAddress_(){
//        return address_;
//    }


}
