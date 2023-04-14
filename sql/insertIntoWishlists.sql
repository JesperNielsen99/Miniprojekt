USE wishlists;

INSERT INTO user (UserName, Email, Password)
VALUES ('Hamza', 'mums@live.dk', '123');

INSERT INTO wishlist (WishlistName, UserID)
VALUES ('Hamzas juleliste', 1);

INSERT INTO wish (WishName, WishLink, WishlistID)
VALUES ('Bamse med lys og lyd', 'https://mycopenhagenkid.com/products/bamse-med-lys-og-lyd-fra-moonie-cappuccino?variant=43699884228822&currency=DKK&gclid=CjwKCAjw_YShBhAiEiwAMomsENtmL3iuo6mR6uRqMmfV7jjODmQcKrUn3eNHhoGq_KzPjlsz9bHf_BoCBNkQAvD_BwE', 1);
