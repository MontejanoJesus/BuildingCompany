package org.example.buildingcompany.service.impl;

import org.example.buildingcompany.classes.Employee;
import org.example.buildingcompany.dao.impl.AddressDAOImpl;
import org.example.buildingcompany.dao.impl.EmployeeDAOImpl;
import org.example.buildingcompany.dao.impl.ProjectDAOImpl;
import org.example.buildingcompany.dao.interfaces.IAddressDAO;
import org.example.buildingcompany.dao.interfaces.IEmployeeDAO;
import org.example.buildingcompany.dao.interfaces.IProjectDAO;
import org.example.buildingcompany.service.interfaces.IEmployeeService;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService implements IEmployeeService {

    private IEmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private IProjectDAO projectDAO = new ProjectDAOImpl();
    private IAddressDAO addressDAO = new AddressDAOImpl();
    @Override
    public void insert(Employee employee) throws SQLException, InterruptedException {
        employeeDAO.insert(employee);
    }

    @Override
    public Employee getById(Long id) throws SQLException, InterruptedException {
        Employee employee = employeeDAO.findById(id);
        employee.setAddress(addressDAO.getAddressByEmployeeId(id));
        employee.setProject(projectDAO.getProjectByEmployeeId(id));
        return employee;
    }

    @Override
    public List<Employee> getAll() throws SQLException, InterruptedException {
        List<Employee> employees = employeeDAO.findAll();
        for(Employee e : employees) {
            e.setAddress(addressDAO.getAddressByEmployeeId(e.getId()));
            e.setProject(projectDAO.getProjectByEmployeeId(e.getId()));
        }
        return employees;
    }

    @Override
    public void update(Employee employee, Long id) throws SQLException, InterruptedException {
        employeeDAO.update(employee, id);

    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        employeeDAO.delete(id);

    }
}