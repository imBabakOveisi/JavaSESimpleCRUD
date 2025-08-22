package model.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<O, I> {
    void  save(O o) throws SQLException;
    void  update(O o) throws SQLException;
    void  deleteById(I id) throws SQLException;
    List<O> findAll() throws SQLException;
    O findById(I id) throws SQLException;
}
