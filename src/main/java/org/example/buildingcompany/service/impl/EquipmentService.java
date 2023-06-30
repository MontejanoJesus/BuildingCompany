package org.example.buildingcompany.service.impl;

import org.example.buildingcompany.classes.Equipment;
import org.example.buildingcompany.dao.IEquipmentDAO;
import org.example.buildingcompany.dao.ISupplierDAO;
import org.example.buildingcompany.dao.mybatisimpl.EquipmentMyBatis;
import org.example.buildingcompany.dao.mybatisimpl.SupplierMyBatis;
import org.example.buildingcompany.service.IEquipmentService;

import java.sql.SQLException;
import java.util.List;

public class EquipmentService implements IEquipmentService {
    private IEquipmentDAO equipmentDAO = new EquipmentMyBatis();
    private ISupplierDAO supplierDAO = new SupplierMyBatis();
    @Override
    public void insert(Equipment equipment) throws SQLException, InterruptedException {
        equipmentDAO.insert(equipment);
    }

    @Override
    public Equipment getById(Long id) throws SQLException, InterruptedException {
        Equipment equipment = equipmentDAO.findById(id);
        equipment.setSupplier(supplierDAO.getSupplierByEquipmentId(id));
        return equipment;
    }

    @Override
    public List<Equipment> getAll() throws SQLException, InterruptedException {
        List<Equipment> equipment = equipmentDAO.findAll();
        for(Equipment e : equipment) {
            e.setSupplier(supplierDAO.getSupplierByEquipmentId(e.getId()));
        }
        return equipment;
    }

    @Override
    public void update(Equipment equipment, Long id) throws SQLException, InterruptedException {
        equipmentDAO.update(equipment, id);
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        equipmentDAO.delete(id);
    }
}
