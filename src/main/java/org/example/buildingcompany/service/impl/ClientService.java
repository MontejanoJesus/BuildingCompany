package org.example.buildingcompany.service.impl;

import org.example.buildingcompany.classes.Client;
import org.example.buildingcompany.dao.impl.AddressDAOImpl;
import org.example.buildingcompany.dao.impl.ClientDAOImpl;
import org.example.buildingcompany.dao.interfaces.IAddressDAO;
import org.example.buildingcompany.dao.interfaces.IClientDAO;
import org.example.buildingcompany.service.interfaces.IClientService;

import java.sql.SQLException;
import java.util.List;

public class ClientService implements IClientService {
    private IClientDAO clientDAO = new ClientDAOImpl();
    private IAddressDAO addressDAO = new AddressDAOImpl();
    @Override
    public void insert(Client client) throws SQLException, InterruptedException {
        clientDAO.insert(client);

    }

    @Override
    public Client getById(Long id) throws SQLException, InterruptedException {
        Client client = clientDAO.findById(id);
        client.setAddress(addressDAO.getAddressByClientId(id));
        return client;
    }

    @Override
    public List<Client> getAll() throws SQLException, InterruptedException {
        List<Client> clients = clientDAO.findAll();
        for(Client c : clients) {
            c.setAddress(addressDAO.getAddressByClientId(c.getId()));
        }
        return clients;
    }

    @Override
    public void update(Client client, Long id) throws SQLException, InterruptedException {
        clientDAO.update(client, id);

    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        clientDAO.delete(id);

    }
}
