package org.example.buildingcompany.designpatterns.facade;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HardDrive {
    private final static Logger logger = LogManager.getLogger(HardDrive.class);
    public void read() {
        logger.info("Data being read from hard-drive." + "\n");
    }
}
