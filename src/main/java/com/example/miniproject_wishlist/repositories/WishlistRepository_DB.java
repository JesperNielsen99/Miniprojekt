package com.example.miniproject_wishlist.repositories;

import com.example.miniproject_wishlist.dto.EmailDTO;
import com.example.miniproject_wishlist.dto.WishDTO;
import com.example.miniproject_wishlist.models.Wishlist;
import com.example.miniproject_wishlist.models.Wish;
import com.example.miniproject_wishlist.repositories.util.DB_Connector;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("Wishlist_DB")
public class WishlistRepository_DB implements IWishlistRepository {
    String SQL = null;
    Connection connection = DB_Connector.getConnection();
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    public List<String> getEmails() {
        try {
            SQL = "SELECT Email FROM user";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            List<String> emails = new ArrayList<>();
            while (resultSet.next()) {
                emails.add(resultSet.getString("Email"));
            }
            return emails;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Wish> getWishes(EmailDTO email) {
        try {
            int userID = getUserID(email);
            SQL = "SELECT WishlistID FROM wishlist WHERE UserID = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            int wishlistID = 0;
            if (resultSet.next()) {
                wishlistID = resultSet.getInt("WishlistID");
            }
            SQL = "SELECT * FROM wish JOIN wishlist_wish USING (WishID) WHERE WishlistID = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, wishlistID);
            resultSet = preparedStatement.executeQuery();

            List<Wish> wishlist = new ArrayList<>();

            while (resultSet.next()) {
                wishlist.add(new Wish(resultSet.getString("WishName"), resultSet.getString("WishLink")));
            }
            return wishlist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Wishlist> getAllWishlists(EmailDTO email) {
        try{
        SQL = "SELECT WishlistName FROM wishlist WHERE UserID = ?";
        preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, getUserID(email));
        resultSet = preparedStatement.executeQuery();

        List<Wishlist> wishlists = new ArrayList<>();
        while (resultSet.next()) {
            wishlists.add(new Wishlist(resultSet.getString("WishlistName"),email));
        }
        return wishlists;

    } catch (SQLException e){
        throw new RuntimeException(e);
    }
    }

    @Override
    public void addWishlist(Wishlist wishlist) {
        try {
            SQL = "INSERT INTO wishlist (WishlistName, UserID) VALUES (?, ?);";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, wishlist.getWishlistName());
            preparedStatement.setInt(2, getUserID(wishlist.getEmail()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public void addWish(WishDTO wish) {
        try {
            SQL = "INSTERT INTO wishlist(wishName,wishLink) VALUES (?, ?);";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, wish.getWishName());
            preparedStatement.setString(2, wish.getWishLink());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


//-----------------------------------------------------Helper--methods----------------------------------------------\\
    private int getUserID(EmailDTO email) {
        try {
            SQL = "SELECT UserID FROM user WHERE Email = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, email.getEmail());
            resultSet = preparedStatement.executeQuery();
            int userID = 0;
            if (resultSet.next()) {
                userID = resultSet.getInt("UserID");
            }
            return userID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
