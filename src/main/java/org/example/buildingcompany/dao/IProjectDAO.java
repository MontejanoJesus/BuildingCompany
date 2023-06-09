package org.example.buildingcompany.dao;

import org.example.buildingcompany.classes.Project;

import java.sql.SQLException;

public interface IProjectDAO extends IDAO<Project> {
    Project getProjectByEmployeeId(Long id) throws SQLException, InterruptedException;
}
