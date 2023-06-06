package org.example.buildingcompany.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Equipment;
import org.example.buildingcompany.interfaces.dao.IEquipmentDAO;
import org.example.buildingcompany.interfaces.dao.ISupplierDAO;
import org.example.buildingcompany.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAOImpl implements IEquipmentDAO {

    private final static Logger logger = LogManager.getLogger(EquipmentDAOImpl.class);
    private static final String INSERT = "INSERT INTO equipment (name, description, quantity, cost, supplier_id) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM equipment WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM equipment";
    private static final String UPDATE = "UPDATE equipment SET name = ?, description = ?, quantity = ?, " +
            "cost = ?, supplier_id = ?" +
            " WHERE id = ?";
    private static final String DELETE = "DELETE FROM equipment WHERE id = ?";
    private static ISupplierDAO supplierDAO = new SupplierDAOImpl();

    @Override
    public void insert(Equipment equipment) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        statement.setString(1, equipment.getName());
        statement.setString(2, equipment.getDescription());
        statement.setInt(3, equipment.getQuantity());
        statement.setInt(4, equipment.getCost());
        statement.setInt(5, equipment.getSupplier().getId());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection();

    }

    @Override
    public List<Equipment> findAll() throws SQLException, InterruptedException {
        List<Equipment> equipments = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            Equipment equipment = new Equipment();
            equipment.setId(resultSet.getInt("id"));
            equipment.setName(resultSet.getString("name"));
            equipment.setDescription(resultSet.getString("description"));
            equipment.setQuantity(resultSet.getInt("quantity"));
            equipment.setCost(resultSet.getInt("cost"));
            equipment.setSupplier(supplierDAO.findById(resultSet.getInt("suppliers_id")));
            equipments.add(equipment);
        }
        ConnectionPool.getInstance().releaseConnection();
        return equipments;
    }

    @Override
    public Equipment findById(Integer id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Equipment equipment = new Equipment();
        if(resultSet.next()) {
            equipment.setId(resultSet.getInt("id"));
            equipment.setName(resultSet.getString("name"));
            equipment.setDescription(resultSet.getString("description"));
            equipment.setQuantity(resultSet.getInt("quantity"));
            equipment.setCost(resultSet.getInt("cost"));
            equipment.setSupplier(supplierDAO.findById(resultSet.getInt("suppliers_id")));
        }
        ConnectionPool.getInstance().releaseConnection();
        return equipment;
    }

    @Override
    public void update(Equipment equipment, Integer id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, equipment.getName());
        statement.setString(2, equipment.getDescription());
        statement.setInt(3, equipment.getQuantity());
        statement.setInt(4, equipment.getCost());
        statement.setInt(5, equipment.getSupplier().getId());
        statement.setInt(6, id);
        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0) {
            logger.info("Equipment was updated");
        } else {
            logger.info("Equipment was not updated");
        }
        ConnectionPool.getInstance().releaseConnection();

    }

    @Override
    public void delete(Integer id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setInt(1, id);
        int rowsDeleted = statement.executeUpdate();
        if(rowsDeleted > 0) {
            logger.info("Equipment deleted");
        } else {
            logger.info("Equipment not deleted");
        }
        ConnectionPool.getInstance().releaseConnection();

    }
}
