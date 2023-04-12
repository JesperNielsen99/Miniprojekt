package com.example.miniproject_wishlist.repositories;

import com.example.miniproject_wishlist.models.User;
import com.example.miniproject_wishlist.repositories.IUserRepository;
import com.example.miniproject_wishlist.repositories.util.DB_Connector;
import org.springframework.stereotype.Repository;

import java.sql.*;
@Repository("User_DB")
public class UserRepository_DB implements IUserRepository {
    String SQL = null;
    Connection connection = DB_Connector.getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public User createUser(User user) {
        try {
            SQL = "INSTERT INTO user (UserName, Email, Password) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User updateUser(User user) {
        try {
            SQL = "UPDATE user SET UserName = ?, Email = ?, Password = ? WHERE UserID = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getUserID());
            preparedStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public User DeleteUser(User user) {
        try {
            SQL = "SELECT WishlistID FROM wishlist WHERE UserID = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, user.getUserID());
            preparedStatement.executeQuery();
            while (resultSet.next()) {
                int wishlistID = resultSet.getInt("WishlistID");
                SQL = "DELETE FROM wishlist_wish WHERE wishlistID = ?";
                preparedStatement.setInt(1, wishlistID);
                preparedStatement.executeUpdate();
            }
            SQL = "DELETE FROM wishlist WHERE UserID = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, user.getUserID());
            preparedStatement.executeUpdate();
            SQL = "DELETE FROM user WHERE UserID = ?";
            preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, user.getUserID());
            preparedStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(String email, String password) {
        try {
            SQL = "SELECT * FROM user WHERE Email = ? AND Password = ?";
            PreparedStatement preparedStatementUserID = connection.prepareStatement(SQL);
            preparedStatementUserID.setString(1, email);
            preparedStatementUserID.setString(2, password);
            resultSet = preparedStatementUserID.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User( resultSet.getInt("UserID"), resultSet.getString("UserName"), resultSet.getString("Email"), resultSet.getString("Password"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
