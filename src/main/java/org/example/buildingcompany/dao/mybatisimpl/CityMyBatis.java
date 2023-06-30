package org.example.buildingcompany.dao.mybatisimpl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.City;
import org.example.buildingcompany.dao.ICityDAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class CityMyBatis implements ICityDAO {
    private final static Logger logger = LogManager.getLogger(CityMyBatis.class);
    @Override
    public City getCityByAddressId(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(ICityDAO.class).getCityByAddressId(id);

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public void insert(City city) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(ICityDAO.class).insert(city);

        } catch (IOException e) {
            logger.error(e);
        }

    }

    @Override
    public List<City> findAll() throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(ICityDAO.class).findAll();

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public City findById(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(ICityDAO.class).findById(id);

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public void update(City city, Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(ICityDAO.class).update(city, id);

        } catch (IOException e) {
            logger.error(e);
        }

    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(ICityDAO.class).delete(id);

        } catch (IOException e) {
            logger.error(e);
        }

    }
}
