package org.example.buildingcompany.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Address;
import org.example.buildingcompany.dao.interfaces.IAddressDAO;
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
    private static final String DELETE = "DELETE FROM address WHERE id = ?";;
    @Override
    public void insert(Address address) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        statement.setString(1, address.getAddress());
        statement.setString(2, address.getPostalCode());
        statement.setLong(3, address.getCity().getId());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);

    }

    @Override
    public List<Address> findAll() throws SQLException, InterruptedException {
        List<Address> addresses = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            Address address = createAddress(resultSet);
            addresses.add(address);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return addresses;
    }

    @Override
    public Address findById(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Address address = createAddress(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return address;
    }

    @Override
    public void update(Address address, Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, address.getAddress());
        statement.setString(2, address.getPostalCode());
        statement.setLong(3, id);
        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0) {
            logger.info("Address was updated");
        } else {
            logger.info("Address was not updated");
        }
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setLong(1, id);
        int rowsDeleted = statement.executeUpdate();
        if(rowsDeleted > 0) {
            logger.info("Address deleted");
        } else {
            logger.info("Address not deleted");
        }
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    public Address getAddressBySupplierId(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM address WHERE " +
                "id = (SELECT address_id FROM suppliers WHERE id = ?)");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Address address = createAddress(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return address;
    }

    public Address getAddressByEmployeeId(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM address WHERE " +
                "id = (SELECT address_id FROM employees WHERE id = ?)");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Address address = createAddress(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return address;
    }

    public Address getAddressByClientId(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM address WHERE " +
                "id = (SELECT address_id FROM clients WHERE id = ?)");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Address address = createAddress(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return address;
    }

    public Address getAddressByProjectId(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM address WHERE " +
                "id = (SELECT address_id FROM projects WHERE id = ?)");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Address address = createAddress(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return address;
    }

    private Address createAddress(ResultSet resultSet) throws SQLException {
        Address address = new Address();
        if(resultSet.next()) {
            address.setId(resultSet.getLong("id"));
            address.setAddress(resultSet.getString("address"));
            address.setPostalCode(resultSet.getString("postal_code"));
        }
        return address;
    }
}
