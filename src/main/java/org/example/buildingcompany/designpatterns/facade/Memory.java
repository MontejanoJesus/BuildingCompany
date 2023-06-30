package org.example.buildingcompany.designpatterns.facade;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Memory {
    private final static Logger logger = LogManager.getLogger(Memory.class);

    public void load() {
        logger.info("Data is being loaded" + "\n");
    }
}
