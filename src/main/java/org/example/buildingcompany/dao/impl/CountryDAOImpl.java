package org.example.buildingcompany.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Country;
import org.example.buildingcompany.dao.interfaces.ICountryDAO;
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
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    public Country findById(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Country country = createCountry(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return country;
    }

    public List<Country> findAll() throws SQLException, InterruptedException {
        List<Country> countries = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Country country = createCountry(resultSet);
            countries.add(country);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return countries;
    }

    public void update(Country country, Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, country.getName());
        statement.setLong(2, id);
        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0) {
            logger.info("Country updated: " + country);
        } else {
            logger.info("No country to update");
        }
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    public void delete(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setLong(1, id);
        int rowsDeleted = statement.executeUpdate();
        if(rowsDeleted > 0) {
            logger.info("Country deleted");
        } else {
            logger.info("Country not deleted");
        }
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    public Country getCountryByCityId(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM country WHERE " +
                "id = (SELECT country_id FROM city WHERE id = ?)");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Country country = createCountry(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return country;
    }

    private Country createCountry(ResultSet resultSet) throws SQLException {
        Country country = new Country();
        if(resultSet.next()) {
            country.setId(resultSet.getLong("id"));
            country.setName(resultSet.getString("name"));
        }
        return country;
    }

}
