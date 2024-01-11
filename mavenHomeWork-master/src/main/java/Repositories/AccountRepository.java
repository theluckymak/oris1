package Repositories;

import Models.User;

import java.sql.SQLException;

public interface AccountRepository {

    void save(User user) throws SQLException;
    boolean login (String username, String password, User user) throws SQLException;
}
