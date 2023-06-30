package org.example.buildingcompany.designpatterns.facade;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CPU {
    private final static Logger logger = LogManager.getLogger(CPU.class);
    public void execute() {
        logger.info("CPU is executing" + "\n");
    }
}
