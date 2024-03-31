package com.ssa.lbcli.scheduler;


import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Author: Sheik Syed Ali
 */
public class KillBackgroundTaskScheduler extends Thread {
//    private Logger logger = LogManger.getLogger(KillBackgroundTaskScheduler.class.getName());

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
                System.out.println(line.split("\\s+")[0]);
            }

            // Wait for the process to complete
            process.waitFor();


        }catch (Exception ex){
            ex.printStackTrace();
        }




//        for(int i=0;i<5;i++){
//            System.out.println("Background check: "+i);
////            logger.info("Background check: "+i);
//
//            try{
//                Thread.sleep(1000);
//            } catch (InterruptedException ex){
//                ex.printStackTrace();
//            }
//        }
    }
}
