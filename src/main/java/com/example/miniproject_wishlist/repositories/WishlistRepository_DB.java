package com.example.miniproject_wishlist.repositories;

import com.example.miniproject_wishlist.models.Wishlist;
import com.example.miniproject_wishlist.models.*;
import com.example.miniproject_wishlist.repositories.util.*;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("Wishlist_DB")
public class WishlistRepository_DB implements IWishlistRepository {
    String SQL = null;
    Connection connection = DB_Connector.getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    @Override
    public List<Wish> getWishes(int wishlistID) {
        try {
            List<Wish> wishlist = new ArrayList<>();
            SQL = "SELECT * FROM wish JOIN wishlist_wish USING (WishID) WHERE WishlistID = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, wishlistID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                wishlist.add(new Wish(
                        resultSet.getInt("WishID"),
                        resultSet.getString("WishName"),
                        resultSet.getString("WishLink"),
                        null));
            }
            return wishlist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Wishlist> getAllWishlists(User user) {
        try {
            SQL = "SELECT WishlistName, WishlistID FROM wishlist WHERE UserID = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, user.getUserID());
            resultSet = preparedStatement.executeQuery();
            List<Wishlist> wishlists = new ArrayList<>();
            while (resultSet.next()) {
                wishlists.add(new Wishlist(resultSet.getString("WishlistName"), resultSet.getInt("WishlistID"), user.getUserID()));
            }
            return wishlists;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Wishlist getWishlist(int wishlistID, User user) {
        Wishlist wishlist = null;
        try {
            SQL = "SELECT WishlistName, UserID FROM wishlist WHERE WishlistID = ? AND UserID";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, wishlistID);
            preparedStatement.setInt(2, user.getUserID());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                wishlist = new Wishlist(resultSet.getString("WishlistName"), wishlistID, resultSet.getInt("UserID"));
            }
            return wishlist;

        } catch (SQLException e) {
            return wishlist;
//            throw new RuntimeException(e);

        }
    }

    @Override
    public void addWishlist(Wishlist wishlist) {
        try {
            SQL = "INSERT INTO wishlist (WishlistName, UserID) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, wishlist.getWishlistName());
            preparedStatement.setInt(2, wishlist.getUserID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addWish(Wish wish) {
        try {
            SQL = "INSTERT INTO wishlist (wishName, wishLink) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, wish.getWishName());
            preparedStatement.setString(2, wish.getWishLink());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            int wishID = resultSet.getInt("WishID");
            for (Wishlist wishlist : wish.getWishlists()) {
                SQL = "SELECT WishlistID FROM wishlist WHERE WishlistName = ?";
                preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.setString(1, wishlist.getWishlistName());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    SQL = "INSERT INTO wishlist_wish (WishID, WishlistID) VALUES (?, ?)";
                    preparedStatement = connection.prepareStatement(SQL);
                    preparedStatement.setInt(1, wishID);
                    preparedStatement.setInt(2, resultSet.getInt("WishlistID"));
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
/*
    @Override
    public void deleteWish(Wish wish) {
        try {
            SQL = "delete from wish where WishID = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, "WishId");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


 */


}
