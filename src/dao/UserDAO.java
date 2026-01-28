package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import util.DBUtil;

public class UserDAO {

    private static final String INSERT_USER_SQL =
        "INSERT INTO users (user_id, name, email, password, role, security_question, security_answer) " +
        "VALUES (users_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_BY_EMAIL_SQL =
        "SELECT user_id, name, email, password, role, security_question, security_answer " +
        "FROM users WHERE email = ?";

    // Register user
    public boolean registerUser(User user) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_USER_SQL)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getSecurityQuestion());
            ps.setString(6, user.getSecurityAnswer());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Login user
    public User login(String email, String password) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_EMAIL_SQL)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                    user.setSecurityQuestion(rs.getString("security_question"));
                    user.setSecurityAnswer(rs.getString("security_answer"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
