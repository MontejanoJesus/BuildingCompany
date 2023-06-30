package org.example.buildingcompany.service.impl;

import org.example.buildingcompany.classes.Project;
import org.example.buildingcompany.dao.IAddressDAO;
import org.example.buildingcompany.dao.IClientDAO;
import org.example.buildingcompany.dao.IProjectDAO;
import org.example.buildingcompany.dao.mybatisimpl.AddressMyBatis;
import org.example.buildingcompany.dao.mybatisimpl.ClientMyBatis;
import org.example.buildingcompany.dao.mybatisimpl.ProjectMyBatis;
import org.example.buildingcompany.service.IProjectService;

import java.sql.SQLException;
import java.util.List;

public class ProjectService implements IProjectService {
    private IProjectDAO projectDAO = new ProjectMyBatis();
    private IClientDAO clientDAO = new ClientMyBatis();
    private IAddressDAO addressDAO = new AddressMyBatis();
    @Override
    public void insert(Project project) throws SQLException, InterruptedException {
        projectDAO.insert(project);

    }

    @Override
    public Project getById(Long id) throws SQLException, InterruptedException {
        Project project = projectDAO.findById(id);
        project.setAddress(addressDAO.getAddressByProjectId(id));
        project.setClient(clientDAO.getClientByProjectId(id));
        return project;
    }

    @Override
    public List<Project> getAll() throws SQLException, InterruptedException {
        List<Project> projects = projectDAO.findAll();
        for(Project p : projects) {
            p.setAddress(addressDAO.getAddressByProjectId(p.getId()));
            p.setClient(clientDAO.getClientByProjectId(p.getId()));
        }
        return projects;
    }

    @Override
    public void update(Project project, Long id) throws SQLException, InterruptedException {
        projectDAO.update(project, id);

    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        projectDAO.delete(id);

    }
}
