package com.example.miniproject_wishlist.repositories;

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

    @Override
    public List<Wish> getWishes() {
        try {
            SQL = "SELECT * FROM wish";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
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
    public Wish getWish(String wishName) {
        try {
            Connection connection = DB_Connector.getConnection();
            SQL = "SELECT * From wish WHERE WishName = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, wishName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Wish(resultSet.getString("WishName"), resultSet.getString("WishLink"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
