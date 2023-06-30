package org.example.buildingcompany.dao.mybatisimpl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Supplier;
import org.example.buildingcompany.dao.ISupplierDAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class SupplierMyBatis implements ISupplierDAO {
    private final static Logger logger = LogManager.getLogger(SupplierMyBatis.class);
    @Override
    public void insert(Supplier supplier) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(ISupplierDAO.class).insert(supplier);

        } catch (IOException e) {
            logger.error(e);
        }

    }

    @Override
    public List<Supplier> findAll() throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(ISupplierDAO.class).findAll();

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Supplier findById(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(ISupplierDAO.class).findById(id);

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public void update(Supplier supplier, Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(ISupplierDAO.class).update(supplier, id);

        } catch (IOException e) {
            logger.error(e);
        }

    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(ISupplierDAO.class).delete(id);

        } catch (IOException e) {
            logger.error(e);
        }

    }

    @Override
    public Supplier getSupplierByMaterialId(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(ISupplierDAO.class).getSupplierByMaterialId(id);

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Supplier getSupplierByEquipmentId(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(ISupplierDAO.class).getSupplierByEquipmentId(id);

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }
}
