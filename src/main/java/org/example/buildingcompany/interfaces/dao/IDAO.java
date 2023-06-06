package org.example.buildingcompany.interfaces.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    void insert(T t) throws SQLException, InterruptedException;
    List<T> findAll() throws SQLException, InterruptedException;
    T findById(Integer id) throws SQLException, InterruptedException;
    void update(T t, Integer id) throws SQLException, InterruptedException;
    void delete(Integer id) throws SQLException, InterruptedException;
}
