package org.example.buildingcompany.service.impl;

import org.example.buildingcompany.classes.City;
import org.example.buildingcompany.dao.ICityDAO;
import org.example.buildingcompany.dao.ICountryDAO;
import org.example.buildingcompany.mybatis.daoimpl.CityMyBatis;
import org.example.buildingcompany.mybatis.daoimpl.CountryMyBatis;
import org.example.buildingcompany.service.ICityService;

import java.sql.SQLException;
import java.util.List;

public class CityServiceImpl implements ICityService {
    private ICityDAO cityDAO = new CityMyBatis();
    private ICountryDAO countryDAO = new CountryMyBatis();
    @Override
    public void insert(City city) throws SQLException, InterruptedException{
        cityDAO.insert(city);
    }

    @Override
    public City getById(Long id) throws SQLException, InterruptedException {
        City city = cityDAO.findById(id);
        city.setCountry(countryDAO.getCountryByCityId(id));
        return city;
    }

    @Override
    public List<City> getAll() throws SQLException, InterruptedException {
        List<City> cities = cityDAO.findAll();
        for(City c : cities) {
            c.setCountry(countryDAO.getCountryByCityId(c.getId()));
        }
        return cities;
    }

    @Override
    public void update(City city, Long id) throws SQLException, InterruptedException {
        cityDAO.update(city, id);

    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        cityDAO.delete(id);
    }
}
