package model.service;

import java.util.List;

public interface Service<O, I> {
    void save(O o) throws Exception;

    void update(O o) throws Exception;

    void deleteById(Integer id) throws Exception;

    List<O> findAll() throws Exception;

    O findById(Integer id) throws Exception;
}
