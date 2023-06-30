package org.example.buildingcompany.designpatterns.factory;

import org.example.buildingcompany.dao.IDAO;

public abstract class AbstractFactory {
    abstract IDAO getDao(String daoType);
}
