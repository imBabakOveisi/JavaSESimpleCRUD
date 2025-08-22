package model.tools;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionProvider {

    @Getter
    private final static ConnectionProvider provider = new ConnectionProvider();

    private ConnectionProvider() {
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE",
                "javase",
                "java123"
        );
    }

    public int getNextId(String sequenceName) throws SQLException {
        ResultSet resultSet = getProvider().getConnection().prepareStatement(
                String.format("SELECT %S.nextval AS NEXT_ID FROM DUAL", sequenceName)
        ).executeQuery();
        resultSet.next();
        return resultSet.getInt("NEXT_ID");
    }
}
