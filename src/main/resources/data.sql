INSERT INTO product (id, name, units_per_carton, carton_price) VALUES
   (1, 'Penguin-ears', 20, 175),
   (2, 'Horseshoe', 5, 825)
    ON DUPLICATE KEY UPDATE name =name;