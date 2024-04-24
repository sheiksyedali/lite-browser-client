package com.ssa.lbcli.del;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.W32Service;
import com.sun.jna.platform.win32.W32ServiceManager;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SystemAppsList1 {
    public static void main(String[] args) {
        // Get the list of all running processes
        Win32Process process = Win32Process.INSTANCE;
        IntByReference pCount = new IntByReference();
        HANDLE hProcess = Kernel32.INSTANCE.GetCurrentProcess();
        int[] pids = process.EnumProcesses(hProcess, 0, pCount);
//        process.EnumProcesses(hProcess,0,)

        // Convert the process IDs to a list of process names
        List<String> processNames = Arrays.stream(pids)
                .mapToObj(pid -> {
                    HANDLE h = Kernel32.INSTANCE.OpenProcess(WinNT.PROCESS_QUERY_INFORMATION | WinNT.PROCESS_VM_READ, false, pid);
                    char[] name = new char[1024];
                    IntByReference len = new IntByReference(name.length);
                    boolean success = process.GetProcessImageFileNameW(h, name, len);
                    if (success) {
                        return Native.toString(name);
                    }
                    return null;
                })
                .filter(name -> name != null)
                .collect(Collectors.toList());

        // Filter the list of process names to include only system applications
        List<String> systemApps = processNames.stream()
                .filter(name -> !name.toLowerCase().contains("system32"))
                .filter(name -> !name.toLowerCase().contains("windows"))
                .filter(name -> !name.toLowerCase().contains("wininit"))
                .filter(name -> !name.toLowerCase().contains("smss"))
                .collect(Collectors.toList());

        // Print the list of system applications
        systemApps.forEach(System.out::println);
    }

    // Define the Win32Process interface for JNA
    public interface Win32Process extends com.sun.jna.Library {
        Win32Process INSTANCE = Native.load("psapi", Win32Process.class);

        boolean GetProcessImageFileNameW(HANDLE hProcess, char[] lpImageFileName, IntByReference nSize);

        int[] EnumProcesses(HANDLE hProcess, int lpidProcess, IntByReference lpcbNeeded);
    }
}
