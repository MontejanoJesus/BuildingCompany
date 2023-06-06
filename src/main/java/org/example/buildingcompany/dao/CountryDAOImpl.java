package org.example.buildingcompany.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Country;
import org.example.buildingcompany.interfaces.dao.ICountryDAO;
import org.example.buildingcompany.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAOImpl implements ICountryDAO {

    private final static Logger logger = LogManager.getLogger(CountryDAOImpl.class);
    private static final String INSERT = "INSERT INTO country (name) VALUES (?)";
    private static final String SELECT_BY_ID = "SELECT * FROM country WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM country";
    private static final String UPDATE = "UPDATE country SET name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM country WHERE id = ?";

    public void insert(Country country) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        statement.setString(1, country.getName());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection();
    }

    public Country findById(Integer countryId) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, countryId);
        ResultSet resultSet = statement.executeQuery();
        Country country = new Country();
        if(resultSet.next()) {
            country.setId(resultSet.getInt("id"));
            country.setName(resultSet.getString("name"));
        }
        ConnectionPool.getInstance().releaseConnection();
        return country;
    }

    public List<Country> findAll() throws SQLException, InterruptedException {
        List<Country> countries = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Country country = new Country();
            country.setId(resultSet.getInt("id"));
            country.setName(resultSet.getString("name"));
            countries.add(country);
        }
        ConnectionPool.getInstance().releaseConnection();
        return countries;
    }

    public void update(Country country) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, country.getName());
        statement.setInt(2, country.getId());
        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0) {
            logger.info("Country updated: " + country);
        } else {
            logger.info("No country to update");
        }
        ConnectionPool.getInstance().releaseConnection();
    }

    public void delete(Integer id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, id);
        int rowsDeleted = statement.executeUpdate();
        if(rowsDeleted > 0) {
            logger.info("Country deleted");
        } else {
            logger.info("Country not deleted");
        }
        ConnectionPool.getInstance().releaseConnection();
    }
}
