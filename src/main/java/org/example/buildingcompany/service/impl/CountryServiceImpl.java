package org.example.buildingcompany.service.impl;

import org.example.buildingcompany.classes.Country;
import org.example.buildingcompany.dao.ICountryDAO;
import org.example.buildingcompany.dao.mybatisimpl.CountryMyBatis;
import org.example.buildingcompany.service.ICountryService;

import java.sql.SQLException;
import java.util.List;

public class CountryServiceImpl implements ICountryService {
    private ICountryDAO countryDAO = new CountryMyBatis();
    @Override
    public void insert(Country country) throws SQLException, InterruptedException {
        countryDAO.insert(country);
    }

    @Override
    public Country getById(Long id) throws SQLException, InterruptedException {
        return countryDAO.findById(id);
    }

    @Override
    public List<Country> getAll() throws SQLException, InterruptedException {
        return countryDAO.findAll();
    }

    @Override
    public void update(Country country, Long id) throws SQLException, InterruptedException {
        countryDAO.update(country, id);
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        countryDAO.delete(id);
    }
}
