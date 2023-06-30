package org.example.buildingcompany.designpatterns.observer;

public class ObserverRunner {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        Observer observer1 = new Observer1();
        Observer observer2 = new Observer2();

        subject.registerObserver(observer1);
        subject.registerObserver(observer2);

        subject.setState(10);

        subject.removeObserver(observer1);

        subject.setState(15);
    }
}
