package com.ssa.lbcli.process;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessManager {
    private static final Logger logger = LogManager.getLogger(ProcessManager.class);
    private static List<String> whiteListedServices = new ArrayList<>();

//    static {
    public ProcessManager() {
        whiteListedServices.add("system");
        whiteListedServices.add("registry");
        whiteListedServices.add("smss.exe");
        whiteListedServices.add("csrss.exe");
        whiteListedServices.add("wininit.exe");
        whiteListedServices.add("services.exe");
        whiteListedServices.add("lsass.exe");
        whiteListedServices.add("svchost.exe");
        whiteListedServices.add("lsm.exe");
        whiteListedServices.add("explorer.exe");
        whiteListedServices.add("idea64.exe");
        whiteListedServices.add("java.exe");
        whiteListedServices.add("javaw.exe");
        whiteListedServices.add("jcef_helper.exe");
        whiteListedServices.add("AutoHotKey.exe");

        //debug
//        whiteListedServices.add("idea64.exe");

    }
    public void kill(){
        try{
            logger.info("In processmanager start");
            // Execute a command to list all running tasks
            ProcessBuilder processBuilder = new ProcessBuilder("tasklist");
            processBuilder.redirectErrorStream(true); // Redirect error stream to the input stream
            Process process = processBuilder.start();

            // Read the output of the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                String service = line.split("\\s+")[0];
                service = service.trim().toLowerCase();
                if(!whiteListedServices.contains(service)){
                    logger.info("Killing: "+service);
//                    ProcessBuilder processBuilder1 = new ProcessBuilder("taskkill", "/F", "/IM", service);
//                    processBuilder1.inheritIO(); // Redirect input/output to the current process
//                    Process process1 = processBuilder1.start();
//                    process1.waitFor(); // Wait for the process to complete
                }
                System.out.println(line.split("\\s+")[0]);
            }

            // Wait for the process to complete
//            process.waitFor();

            logger.info("In processmanager end");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void killProcess(String serviceName){
        try{
            ProcessBuilder processBuilder = new ProcessBuilder("taskkill", "/F", "/IM", serviceName);
            processBuilder.inheritIO(); // Redirect input/output to the current process
            Process process = processBuilder.start();
            process.waitFor();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void backToDaddy(){
        try{
            try {
                Runtime.getRuntime().exec("scripts\\AutoHotKey.exe scripts\\ch.ahk");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
