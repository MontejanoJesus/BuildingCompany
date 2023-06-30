package org.example.buildingcompany.dao.jdbcimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Supplier;
import org.example.buildingcompany.dao.ISupplierDAO;
import org.example.buildingcompany.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements ISupplierDAO {

    private final static Logger logger = LogManager.getLogger(SupplierDAOImpl.class);
    private static final String INSERT = "INSERT INTO supplies (name, phone_number, address_id) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM suppliers WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM suppliers";
    private static final String UPDATE = "UPDATE suppliers SET name = ?, phone_number = ?, address_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM suppliers WHERE id = ?";
    @Override
    public void insert(Supplier supplier) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        statement.setString(1, supplier.getName());
        statement.setString(2, supplier.getPhoneNumber());
        statement.setLong(3, supplier.getAddress().getId());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public List<Supplier> findAll() throws SQLException, InterruptedException {
        List<Supplier> suppliers = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            Supplier supplier = createSupplier(resultSet);
            suppliers.add(supplier);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return suppliers;
    }

    @Override
    public Supplier findById(Long supplierId) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setLong(1, supplierId);
        ResultSet resultSet = statement.executeQuery();
        Supplier supplier = createSupplier(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return supplier;
    }

    @Override
    public void update(Supplier supplier, Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, supplier.getName());
        statement.setString(2, supplier.getPhoneNumber());
        statement.setLong(3, supplier.getAddress().getId());
        statement.setLong(4, id);
        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0) {
            logger.info("Supplier updated");
        } else {
            logger.info("Supplier not updated");
        }
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    public Supplier getSupplierByMaterialId(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM suppliers WHERE " +
                "id = (SELECT suppliers_id FROM materials WHERE id = ?)");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Supplier supplier = createSupplier(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return supplier;
    }

    public Supplier getSupplierByEquipmentId(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM suppliers WHERE " +
                "id = (SELECT suppliers_id FROM equipment WHERE id = ?)");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Supplier supplier = createSupplier(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return supplier;
    }

    @Override
    public void delete(Long supplierId) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setLong(1, supplierId);
        int rowsDeleted = statement.executeUpdate();
        if(rowsDeleted > 0) {
            logger.info("Supplier deleted");
        } else {
            logger.info("Supplier not deleted");
        }
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    private Supplier createSupplier(ResultSet resultSet) throws SQLException {
        Supplier supplier = new Supplier();
        if(resultSet.next()) {
            supplier.setId(resultSet.getLong("id"));
            supplier.setName(resultSet.getString("name"));
            supplier.setPhoneNumber(resultSet.getString("phone_number"));
        }
        return supplier;
    }
}
