package Repositories;

import Models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbclmpl implements UserRepository {
    private Connection connection;
    private Statement statement;

    private static final String SQL_SELECT_FROM_DRIVER = "select user_id,username, email, password from users";

    public UserRepositoryJdbclmpl(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }


    @Override
    public List findAll() {
        try {
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_FROM_DRIVER);
            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("user_id"))
                        .userName(resultSet.getString("username"))
                        .userEmail(resultSet.getString("email"))
                        .userPassword(resultSet.getString("password"))
                        .build();

                result.add(user);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeResources() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
