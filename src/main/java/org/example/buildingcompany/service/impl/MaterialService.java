package org.example.buildingcompany.service.impl;

import org.example.buildingcompany.classes.Material;
import org.example.buildingcompany.dao.impl.MaterialDAOImpl;
import org.example.buildingcompany.dao.impl.SupplierDAOImpl;
import org.example.buildingcompany.dao.interfaces.IMaterialDAO;
import org.example.buildingcompany.dao.interfaces.ISupplierDAO;
import org.example.buildingcompany.service.interfaces.IMaterialService;

import java.sql.SQLException;
import java.util.List;

public class MaterialService implements IMaterialService {
    private IMaterialDAO materialDAO = new MaterialDAOImpl();
    private ISupplierDAO supplierDAO = new SupplierDAOImpl();
    @Override
    public void insert(Material material) throws SQLException, InterruptedException {
        materialDAO.insert(material);
    }

    @Override
    public Material getById(Long id) throws SQLException, InterruptedException {
        Material material = materialDAO.findById(id);
        material.setSupplier(supplierDAO.getSupplierByMaterialId(id));
        return material;
    }

    @Override
    public List<Material> getAll() throws SQLException, InterruptedException {
        List<Material> materials = materialDAO.findAll();
        for(Material m : materials) {
            m.setSupplier(supplierDAO.getSupplierByMaterialId(m.getId()));
        }
        return materials;
    }

    @Override
    public void update(Material material, Long id) throws SQLException, InterruptedException {
        materialDAO.update(material, id);
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        materialDAO.delete(id);
    }
}
