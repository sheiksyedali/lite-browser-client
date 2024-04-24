package com.ssa.lbcli.del;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SystemProcessesList {
    public static void main(String[] args) {
        List<String> systemProcesses = getSystemProcesses();

        System.out.println("System Processes:");
        for (String process : systemProcesses) {
            System.out.println(process);
        }
    }

    private static List<String> getSystemProcesses() {
        List<String> systemProcesses = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec("tasklist /fo csv /nh");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    String processName = parts[0].replace("\"", "");
                    if (!isSystemProcess(processName)) {
                        systemProcesses.add(processName);
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return systemProcesses;
    }

    private static boolean isSystemProcess(String processName) {
        // Add logic here to determine if the process is a system process
        // For example, you can check if the process name contains "System32" or "Windows"
        return processName.toLowerCase().contains("system32") ||
                processName.toLowerCase().contains("windows") ||
                processName.toLowerCase().contains("wininit") ||
                processName.toLowerCase().contains("smss");
    }
}
