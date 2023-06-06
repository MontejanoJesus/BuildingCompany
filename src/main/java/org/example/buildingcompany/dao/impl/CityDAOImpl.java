package org.example.buildingcompany.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.City;
import org.example.buildingcompany.dao.interfaces.ICityDAO;
import org.example.buildingcompany.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl implements ICityDAO {

    private static final Logger logger = LogManager.getLogger(CityDAOImpl.class);
    private static final String INSERT = "INSERT INTO city (name, country_id) VALUES (?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM city WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM city";
    private static final String UPDATE = "UPDATE city SET name = ?, country_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM city WHERE id = ?";


    @Override
    public void insert(City city) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        statement.setString(1, city.getName());
        statement.setLong(2, city.getCountry().getId());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public List<City> findAll() throws SQLException, InterruptedException {
        List<City> cities = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            City city = createCity(resultSet);
            cities.add(city);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return cities;
    }

    @Override
    public City findById(Long cityId) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setLong(1, cityId);
        ResultSet resultSet = statement.executeQuery();
        City city = createCity(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return city;
    }

    @Override
    public void update(City city, Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, city.getName());
        statement.setLong(2,city.getCountry().getId());
        statement.setLong(3, id);
        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0) {
            logger.info("City updated: " + city);
        } else {
            logger.info("No city to update");
        }
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setLong(1, id);
        int rowsDeleted = statement.executeUpdate();
        if(rowsDeleted > 0) {
            logger.info("City deleted");
        } else {
            logger.info("Country not deleted");
        }
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public City getCityByAddressId(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM city WHERE " +
                "id = (SELECT city_id FROM address WHERE id = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        City city = createCity(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return city;
    }

    private City createCity(ResultSet resultSet) throws SQLException {
        City city = new City();
        if(resultSet.next()) {
            city.setId(resultSet.getLong("id"));
            city.setName(resultSet.getString("name"));
        }
        return city;
    }


}
