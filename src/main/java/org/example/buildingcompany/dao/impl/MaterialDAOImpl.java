package org.example.buildingcompany.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Material;
import org.example.buildingcompany.dao.interfaces.IMaterialDAO;
import org.example.buildingcompany.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAOImpl implements IMaterialDAO {

    private final static Logger logger = LogManager.getLogger(MaterialDAOImpl.class);
    private static final String INSERT = "INSERT INTO materials (name, description, quantity, cost, supplier_id) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM materials WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM materials";
    private static final String UPDATE = "UPDATE materials SET name = ?, description = ?, quantity = ?, " +
            "cost = ?, supplier_id = ?" +
            " WHERE id = ?";
    private static final String DELETE = "DELETE FROM materials WHERE id = ?";
    @Override
    public void insert(Material material) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        statement.setString(1, material.getName());
        statement.setString(2, material.getDescription());
        statement.setInt(3, material.getQuantity());
        statement.setInt(4, material.getCost());
        statement.setLong(5, material.getSupplier().getId());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public List<Material> findAll() throws SQLException, InterruptedException {
        List<Material> materials = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            Material material = createMaterial(resultSet);
            materials.add(material);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return materials;
    }

    @Override
    public Material findById(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Material material = createMaterial(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return material;
    }

    @Override
    public void update(Material material, Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, material.getName());
        statement.setString(2, material.getDescription());
        statement.setInt(3, material.getQuantity());
        statement.setInt(4, material.getCost());
        statement.setLong(5, material.getSupplier().getId());
        statement.setLong(6, id);
        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0) {
            logger.info("Material was updated");
        } else {
            logger.info("Material was not updated");
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
            logger.info("Material deleted");
        } else {
            logger.info("Material not deleted");
        }
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    private Material createMaterial(ResultSet resultSet) throws SQLException {
        Material material = new Material();
        if(resultSet.next()) {
            material.setId(resultSet.getLong("id"));
            material.setName(resultSet.getString("name"));
            material.setDescription(resultSet.getString("description"));
            material.setQuantity(resultSet.getInt("quantity"));
            material.setCost(resultSet.getInt("cost"));
        }
        return material;
    }
}
