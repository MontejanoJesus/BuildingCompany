package org.example.buildingcompany.designpatterns.facade;

public class FacadeRunner {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();

        computer.start();
        computer.shutdown();
    }
}
