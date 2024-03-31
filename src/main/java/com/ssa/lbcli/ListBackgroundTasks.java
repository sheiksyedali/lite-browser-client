package com.ssa.lbcli;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListBackgroundTasks {
    public static void main(String[] args) {
        try {
            // Execute a command to list all running tasks
            ProcessBuilder processBuilder = new ProcessBuilder("tasklist");
            processBuilder.redirectErrorStream(true); // Redirect error stream to the input stream
            Process process = processBuilder.start();

            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to complete
            process.waitFor();

            ProcessBuilder processBuilder1 = new ProcessBuilder("taskkill", "/F", "/IM", "chrome.exe");
            processBuilder1.inheritIO(); // Redirect input/output to the current process
            Process process1 = processBuilder1.start();
            process1.waitFor(); // Wait for the process to complete


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.err.println("Failed to list background tasks: " + e.getMessage());
        }
    }
}
