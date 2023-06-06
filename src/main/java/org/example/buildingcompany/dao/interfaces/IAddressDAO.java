package org.example.buildingcompany.dao.interfaces;

import org.example.buildingcompany.classes.Address;

import java.sql.SQLException;

public interface IAddressDAO extends IDAO<Address> {
    Address getAddressBySupplierId(Long id) throws SQLException, InterruptedException;
    Address getAddressByEmployeeId(Long id) throws SQLException, InterruptedException;
    Address getAddressByClientId(Long id) throws SQLException, InterruptedException;
    Address getAddressByProjectId(Long id) throws SQLException, InterruptedException;

}
