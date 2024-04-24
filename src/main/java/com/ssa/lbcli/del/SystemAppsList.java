//package com.ssa.lbcli.del;
//
//import com.sun.jna.Native;
//import com.sun.jna.platform.win32.Kernel32;
//import com.sun.jna.platform.win32.WinNT;
//import com.sun.jna.platform.win32.WinDef.HANDLE;
//import com.sun.jna.ptr.IntByReference;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class SystemAppsList {
//    public static void main(String[] args) {
//        // Get the list of all running processes
//        int[] processIds = getProcessIds();
//
//        // Convert the process IDs to a list of process names
//        List<String> processNames = Arrays.stream(processIds)
//                .mapToObj(pid -> getProcessName(pid))
//                .collect(Collectors.toList());
//
//        // Filter the list of process names to include only system applications
//        List<String> systemApps = processNames.stream()
//                .filter(name -> !name.toLowerCase().contains("system32"))
//                .filter(name -> !name.toLowerCase().contains("windows"))
//                .filter(name -> !name.toLowerCase().contains("wininit"))
//                .filter(name -> !name.toLowerCase().contains("smss"))
//                .toList();
//
//        // Print the list of system applications
//        systemApps.forEach(System.out::println);
//    }
//
//    private static int[] getProcessIds() {
//        // Get the handle to the current process
//        HANDLE hProcess = Kernel32.INSTANCE.GetCurrentProcess();
//
//        // Call EnumProcesses with a buffer size of 0 to get the required buffer size
//        IntByReference pCount = new IntByReference();
//        Kernel32.INSTANCE.EnumProcesses(new int[1], 0, pCount);
//
//        // Allocate memory for the buffer
//        int[] pids = new int[pCount.getValue() / 4]; // Each int is 4 bytes
//
//        // Call EnumProcesses again to get the process IDs
//        Kernel32.INSTANCE.EnumProcesses(pids, pids.length * 4, pCount);
//
//        return pids;
//    }
//
//    private static String getProcessName(int pid) {
//        // Open the process
//        HANDLE h = Kernel32.INSTANCE.OpenProcess(WinNT.PROCESS_QUERY_INFORMATION | WinNT.PROCESS_VM_READ, false, pid);
//        char[] name = new char[1024];
//        IntByReference len = new IntByReference(name.length);
//
//        // Get the process image file name
//        boolean success = Kernel32.INSTANCE.GetProcessImageFileNameW(h, name, len);
//        if (success) {
//            return Native.toString(name);
//        }
//        return null;
//    }
//}
