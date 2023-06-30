package org.example.buildingcompany.dao.jdbcimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Client;
import org.example.buildingcompany.dao.IClientDAO;
import org.example.buildingcompany.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements IClientDAO {

    private final static Logger logger = LogManager.getLogger(ClientDAOImpl.class);
    private static final String INSERT = "INSERT INTO clients (name, phone_number, address_id) VALUES (?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM clients WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM clients";
    private static final String UPDATE = "UPDATE clients SET name = ?, phone_number = ?, address_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM clients WHERE id = ?";
    @Override
    public void insert(Client client) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT);
        statement.setString(1, client.getName());
        statement.setString(2, client.getPhoneNumber());
        statement.setLong(3, client.getAddress().getId());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);

    }

    @Override
    public List<Client> findAll() throws SQLException, InterruptedException {
        List<Client> clients = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            Client client = createClient(resultSet);
            clients.add(client);
        }
        ConnectionPool.getInstance().releaseConnection(connection);
        return clients;
    }

    @Override
    public Client findById(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Client client = createClient(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return client;
    }

    @Override
    public void update(Client client, Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);
        statement.setString(1, client.getName());
        statement.setString(2, client.getPhoneNumber());
        statement.setLong(3, client.getAddress().getId());
        statement.setLong(4, id);
        int rowsUpdated = statement.executeUpdate();
        if(rowsUpdated > 0) {
            logger.info("Client updated");
        } else {
            logger.info("Client not updated");
        }
        ConnectionPool.getInstance().releaseConnection(connection);

    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);
        statement.setLong(1, id);
        int rowsDeleted = statement.executeUpdate();
        if(rowsDeleted > 0) {
            logger.info("Client deleted");
        } else {
            logger.info("Client not deleted");
        }
        ConnectionPool.getInstance().releaseConnection(connection);

    }

    public Client getClientByProjectId(Long id) throws SQLException, InterruptedException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM clients WHERE " +
                "id = (SELECT clients_id FROM projects WHERE id = ?)");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        Client client = createClient(resultSet);
        ConnectionPool.getInstance().releaseConnection(connection);
        return client;
    }

    private Client createClient(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        if(resultSet.next()) {
            client.setId(resultSet.getLong("id"));
            client.setName(resultSet.getString("name"));
            client.setPhoneNumber(resultSet.getString("phone_number"));
        }
        return client;
    }
}
