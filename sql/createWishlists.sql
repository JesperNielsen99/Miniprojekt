DROP DATABASE IF EXISTS wishlists;
CREATE DATABASE wishlists;

USE wishlists;

CREATE TABLE user
(
    UserID   INT (10) NOT NULL AUTO_INCREMENT,
    UserName VARCHAR(50) NOT NULL,
    Email    VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(50) NOT NULL,
    PRIMARY KEY (UserID)
);

CREATE TABLE wishlist
(
    WishlistID   INT (10) NOT NULL AUTO_INCREMENT,
    WishlistName VARCHAR(30) NOT NULL UNIQUE,
    UserID       INT,
    PRIMARY KEY (WishlistID),
    FOREIGN KEY (UserID) REFERENCES user (UserID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE wish
(
    WishID     INT (10) NOT NULL AUTO_INCREMENT,
    WishName   VARCHAR(30) NOT NULL,
    WishLink   VARCHAR(255),
    WishlistID INT,
    PRIMARY KEY (WishID),
    FOREIGN KEY (WishlistID) REFERENCES wishlist (WishlistID) ON DELETE CASCADE ON UPDATE CASCADE
);
