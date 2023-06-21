package org.example.buildingcompany.mybatis.daoimpl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Country;
import org.example.buildingcompany.dao.ICountryDAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class CountryMyBatis implements ICountryDAO {
    private final static Logger logger = LogManager.getLogger(CountryMyBatis.class);
    @Override
    public Country getCountryByCityId(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(ICountryDAO.class).getCountryByCityId(id);

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public void insert(Country country) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)) {
            session.getMapper(ICountryDAO.class).insert(country);
        }
        catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    public List<Country> findAll() throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(ICountryDAO.class).findAll();
        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Country findById(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(ICountryDAO.class).findById(id);

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public void update(Country country, Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(ICountryDAO.class).update(country, id);

        } catch (IOException e) {
            logger.error(e);
        }

    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(ICountryDAO.class).delete(id);

        } catch (IOException e) {
            logger.error(e);
        }

    }
}
