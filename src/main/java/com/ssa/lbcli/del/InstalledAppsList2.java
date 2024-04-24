package com.ssa.lbcli.del;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class InstalledAppsList2 {
    public static void main(String[] args) {
        List<String> installedApps = getInstalledApps();

        System.out.println("Installed Applications:");
        for (String app : installedApps) {
            System.out.println(app);
        }
    }

    private static List<String> getInstalledApps() {
        List<String> installedApps = new ArrayList<>();
        Preferences registry = Preferences.userRoot().node("HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall");
        try {
            String[] subkeys = registry.childrenNames();
            for (String subkey : subkeys) {
                Preferences appKey = registry.node(subkey);
                String displayName = appKey.get("DisplayName", "");
                if (!displayName.isEmpty()) {
                    installedApps.add(displayName);
                }
            }
        }  catch (Exception e){
            e.printStackTrace();
        }
        return installedApps;
    }
}
