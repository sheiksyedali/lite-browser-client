package com.ssa.lbcli.del;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class InstalledApps {
    public static void main(String[] args) {
        List<String> installedApps = getInstalledApps();
        Set<String> runningApps = getRunningApps();

        System.out.println("Installed Applications:");
        for (String app : installedApps) {
            System.out.println(app + (runningApps.contains(app) ? " (Running)" : ""));
        }
    }

    private static List<String> getInstalledApps() {
        List<String> installedApps = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec("reg query HKLM\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall /s");
            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("DisplayName")) {
                    String[] parts = line.split("    ");
                    if (parts.length > 2) {
                        String appName = parts[2].trim();
                        if (!appName.isEmpty()) {
                            installedApps.add(appName);
                        }
                    }
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return installedApps;
    }

    private static Set<String> getRunningApps() {
        Set<String> runningApps = new HashSet<>();
        try {
            Process process = Runtime.getRuntime().exec("tasklist /fo csv /nh");
            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    String processName = parts[0].replace("\"", "");
                    runningApps.add(processName);
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return runningApps;
    }
}
