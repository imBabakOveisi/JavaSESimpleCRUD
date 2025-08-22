package model.tools;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<O> {
    O mapper(ResultSet resultSet) throws SQLException;
}
