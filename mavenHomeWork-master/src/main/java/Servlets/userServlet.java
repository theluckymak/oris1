package Servlets;

import Models.User;
import Repositories.UserRepository;
import Repositories.UserRepositoryJdbclmpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@WebServlet("/users")
class UserServlet extends HttpServlet {

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/javaServlet";

    private UserRepository userRepository;

    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();
            userRepository = new UserRepositoryJdbclmpl(connection, statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> result;

        try {
            result = userRepository.findAll();
        } catch (Exception e) {
            // Handle the exception, e.g., log the error, redirect to an error page, or display a generic message
            throw new RuntimeException("Error retrieving users from the database", e);
        }

        request.setAttribute("usersForJsp", result);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        // Close any open resources (e.g., database connection) during servlet shutdown
        try {
            userRepository.closeResources();
        } catch (SQLException e) {
            // Log or handle the exception appropriately
            e.printStackTrace();
        }
    }
}
