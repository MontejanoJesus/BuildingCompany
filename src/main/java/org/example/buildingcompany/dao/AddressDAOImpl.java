package org.example.buildingcompany.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Address;
import org.example.buildingcompany.interfaces.dao.IAddressDAO;
import org.example.buildingcompany.interfaces.dao.ICityDAO;
import org.example.buildingcompany.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAOImpl implements IAddressDAO {

    private final static Logger logger = LogManager.getLogger(AddressDAOImpl.class);
    private static final String INSERT = "INSERT INTO address (address, postal_code, city_id) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM address WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM address";
    private static final String UPDATE = "UPDATE address SET address = ?, postal_code = ?, city_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM address WHERE id = ?";
    private static ICityDAO cityDAO = new CityDAOImpl();
    @Override
    public void insert(Address address) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        statement.setString(1, address.getAddress());
        statement.setString(2, address.getPostalCode());
        statement.setInt(3, address.getCity().getId());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection();

    }

    @Override
    public List<Address> findAll() throws SQLException, InterruptedException {
        List<Address> addresses = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            Address address = new Address();
            address.setId(resultSet.getInt("id"));
            address.setAddress(resultSet.getString("address"));
            address.setPostalCode(resultSet.getString("postal_code"));
            address.setCity(cityDAO.findById(resultSet.getInt("city_id")));
            addresses.add(address);
        }
        ConnectionPool.getInstance().releaseConnection();
        return addresses;
    }

    @Override
    public Address findById(Integer id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Address address = new Address();
        if(resultSet.next()) {
            address.setId(resultSet.getInt("id"));
            address.setAddress(resultSet.getString("address"));
            address.setPostalCode(resultSet.getString("postal_code"));
            address.setCity(cityDAO.findById(resultSet.getInt("city_id")));
        }
        ConnectionPool.getInstance().releaseConnection();
        return address;
    }

    @Override
    public void update(Address address, Integer id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, address.getAddress());
        statement.setString(2, address.getPostalCode());
        statement.setInt(3, id);
        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0) {
            logger.info("Address was updated");
        } else {
            logger.info("Address was not updated");
        }
        ConnectionPool.getInstance().releaseConnection();
    }

    @Override
    public void delete(Integer addressId) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, addressId);
        int rowsDeleted = statement.executeUpdate();
        if(rowsDeleted > 0) {
            logger.info("Address deleted");
        } else {
            logger.info("Address not deleted");
        }
        ConnectionPool.getInstance().releaseConnection();
    }
}
