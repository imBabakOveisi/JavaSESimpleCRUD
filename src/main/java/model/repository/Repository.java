package model.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<O, I> {
    public void  save(O o) throws SQLException;
    public void  update(O o) throws SQLException;
    public void  deleteById(I id) throws SQLException;
    public List<O> findAll() throws SQLException;
    public O findById(I id) throws SQLException;
}
