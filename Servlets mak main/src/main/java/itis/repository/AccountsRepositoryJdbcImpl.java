package itis.repository;

import itis.models.User;

import javax.sql.DataSource;
import java.net.DatagramSocket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountsRepositoryJdbcImpl implements AccountsRepository {

    private DataSource dataSource;
    private static final String SQL_INSERT = "insert into accounts(first_name, second_name, age) values ";

    public AccountsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public void save(User user) throws SQLException {
        String sql = SQL_INSERT + "(?, ?, ?)";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getNameOfUser());
        preparedStatement.setString(2, user.getSurnameOfUser());
        preparedStatement.setInt(3, user.getAgeOfUser());
        preparedStatement.executeUpdate();
        System.out.println("Done");
    }
}
