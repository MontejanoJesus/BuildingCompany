package org.example.buildingcompany.dao.jdbcimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Project;
import org.example.buildingcompany.dao.IProjectDAO;
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

    @Override
    public void insert(Project project) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        statement.setString(1, project.getName());
        statement.setString(2, project.getDescription());
        statement.setInt(3, project.getCost());
        statement.setLong(4, project.getClient().getId());
        statement.setLong(5, project.getAddress().getId());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);

    }

    @Override
    public List<Project> findAll() throws SQLException, InterruptedException {
        List<Project> projects = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            Project project = createProject(resultSet);
            projects.add(project);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return projects;
    }

    @Override
    public Project findById(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Project project = createProject(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return project;
    }

    @Override
    public void update(Project project, Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, project.getName());
        statement.setString(2, project.getDescription());
        statement.setInt(3, project.getCost());
        statement.setLong(4, project.getClient().getId());
        statement.setLong(5, project.getAddress().getId());
        statement.setLong(6, id);
        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0) {
            logger.info("Project was updated");
        } else {
            logger.info("Project was not updated");
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
            logger.info("Project deleted");
        } else {
            logger.info("Project not deleted");
        }
        ConnectionPool.getInstance().releaseConnection(connection);

    }

    public Project getProjectByEmployeeId(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM projects WHERE " +
                "id = (SELECT project_id FROM employees WHERE id = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Project project = createProject(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return project;
    }

    private Project createProject(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        if(resultSet.next()) {
            project.setId(resultSet.getLong("id"));
            project.setName(resultSet.getString("name"));
            project.setDescription(resultSet.getString("description"));
            project.setCost(resultSet.getInt("cost"));
        }
        return project;
    }
}
