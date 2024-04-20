package com.ssa.lbcli.listeners;

import com.ssa.lbcli.window.Alert;
import org.cef.browser.CefBrowser;
import org.cef.handler.CefKeyboardHandlerAdapter;

public class BrowserKeyboardHandler extends CefKeyboardHandlerAdapter {
    @Override
    public boolean onKeyEvent(CefBrowser browser, CefKeyEvent event) {
        // Handle keyboard events here
        boolean is_system_key = event.is_system_key;
        int nativeKeyCode = event.native_key_code;
        int winKeyCode = event.windows_key_code;
        char c = event.character;
        System.out.println("is system key: "+is_system_key+
                "; native key code: "+nativeKeyCode+"; "
                +"win key code: "+winKeyCode
                +"; char: "+c+"; "
        );
        switch (winKeyCode){
            case 17: //Ctrl key
            case 18: //Alt key
            case 91: //Windows key
            case 112: //F1 key
            case 113: //F2 key
            case 114: //F3 key
            case 115: //F4 key
            case 116: //F5 key
            case 117: //F6 key
            case 118: //F7 key
            case 119: //F8 key
            case 120: //F9 key
            case 121: //F10 key
            case 122: //F11 key
            case 123: //F12 key
                Alert.unauthorizedAlert();
        }
//        if(winKeyCode == 91){
//            setBrowserFocus(true);
//        }
//                if (event.getType() == CefKeyEvent.EventType.KEY_PRESSED) {
//                    System.out.println("Key Pressed: " + event.getNativeKeyCode());
//                } else if (event.getType() == CefKeyEvent.EventType.KEY_RELEASED) {
//                    System.out.println("Key Released: " + event.getNativeKeyCode());
//                }
        return false; // Return true if the event is handled
    }
}
