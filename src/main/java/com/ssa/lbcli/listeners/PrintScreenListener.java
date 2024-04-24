package com.ssa.lbcli.listeners;

import com.ssa.lbcli.window.Alert;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import javax.swing.*;

public class PrintScreenListener {
    private static User32 user32;
    private static WinDef.HMODULE hMod;
    private static User32.HHOOK hook;

    private static boolean allowProcessing = true;

    public  void enable(){
        user32 = User32.INSTANCE;
        hMod = null;
        int idHook = WinUser.WH_KEYBOARD_LL;
        hook = user32.SetWindowsHookEx(idHook, keyboardHook, hMod, 0);
        if (hook == null) {
            System.err.println("Failed to set hook. Error code: " + Native.getLastError());
            return;
        }

        // Message loop to keep the hook active
        int result;
        WinUser.MSG msg = new WinUser.MSG();
        while ((result = user32.GetMessage(msg, null, 0, 0)) != 0) {
            if (result == -1) {
                break;
            } else {
                user32.TranslateMessage(msg);
                user32.DispatchMessage(msg);
            }
        }

        // Unhook the keyboard hook when the message loop exits
        user32.UnhookWindowsHookEx(hook);
    }
    private User32.LowLevelKeyboardProc keyboardHook = (int nCode, User32.WPARAM wParam, User32.KBDLLHOOKSTRUCT lParam) -> {
        if (nCode >= 0 && wParam.intValue() == WinUser.WM_KEYDOWN) {
            int vkCode = lParam.vkCode;
            if (vkCode == 0x2C && allowProcessing) { // Check for Print Screen key (VK_SNAPSHOT)
                System.out.println("Print Screen key pressed");
                Alert.unauthorizedAlert();
//                System.exit(0);
                allowProcessing = false; // Prevent further processing by the hook
                new Thread(() -> {
                    try {
                        Thread.sleep(100); // Delay to allow the event to propagate
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    allowProcessing = true; // Allow processing again after a delay
                }).start();
                return new User32.LRESULT(1); // Prevent further processing by the hook
            }
        }
        // Bring the Swing window to the front when it loses focus
//        if (nCode >= 0 && wParam.intValue() == 0x0008 /* WM_KILLFOCUS */) {
//            SwingUtilities.invokeLater(() -> {
//                jFrame.toFront();
//                jFrame.requestFocus();
//            });
//        }
//        return user32.CallNextHookEx(null, nCode, wParam, lParam.getPointer());
        return User32.INSTANCE.CallNextHookEx(null, nCode, wParam, new WinDef.LPARAM(lParam.getPointer().getLong(0)));

    };
}
