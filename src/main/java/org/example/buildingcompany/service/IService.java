package org.example.buildingcompany.service;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    void insert(T t) throws SQLException, InterruptedException;
    T getById(Long id) throws SQLException, InterruptedException;
    List<T> getAll() throws SQLException, InterruptedException;
    void update(T t, Long id) throws SQLException, InterruptedException;
    void delete(Long id) throws SQLException, InterruptedException;
}
