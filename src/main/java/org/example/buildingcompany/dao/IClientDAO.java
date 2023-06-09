package org.example.buildingcompany.dao;

import org.example.buildingcompany.classes.Client;

import java.sql.SQLException;

public interface IClientDAO extends IDAO<Client> {
    Client getClientByProjectId(Long id) throws SQLException, InterruptedException;
}
