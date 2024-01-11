package itis.servlets;

import itis.models.User;
import itis.repository.AccountsRepository;
import itis.repository.AccountsRepositoryJdbcImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@WebServlet("/save")
public class SaveServlet extends HttpServlet {

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "gjhfqr102";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-200";

    private AccountsRepository accountsRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        accountsRepository = (AccountsRepository) config.getServletContext().getAttribute("accountRep");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/save.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountName = request.getParameter("name");
        String accountSurname = request.getParameter("surname");
        Integer accountAge = Integer.valueOf(request.getParameter("age"));

        User user = User.builder()
                .nameOfUser(accountName)
                .surnameOfUser(accountSurname)
                .ageOfUser(accountAge)
                .build();
        try {
            accountsRepository.save(user);
            response.sendRedirect("/minimal");
        } catch (SQLException e) {
            response.sendRedirect("/save");
            throw new RuntimeException(e);
        }
    }
}
