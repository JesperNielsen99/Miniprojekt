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
    
CREATE TABLE user_wish (
	WishID INT,
    UserID INT,
    FOREIGN KEY (WishID) REFERENCES wish (WishID),
    FOREIGN KEY (UserID)  REFERENCES user (UserID),
    PRIMARY KEY (WishID, UserID)
    );
    
INSERT INTO user (UserName, Email)
VALUES ('Hamza', 'jegvilhahjort@live.dk');

INSERT INTO wish (WishName, WishLink)
VALUES ('Bamse med lys og lyd', 'https://mycopenhagenkid.com/products/bamse-med-lys-og-lyd-fra-moonie-cappuccino?variant=43699884228822&currency=DKK&gclid=CjwKCAjw_YShBhAiEiwAMomsENtmL3iuo6mR6uRqMmfV7jjODmQcKrUn3eNHhoGq_KzPjlsz9bHf_BoCBNkQAvD_BwE');

INSERT INTO user_wish (WishID, UserID)
VALUES (1, 1);
