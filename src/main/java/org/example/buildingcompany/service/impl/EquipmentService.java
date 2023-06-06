package org.example.buildingcompany.service.impl;

import org.example.buildingcompany.classes.Equipment;
import org.example.buildingcompany.dao.impl.EquipmentDAOImpl;
import org.example.buildingcompany.dao.impl.SupplierDAOImpl;
import org.example.buildingcompany.dao.interfaces.IEquipmentDAO;
import org.example.buildingcompany.dao.interfaces.ISupplierDAO;
import org.example.buildingcompany.service.interfaces.IEquipmentService;

import java.sql.SQLException;
import java.util.List;

public class EquipmentService implements IEquipmentService {
    private IEquipmentDAO equipmentDAO = new EquipmentDAOImpl();
    private ISupplierDAO supplierDAO = new SupplierDAOImpl();
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
