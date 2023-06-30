package org.example.buildingcompany.designpatterns.factory;

public class FactoryGenerator {
    public static AbstractFactory getFactory(String choice) {
        if(choice.equalsIgnoreCase("MyBatis")) {
            return new MyBatisFactory();
        } else if (choice.equalsIgnoreCase("JDBC")) {
            return new JDBCFactory();
        }
        return null;
    }
}
