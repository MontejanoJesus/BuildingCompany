package org.example.buildingcompany.designpatterns.observer;


import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject{

    private List<Observer> observers;
    private Integer state;

    public ConcreteSubject() {
        observers = new ArrayList<>();
    }
    public void setState(Integer state) {
        this.state = state;
        notifyObservers();
    }
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers) {
            observer.update(state);
        }

    }
}
