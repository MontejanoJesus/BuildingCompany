package org.example.buildingcompany.classes;

import java.sql.Date;

public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private Date hireDate;
    private String phoneNumber;
    private Address address;
    private Project project;

    public Employee(){}

    public Employee(String firstName, String lastName, Date hireDate, String phoneNumber, Address address, Project project) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hireDate=" + hireDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address.getAddress() +
                ", project=" + project.getName() +
                '}';
    }
}
