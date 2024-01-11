package Repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public interface UserRepository {
    Connection connection = null;
    Statement statement = null;

    List findAll();

    default void closeResources() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
