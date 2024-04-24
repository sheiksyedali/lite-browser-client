package com.ssa.lbcli.del;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;

public class PrintScreenListener {

    private static HMODULE hMod;
    private static int idHook;
    private static User32.HHOOK hook;

    public static void main(String[] args) {
        User32 user32 = User32.INSTANCE;
        hMod = null;
        idHook = WinUser.WH_KEYBOARD_LL;
        hook = user32.SetWindowsHookEx(idHook, keyboardHook, hMod, 0);
        if (hook == null) {
            System.err.println("Failed to set hook. Error code: " + Native.getLastError());
            return;
        }
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
        user32.UnhookWindowsHookEx(hook);
    }

    private static User32.LowLevelKeyboardProc keyboardHook = (int nCode, User32.WPARAM wParam, KBDLLHOOKSTRUCT lParam) -> {
        if (nCode >= 0) {
            switch (wParam.intValue()) {
                case WinUser.WM_KEYDOWN:
                case WinUser.WM_SYSKEYDOWN:
                case WinUser.WM_KEYUP:
                case WinUser.WM_SYSKEYUP:
                    if (lParam.vkCode == 0x2C && (lParam.flags & 0x80) != 0) {
                        System.out.println("Print Screen key pressed");
                        System.exit(0);
                    }
                    break;
            }
        }
//        return User32.INSTANCE.CallNextHookEx(null, nCode, wParam, new LPARAM(lParam.getPointer().getPointer(0)));
//        return User32.INSTANCE.CallNextHookEx(null, nCode, wParam, lParam.getPointer());
        return User32.INSTANCE.CallNextHookEx(null, nCode, wParam, new LPARAM(lParam.getPointer().getLong(0)));

    };
}
