package org.example.buildingcompany.service.impl;

import org.example.buildingcompany.classes.Address;
import org.example.buildingcompany.dao.IAddressDAO;
import org.example.buildingcompany.dao.ICityDAO;
import org.example.buildingcompany.dao.mybatisimpl.AddressMyBatis;
import org.example.buildingcompany.dao.mybatisimpl.CityMyBatis;
import org.example.buildingcompany.service.IAddressService;

import java.sql.SQLException;
import java.util.List;

public class AddressServiceImpl implements IAddressService {
    private IAddressDAO addressDAO = new AddressMyBatis();
    private ICityDAO cityDAO = new CityMyBatis();

    @Override
    public void insert(Address address) throws SQLException, InterruptedException {
        addressDAO.insert(address);
    }

    @Override
    public Address getById(Long id) throws SQLException, InterruptedException {
        Address address = addressDAO.findById(id);
        address.setCity(cityDAO.getCityByAddressId(id));
        return address;
    }

    @Override
    public List<Address> getAll() throws SQLException, InterruptedException {
        List<Address> addresses = addressDAO.findAll();
        for(Address a : addresses) {
            a.setCity(cityDAO.getCityByAddressId(a.getId()));
        }
        return addresses;
    }

    @Override
    public void update(Address address, Long id) throws SQLException, InterruptedException {
        addressDAO.update(address, id);

    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        addressDAO.delete(id);

    }
}
