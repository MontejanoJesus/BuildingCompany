package org.example.buildingcompany.mybatis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Country;
import org.example.buildingcompany.service.ICountryService;
import org.example.buildingcompany.service.impl.CountryServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class MyBatisRunner {
    private final static Logger logger = LogManager.getLogger(MyBatisRunner.class);
    public static void main(String[] args) throws SQLException, InterruptedException {
        // Service using MyBatis
        ICountryService countryDAO = new CountryServiceImpl();

        List<Country> countries = countryDAO.getAll();
        Country country = countryDAO.getById(3L);

        logger.info(countries + "\n");
        logger.info(country);
    }
}
