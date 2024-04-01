package com.ssa.lbcli.scheduler;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Sheik Syed Ali
 */
public class KillBackgroundTaskScheduler extends Thread {
//    private Logger logger = LogManger.getLogger(KillBackgroundTaskScheduler.class.getName());

    private List<String> whiteListedServices = new ArrayList<>();
    public KillBackgroundTaskScheduler(){
        prepWhitelistedServices();
    }

    private void prepWhitelistedServices(){
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
    }

    public void run(){

        try{
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
                    ProcessBuilder processBuilder1 = new ProcessBuilder("taskkill", "/F", "/IM", service);
                    processBuilder1.inheritIO(); // Redirect input/output to the current process
                    Process process1 = processBuilder1.start();
                    process1.waitFor(); // Wait for the process to complete
                }
                System.out.println(line.split("\\s+")[0]);
            }

            // Wait for the process to complete
            process.waitFor();


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
