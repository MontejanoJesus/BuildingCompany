package org.example.buildingcompany.service.impl;

import org.example.buildingcompany.classes.Supplier;
import org.example.buildingcompany.dao.IAddressDAO;
import org.example.buildingcompany.dao.ISupplierDAO;
import org.example.buildingcompany.mybatis.daoimpl.AddressMyBatis;
import org.example.buildingcompany.mybatis.daoimpl.SupplierMyBatis;
import org.example.buildingcompany.service.ISupplierService;

import java.sql.SQLException;
import java.util.List;

public class SupplierServiceImpl implements ISupplierService {
    private ISupplierDAO supplierDAO = new SupplierMyBatis();
    private IAddressDAO addressDAO = new AddressMyBatis();
    @Override
    public void insert(Supplier supplier) throws SQLException, InterruptedException {
        supplierDAO.insert(supplier);

    }

    @Override
    public Supplier getById(Long id) throws SQLException, InterruptedException {
        Supplier supplier = supplierDAO.findById(id);
        supplier.setAddress(addressDAO.getAddressBySupplierId(id));
        return supplier;
    }

    @Override
    public List<Supplier> getAll() throws SQLException, InterruptedException {
        List<Supplier> suppliers = supplierDAO.findAll();
        for(Supplier s : suppliers) {
            s.setAddress(addressDAO.getAddressBySupplierId(s.getId()));
        }
        return suppliers;
    }

    @Override
    public void update(Supplier supplier, Long id) throws SQLException, InterruptedException {
        supplierDAO.update(supplier, id);

    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        supplierDAO.delete(id);

    }
}
