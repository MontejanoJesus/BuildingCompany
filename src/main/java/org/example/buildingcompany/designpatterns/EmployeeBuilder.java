package org.example.buildingcompany.designpatterns;

import org.example.buildingcompany.classes.Address;
import org.example.buildingcompany.classes.Employee;
import org.example.buildingcompany.classes.Project;

import java.sql.Date;

public class EmployeeBuilder {

    private String firstName;
    private String lastName;
    private Date hireDate;
    private String phoneNumber;
    private Address address;
    private Project project;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public Project getProject() {
        return project;
    }

    public EmployeeBuilder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public EmployeeBuilder hireDate(Date hireDate) {
        this.hireDate = hireDate;
        return this;
    }
    public EmployeeBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    public EmployeeBuilder address(Address address) {
        this.address = address;
        return this;
    }
    public EmployeeBuilder project(Project project) {
        this.project = project;
        return this;
    }
    public Employee build() {
        Employee employee = new Employee(this);
        return employee;
    }

}
