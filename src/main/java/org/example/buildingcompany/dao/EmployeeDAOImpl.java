package org.example.buildingcompany.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Employee;
import org.example.buildingcompany.interfaces.dao.IAddressDAO;
import org.example.buildingcompany.interfaces.dao.IEmployeeDAO;
import org.example.buildingcompany.interfaces.dao.IProjectDAO;
import org.example.buildingcompany.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAOImpl implements IEmployeeDAO {

    private final static Logger logger = LogManager.getLogger(EmployeeDAOImpl.class);
    private static final String INSERT = "INSERT INTO employees (first_name, last_name, hire_date, phone_number, " +
            "address_id, project_id) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM employees WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM employees";
    private static final String UPDATE = "UPDATE employees SET first_name = ?, last_name = ?, hire_date = ?, " +
            "phone_number = ?, address_id = ?, project_id = ?" +
            " WHERE id = ?";
    private static final String DELETE = "DELETE FROM employees WHERE id = ?";
    private static IAddressDAO addressDAO = new AddressDAOImpl();
    private static IProjectDAO projectDAO = new ProjectDAOImpl();

    @Override
    public void insert(Employee employee) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
        statement.setDate(3, employee.getHireDate());
        statement.setString(4, employee.getPhoneNumber());
        statement.setInt(5, employee.getAddress().getId());
        statement.setInt(6, employee.getProject().getId());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection();

    }

    @Override
    public List<Employee> findAll() throws SQLException, InterruptedException {
        List<Employee> employees = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            Employee employee = new Employee();
            employee.setId(resultSet.getInt("id"));
            employee.setFirstName(resultSet.getString("first_name"));
            employee.setLastName(resultSet.getString("last_name"));
            employee.setHireDate(resultSet.getDate("hire_date"));
            employee.setPhoneNumber(resultSet.getString("phone_number"));
            employee.setAddress(addressDAO.findById(resultSet.getInt("address_id")));
            employee.setProject(projectDAO.findById(resultSet.getInt("project_id")));
            employees.add(employee);
        }
        ConnectionPool.getInstance().releaseConnection();
        return employees;
    }

    @Override
    public Employee findById(Integer id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Employee employee = new Employee();
        if(resultSet.next()) {
            employee.setId(resultSet.getInt("id"));
            employee.setFirstName(resultSet.getString("first_name"));
            employee.setLastName(resultSet.getString("last_name"));
            employee.setHireDate(resultSet.getDate("hire_date"));
            employee.setPhoneNumber(resultSet.getString("phone_number"));
            employee.setAddress(addressDAO.findById(resultSet.getInt("address_id")));
            employee.setProject(projectDAO.findById(resultSet.getInt("project_id")));
        }
        ConnectionPool.getInstance().releaseConnection();
        return employee;
    }

    @Override
    public void update(Employee employee, Integer id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
        statement.setDate(3, employee.getHireDate());
        statement.setString(4, employee.getPhoneNumber());
        statement.setInt(5, employee.getAddress().getId());
        statement.setInt(6, employee.getProject().getId());
        statement.setInt(7, id);
        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0) {
            logger.info("Employee updated");
        } else {
            logger.info("Employee not updated");
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
            logger.info("Employee was deleted");
        } else {
            logger.info("Employee not deleted");
        }
        ConnectionPool.getInstance().releaseConnection();

    }
}
