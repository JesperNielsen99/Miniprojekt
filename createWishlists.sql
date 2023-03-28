DROP DATABASE IF EXISTS wishlists;
CREATE DATABASE wishlists;

USE wishlists;

CREATE TABLE user (
	UserID INT (10) NOT NULL AUTO_INCREMENT,  
    UserName VARCHAR (50) NOT NULL, 
    Email VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (UserID)
    );
    
CREATE TABLE wish (
	WishID INT (10) NOT NULL AUTO_INCREMENT,  
    WishName VARCHAR (30) NOT NULL, 
    WishLink VARCHAR (255), 
    PRIMARY KEY (WishID)
    );
    
CREATE TABLE wishlist (
WishlistID INT (10) NOT NULL AUTO_INCREMENT,
WishlistName VARCHAR (30) NOT NULL UNIQUE,
UserID INT,
PRIMARY KEY (WishlistID),
FOREIGN KEY (UserID)  REFERENCES user (UserID)
);

CREATE TABLE wishList_wish (
	WishID INT,
    WishlistID INT,
    FOREIGN KEY (WishID) REFERENCES wish (WishID),
    FOREIGN KEY (WishlistID)  REFERENCES wishlist (WishlistID),
    PRIMARY KEY (WishID, WishlistID)
    );
    

