package service;

import dao.UserDAO;
import model.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) {
        // Business rules
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            System.out.println("Invalid email format");
            return false;
        }

        if (!user.getRole().equalsIgnoreCase("BUYER") &&
            !user.getRole().equalsIgnoreCase("SELLER")) {
            System.out.println("Role must be BUYER or SELLER");
            return false;
        }

        return userDAO.registerUser(user);
    }

    public User login(String email, String password) {
        return userDAO.login(email, password);
    }
}
