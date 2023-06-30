package org.example.buildingcompany.designpatterns.factory;

import org.example.buildingcompany.dao.IDAO;
import org.example.buildingcompany.dao.jdbcimpl.*;

public class JDBCFactory extends AbstractFactory {
    public IDAO getDao(String daoType) {
        if(daoType == null) {
            return null;
        }
        if(daoType.equalsIgnoreCase("Country")) {
            return new CountryDAOImpl();
        } else if (daoType.equalsIgnoreCase("City")) {
            return new CityDAOImpl();
        } else if (daoType.equalsIgnoreCase("Address")) {
            return new AddressDAOImpl();
        } else if (daoType.equalsIgnoreCase("Employee")) {
            return new EmployeeDAOImpl();
        } else if (daoType.equalsIgnoreCase("Client")) {
            return new ClientDAOImpl();
        } else if (daoType.equalsIgnoreCase("Supplier")) {
            return new SupplierDAOImpl();
        } else if (daoType.equalsIgnoreCase("Project")) {
            return new ProjectDAOImpl();
        } else if (daoType.equalsIgnoreCase("Material")) {
            return new MaterialDAOImpl();
        } else if (daoType.equalsIgnoreCase("Equipment")) {
            return new EquipmentDAOImpl();
        }
        return null;
    }
}
