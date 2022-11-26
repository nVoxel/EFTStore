package com.voxeldev.eftstore.web.listeners;

import com.voxeldev.eftstore.dao.impls.*;
import com.voxeldev.eftstore.services.impls.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class MainContextListener implements javax.servlet.ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String databaseMasterPassword;
        String databaseMasterUser;
        String databaseUrl;
        String avatarsStoragePath;
        
        Properties properties = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            properties.load(loader.getResourceAsStream("app.properties"));
            
            databaseUrl = properties.getProperty("db.url");
            databaseMasterUser = properties.getProperty("db.master_username");
            databaseMasterPassword = properties.getProperty("db.master_password");
            avatarsStoragePath = properties.getProperty("avatars.path");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource(
                databaseUrl, databaseMasterUser, databaseMasterPassword
        );
        
        ServletContext servletContext = sce.getServletContext();
        
        AvatarsRepositoryImpl avatarsRepository = new AvatarsRepositoryImpl(driverManagerDataSource);
        CartItemsRepositoryImpl cartItemsRepository = new CartItemsRepositoryImpl(driverManagerDataSource);
        StoreItemsRepositoryImpl storeItemsRepository = new StoreItemsRepositoryImpl(driverManagerDataSource);
        UsersRepositoryImpl usersRepository = new UsersRepositoryImpl(driverManagerDataSource);
        SessionsRepositoryImpl sessionsRepository = new SessionsRepositoryImpl(driverManagerDataSource);
        PasswordsServiceImpl passwordsService = new PasswordsServiceImpl();
        AvatarsServiceImpl avatarsService = new AvatarsServiceImpl(avatarsStoragePath, avatarsRepository, usersRepository);
        UsersServiceImpl usersService = new UsersServiceImpl(usersRepository, passwordsService);
        SignUpServiceImpl signUpService = new SignUpServiceImpl(usersRepository, usersService);
        SessionsServiceImpl sessionsService = new SessionsServiceImpl(sessionsRepository);
        AuthenticationServiceImpl authenticationService = new AuthenticationServiceImpl(usersRepository, sessionsService, passwordsService);
        CartItemsServiceImpl cartItemsService = new CartItemsServiceImpl(storeItemsRepository);
        
        servletContext.setAttribute("AvatarsRepository", avatarsRepository);
        servletContext.setAttribute("CartItemsRepository", cartItemsRepository);
        servletContext.setAttribute("StoreItemsRepository", storeItemsRepository);
        servletContext.setAttribute("UsersRepository", usersRepository);
        servletContext.setAttribute("SessionsRepository", sessionsRepository);
        servletContext.setAttribute("AuthenticationService", authenticationService);
        servletContext.setAttribute("AvatarsService", avatarsService);
        servletContext.setAttribute("PasswordService", passwordsService);
        servletContext.setAttribute("SignUpService", signUpService);
        servletContext.setAttribute("UsersService", usersService);
        servletContext.setAttribute("SessionsService", sessionsService);
        servletContext.setAttribute("CartItemsService", cartItemsService);
    }
}
