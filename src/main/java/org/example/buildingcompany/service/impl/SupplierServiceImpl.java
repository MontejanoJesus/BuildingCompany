package org.example.buildingcompany.service.impl;

import org.example.buildingcompany.classes.Supplier;
import org.example.buildingcompany.dao.impl.AddressDAOImpl;
import org.example.buildingcompany.dao.impl.SupplierDAOImpl;
import org.example.buildingcompany.dao.interfaces.IAddressDAO;
import org.example.buildingcompany.dao.interfaces.ISupplierDAO;
import org.example.buildingcompany.service.interfaces.ISupplierService;

import java.sql.SQLException;
import java.util.List;

public class SupplierServiceImpl implements ISupplierService {
    private ISupplierDAO supplierDAO = new SupplierDAOImpl();
    private IAddressDAO addressDAO = new AddressDAOImpl();
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
