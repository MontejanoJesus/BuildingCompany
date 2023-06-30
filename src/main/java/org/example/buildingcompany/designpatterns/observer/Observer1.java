package org.example.buildingcompany.designpatterns.observer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Observer1 implements Observer{
    private final static Logger logger = LogManager.getLogger(Observer1.class);
    @Override
    public void update(Integer state) {
        logger.info("Observer1 received an update. New state is: " + state + "\n");
    }
}
