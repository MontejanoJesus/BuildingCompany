package org.example.buildingcompany.designpatterns.facade;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComputerFacade {
    private final static Logger logger = LogManager.getLogger(ComputerFacade.class);

    private CPU cpu;
    private HardDrive hardDrive;
    private Memory memory;

    public ComputerFacade() {
        cpu = new CPU();
        hardDrive = new HardDrive();
        memory = new Memory();
    }
    public void start() {
        logger.info("Computer is starting" + "\n");
        cpu.execute();
        memory.load();
        hardDrive.read();

    }
    public void shutdown() {
        logger.info("Computer is shutting down." + "\n");
    }
}
