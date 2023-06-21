package org.example.buildingcompany.mybatis.daoimpl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Equipment;
import org.example.buildingcompany.dao.IEquipmentDAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class EquipmentMyBatis implements IEquipmentDAO {
    private final static Logger logger = LogManager.getLogger(EquipmentMyBatis.class);
    @Override
    public void insert(Equipment equipment) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(IEquipmentDAO.class).insert(equipment);

        } catch (IOException e) {
            logger.error(e);
        }

    }

    @Override
    public List<Equipment> findAll() throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(IEquipmentDAO.class).findAll();

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Equipment findById(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(IEquipmentDAO.class).findById(id);

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public void update(Equipment equipment, Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(IEquipmentDAO.class).update(equipment, id);

        } catch (IOException e) {
            logger.error(e);
        }

    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(IEquipmentDAO.class).delete(id);

        } catch (IOException e) {
            logger.error(e);
        }

    }
}
