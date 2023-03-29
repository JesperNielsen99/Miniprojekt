USE wishlists;

INSERT INTO user (UserName, Email)
VALUES ('Hamza', 'jegvilhahjort@live.dk');

INSERT INTO wish (WishName, WishLink)
VALUES ('Bamse med lys og lyd', 'https://mycopenhagenkid.com/products/bamse-med-lys-og-lyd-fra-moonie-cappuccino?variant=43699884228822&currency=DKK&gclid=CjwKCAjw_YShBhAiEiwAMomsENtmL3iuo6mR6uRqMmfV7jjODmQcKrUn3eNHhoGq_KzPjlsz9bHf_BoCBNkQAvD_BwE');

INSERT INTO wishlist (WishlistName, UserID)
VALUES ('Hamzas juleliste', 1);

INSERT INTO wishlist_wish (WishID, WishlistID)
VALUES (1, 1);