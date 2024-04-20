package com.ssa.lbcli.listeners;

import com.ssa.lbcli.window.Alert;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        switch (keycode){
            case 17: //Ctrl key
            case 18: //Alt key
            case 524: //Windows key
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
        System.out.println("field key lister: "+e.getKeyCode()+"; "+e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
