package org.example.buildingcompany.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    void insert(T t) throws SQLException, InterruptedException;
    List<T> findAll() throws SQLException, InterruptedException;
    T findById(Long id) throws SQLException, InterruptedException;
    void update(T t, Long id) throws SQLException, InterruptedException;
    void delete(Long id) throws SQLException, InterruptedException;
}
