package org.example.buildingcompany.interfaces.service;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    void insert(T t) throws SQLException, InterruptedException;
    T getById(Integer id) throws SQLException, InterruptedException;
    List<T> getAll() throws SQLException, InterruptedException;
    void update(T t, Integer id) throws SQLException, InterruptedException;
    void delete(Integer id) throws SQLException, InterruptedException;
}
