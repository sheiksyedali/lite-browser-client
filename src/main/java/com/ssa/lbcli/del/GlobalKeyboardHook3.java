package com.ssa.lbcli.del;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser;

import javax.swing.*;

public class GlobalKeyboardHook3 {

    private static User32 user32;
    private static HMODULE hMod;
    private static User32.HHOOK hook;

    private static boolean allowProcessing = true;
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("Global Keyboard Hook Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);

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

    private static User32.LowLevelKeyboardProc keyboardHook = (int nCode, User32.WPARAM wParam, User32.KBDLLHOOKSTRUCT lParam) -> {
        if (nCode >= 0 && wParam.intValue() == WinUser.WM_KEYDOWN) {
            int vkCode = lParam.vkCode;
            if (vkCode == 0x2C && allowProcessing) { // Check for Print Screen key (VK_SNAPSHOT)
                System.out.println("Print Screen key pressed");
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
        if (nCode >= 0 && wParam.intValue() == 0x0008 /* WM_KILLFOCUS */) {
            SwingUtilities.invokeLater(() -> {
                frame.toFront();
                frame.requestFocus();
            });
        }
//        return user32.CallNextHookEx(null, nCode, wParam, lParam.getPointer());
        return User32.INSTANCE.CallNextHookEx(null, nCode, wParam, new LPARAM(lParam.getPointer().getLong(0)));

    };
}
