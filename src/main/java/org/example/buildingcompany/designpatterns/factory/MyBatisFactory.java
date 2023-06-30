package org.example.buildingcompany.designpatterns.factory;

import org.example.buildingcompany.dao.IDAO;
import org.example.buildingcompany.dao.mybatisimpl.*;

public class MyBatisFactory extends AbstractFactory {
    public IDAO getDao(String daoType) {
        if(daoType == null) {
            return null;
        }
        if(daoType.equalsIgnoreCase("Country")) {
            return new CountryMyBatis();
        } else if (daoType.equalsIgnoreCase("City")) {
            return new CityMyBatis();
        } else if (daoType.equalsIgnoreCase("Address")) {
            return new AddressMyBatis();
        } else if (daoType.equalsIgnoreCase("Employee")) {
            return new EmployeeMyBatis();
        } else if (daoType.equalsIgnoreCase("Client")) {
            return new ClientMyBatis();
        } else if (daoType.equalsIgnoreCase("Supplier")) {
            return new SupplierMyBatis();
        } else if (daoType.equalsIgnoreCase("Project")) {
            return new ProjectMyBatis();
        } else if (daoType.equalsIgnoreCase("Material")) {
            return new MaterialMyBatis();
        } else if (daoType.equalsIgnoreCase("Equipment")) {
            return new EquipmentMyBatis();
        }
        return null;
    }
}
