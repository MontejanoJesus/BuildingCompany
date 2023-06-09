package org.example.buildingcompany.dao;

import org.example.buildingcompany.classes.Supplier;

import java.sql.SQLException;

public interface ISupplierDAO extends IDAO<Supplier> {
    Supplier getSupplierByMaterialId(Long id) throws SQLException, InterruptedException;
    Supplier getSupplierByEquipmentId(Long id) throws SQLException, InterruptedException;
}
