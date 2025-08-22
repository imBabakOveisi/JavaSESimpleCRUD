package model.service;

import model.entity.Person;

import java.sql.SQLException;
import java.util.List;

public interface Service<O, I> {
    void save(O o) throws SQLException;
    void update(O o) throws SQLException;
    void deleteById(Integer id) throws SQLException;
    List<O> findAll() throws SQLException;
    O findById(Integer id) throws SQLException;
}
