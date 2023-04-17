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
            SQL = "SELECT * FROM wish WHERE WishlistID = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, wishlistID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                wishlist.add(new Wish(
                        resultSet.getInt("WishID"),
                        resultSet.getString("WishName"),
                        resultSet.getString("WishLink"),
                        resultSet.getInt("WishlistID")
                ));
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
    public Wishlist getWishlist(int wishlistID) {
        try {
            SQL = "SELECT WishlistName, UserID FROM wishlist WHERE WishlistID = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, wishlistID);
            resultSet = preparedStatement.executeQuery();
            Wishlist wishlist = null;
            if (resultSet.next()) {
                wishlist = new Wishlist(resultSet.getString("WishlistName"), wishlistID, resultSet.getInt("UserID"));
            }
            return wishlist;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(User user){
        try{
            SQL = "INSERT INTO user (UserName, Email, Password)\n" +
                    "VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
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
    public void addWish(Wish wish, int wishlistID) {
        try {
            SQL = "INSERT INTO wish (wishName, wishLink, wishlistID) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, wish.getWishName());
            preparedStatement.setString(2, wish.getWishLink());
            preparedStatement.setInt(3, wishlistID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteWish(int wishID){
        try{
            SQL = "delete from wish where WishID = (?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1,wishID);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteWishlist(int wishlistID){
        try{
            SQL = "delete from wishlist where WishlistID = (?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, wishlistID);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }





}
