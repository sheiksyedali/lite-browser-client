package com.ssa.lbcli.del;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemAppList2 {
    public static void main(String[] args) {
        try {
            Process process = Runtime.getRuntime().exec("tasklist /fo csv /nh");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    String processName = parts[0].replace("\"", "");
                    System.out.println(processName);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
