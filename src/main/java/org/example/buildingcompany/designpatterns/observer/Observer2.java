package org.example.buildingcompany.designpatterns.observer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Observer2 implements Observer{
    private final static Logger logger = LogManager.getLogger(Observer2.class);
    @Override
    public void update(Integer state) {
        logger.info("Observer2 received an update: New state is: " + state + "\n");
    }
}
