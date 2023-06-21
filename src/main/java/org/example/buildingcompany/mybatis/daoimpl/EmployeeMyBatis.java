package org.example.buildingcompany.mybatis.daoimpl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.buildingcompany.classes.Employee;
import org.example.buildingcompany.dao.IEmployeeDAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class EmployeeMyBatis implements IEmployeeDAO {
    private final static Logger logger = LogManager.getLogger(EmployeeMyBatis.class);
    @Override
    public void insert(Employee employee) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(IEmployeeDAO.class).insert(employee);

        } catch (IOException e) {
            logger.error(e);
        }

    }

    @Override
    public List<Employee> findAll() throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(IEmployeeDAO.class).findAll();

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public Employee findById(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            return session.getMapper(IEmployeeDAO.class).findById(id);

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    public void update(Employee employee, Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(IEmployeeDAO.class).update(employee, id);

        } catch (IOException e) {
            logger.error(e);
        }

    }

    @Override
    public void delete(Long id) throws SQLException, InterruptedException {
        try(InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession(true)){
            session.getMapper(IEmployeeDAO.class).delete(id);

        } catch (IOException e) {
            logger.error(e);
        }

    }
}
