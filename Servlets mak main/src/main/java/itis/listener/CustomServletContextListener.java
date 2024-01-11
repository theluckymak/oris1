package itis.listener;

import itis.repository.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomServletContextListener implements ServletContextListener {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "gjhfqr102";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-200";
    private static final String DB_DRIVER = "org.postgresql.Driver";


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        ServletContext servletContext = servletContextEvent.getServletContext();

        AccountsRepository accountsRepository = new AccountsRepositoryJdbcImpl(dataSource);
        servletContext.setAttribute("accountRep", accountsRepository);
        SignUpService signUpService = new SignUpServiceImpl(accountsRepository);
        servletContext.setAttribute("signService", signUpService);
        FilesRepository filesRepository = new FilesRepositoryJdbcImpl(dataSource);
        FileService fileService = new FileServiceImpl(filesRepository);
        servletContext.setAttribute("filesRepository", filesRepository);
        servletContext.setAttribute("filesUploadService", fileService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}