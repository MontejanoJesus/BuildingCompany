package org.example.buildingcompany.dao.interfaces;

import org.example.buildingcompany.classes.Country;

import java.sql.SQLException;

public interface ICountryDAO extends IDAO<Country> {
    Country getCountryByCityId(Long id) throws SQLException, InterruptedException;
}
