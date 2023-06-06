package org.example.buildingcompany.dao.interfaces;

import org.example.buildingcompany.classes.City;

import java.sql.SQLException;

public interface ICityDAO extends IDAO<City> {
    City getCityByAddressId(Long id) throws SQLException, InterruptedException;
}
