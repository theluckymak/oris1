package Repositories;

import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepositoryJdbclmpl implements AccountRepository {

    private final Connection connection;
    private static final String SQL_INSERT = "insert into users(username, email, password) values ";

    public AccountRepositoryJdbclmpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(User user) throws SQLException {
        String sql = SQL_INSERT + "(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getUserEmail());
        preparedStatement.setString(3, user.getUserPassword());

        preparedStatement.executeUpdate();
        System.out.println("Executed");
    }

    @Override
    public boolean login(String username, String password, User user) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUserName());
        ResultSet resultSet = preparedStatement.executeQuery();
        String userAcc = "";
        String passAcc = "";

        while (resultSet.next()){
            userAcc = resultSet.getString("username");
            passAcc = resultSet.getString("password");
            System.out.println("Retrieved username: " + userAcc);
            System.out.println("Retrieved password: " + passAcc);
        }

        if(userAcc != null && passAcc != null && userAcc.equals(username) && passAcc.equals(password)){
            return true;
        }else {
            return false;
        }

    }
}
