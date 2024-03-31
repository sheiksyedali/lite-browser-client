package com.ssa.lbcli.log;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Author: Sheik Syed Ali
 */
public class LogManger {

    public static Logger getLogger(String name){
        Logger logger = Logger.getLogger(name);
        try{
            String path = "/logs/lbcli.log";
            File logFile = new File(path);
            if(!logFile.exists()){
                logFile.mkdirs();
                logFile.createNewFile();
            }
            FileHandler fileHandler = new FileHandler(path);
            logger.addHandler(fileHandler);


            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return logger;
    }
}
