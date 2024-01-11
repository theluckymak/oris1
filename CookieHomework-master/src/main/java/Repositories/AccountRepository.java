package Repositories;

import Models.User;

import java.sql.SQLException;
import java.util.UUID;

public interface AccountRepository {

    void save (User user) throws SQLException;
    boolean login (String username, String password, User user) throws SQLException;
    boolean findUUID (UUID uuid) throws SQLException;

//    UUID getUserUUID(String username) throws SQLException;

    UUID addUUD (String username, User user) throws SQLException;
}
