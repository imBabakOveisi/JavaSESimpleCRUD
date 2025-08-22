package model.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<O, I> {
    void  save(O object) throws SQLException;
    void  update(O object) throws SQLException;
    void  deleteById(I id) throws SQLException;
    List<O> findAll() throws SQLException;
    O findById(I id) throws SQLException;
}
