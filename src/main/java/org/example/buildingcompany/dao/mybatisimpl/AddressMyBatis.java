package org.example.buildingcompany.dao.mybatisimpl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Address;
import org.example.buildingcompany.dao.IAddressDAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class AddressMyBatis implements IAddressDAO {
    private final static Logger logger = LogManager.getLogger(AddressMyBatis.class);
    @Override
    public Address getAddressBySupplierId(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(IAddressDAO.class).getAddressBySupplierId(id);

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Address getAddressByEmployeeId(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(IAddressDAO.class).getAddressByEmployeeId(id);

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Address getAddressByClientId(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(IAddressDAO.class).getAddressByClientId(id);

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Address getAddressByProjectId(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(IAddressDAO.class).getAddressByProjectId(id);

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public void insert(Address address) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(IAddressDAO.class).insert(address);

        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    public List<Address> findAll() throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(IAddressDAO.class).findAll();

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Address findById(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(IAddressDAO.class).findById(id);

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public void update(Address address, Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(IAddressDAO.class).update(address, id);

        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(IAddressDAO.class).delete(id);

        } catch (IOException e) {
            logger.error(e);
        }
    }
}
