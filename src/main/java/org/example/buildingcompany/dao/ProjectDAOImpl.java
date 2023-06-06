package org.example.buildingcompany.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Project;
import org.example.buildingcompany.interfaces.dao.IAddressDAO;
import org.example.buildingcompany.interfaces.dao.IClientDAO;
import org.example.buildingcompany.interfaces.dao.IProjectDAO;
import org.example.buildingcompany.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAOImpl implements IProjectDAO {

    private final static Logger logger = LogManager.getLogger(ProjectDAOImpl.class);
    private static final String INSERT = "INSERT INTO projects (name, description, cost, client_id, address_id) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM projects WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM projects";
    private static final String UPDATE = "UPDATE projects SET name = ?, description = ?, cost = ?, " +
            "client_id = ?, address_id = ?" +
            " WHERE id = ?";
    private static final String DELETE = "DELETE FROM projects WHERE id = ?";
    private static IClientDAO clientDAO = new ClientDAOImpl();
    private static IAddressDAO addressDAO = new AddressDAOImpl();

    @Override
    public void insert(Project project) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        statement.setString(1, project.getName());
        statement.setString(2, project.getDescription());
        statement.setInt(3, project.getCost());
        statement.setInt(4, project.getClient().getId());
        statement.setInt(5, project.getAddress().getId());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection();

    }

    @Override
    public List<Project> findAll() throws SQLException, InterruptedException {
        List<Project> projects = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            Project project = new Project();
            project.setId(resultSet.getInt("id"));
            project.setName(resultSet.getString("name"));
            project.setDescription(resultSet.getString("description"));
            project.setCost(resultSet.getInt("cost"));
            project.setClient(clientDAO.findById(resultSet.getInt("clients_id")));
            project.setAddress(addressDAO.findById(resultSet.getInt("address_id")));
            projects.add(project);
        }
        ConnectionPool.getInstance().releaseConnection();
        return projects;
    }

    @Override
    public Project findById(Integer id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Project project = new Project();
        if(resultSet.next()) {
            project.setId(resultSet.getInt("id"));
            project.setName(resultSet.getString("name"));
            project.setDescription(resultSet.getString("description"));
            project.setCost(resultSet.getInt("cost"));
            project.setClient(clientDAO.findById(resultSet.getInt("clients_id")));
            project.setAddress(addressDAO.findById(resultSet.getInt("address_id")));
        }
        ConnectionPool.getInstance().releaseConnection();
        return project;
    }

    @Override
    public void update(Project project, Integer id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, project.getName());
        statement.setString(2, project.getDescription());
        statement.setInt(3, project.getCost());
        statement.setInt(4, project.getClient().getId());
        statement.setInt(5, project.getAddress().getId());
        statement.setInt(6, id);
        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0) {
            logger.info("Project was updated");
        } else {
            logger.info("Project was not updated");
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
            logger.info("Project deleted");
        } else {
            logger.info("Project not deleted");
        }
        ConnectionPool.getInstance().releaseConnection();

    }
}
