package org.example.buildingcompany.dao.interfaces;

import org.example.buildingcompany.classes.Project;

import java.sql.SQLException;

public interface IProjectDAO extends IDAO<Project> {
    Project getProjectByEmployeeId(Long id) throws SQLException, InterruptedException;
}
