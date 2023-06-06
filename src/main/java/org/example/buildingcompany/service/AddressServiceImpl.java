package org.example.buildingcompany.service;

import org.example.buildingcompany.classes.Address;
import org.example.buildingcompany.dao.AddressDAOImpl;
import org.example.buildingcompany.interfaces.dao.IAddressDAO;
import org.example.buildingcompany.interfaces.service.IService;

import java.sql.SQLException;
import java.util.List;

public class AddressServiceImpl implements IService<Address> {
    private static final IAddressDAO addressDAO = new AddressDAOImpl();
    @Override
    public void insert(Address address) throws SQLException, InterruptedException {
        addressDAO.insert(address);
    }

    @Override
    public Address getById(Integer id) throws SQLException, InterruptedException {
        return addressDAO.findById(id);
    }

    @Override
    public List<Address> getAll() throws SQLException, InterruptedException {
        return addressDAO.findAll();
    }

    @Override
    public void update(Address address, Integer id) throws SQLException, InterruptedException {
        addressDAO.update(address, id);

    }

    @Override
    public void delete(Integer id) throws SQLException, InterruptedException {
        addressDAO.delete(id);

    }
}
