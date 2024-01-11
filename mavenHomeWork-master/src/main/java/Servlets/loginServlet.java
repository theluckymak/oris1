package Servlets;

import Models.User;
import Repositories.AccountRepository;
import Repositories.AccountRepositoryJdbclmpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/javaServlet";

    AccountRepository accountRepository;

    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            accountRepository = new AccountRepositoryJdbclmpl(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountUsername = request.getParameter("username");
        String accountPassword = request.getParameter("password");
        System.out.println(" username: " + accountUsername);
        System.out.println(" password: " + accountPassword);

        User user = User.builder()
                .userName(accountUsername)
                .userPassword(accountPassword)
                .build();

        try {
            if(accountRepository.login(accountUsername, accountPassword, user)) {
                response.sendRedirect("/home");
            }else {
                response.sendRedirect("/login?error=1");
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }


    }
}
