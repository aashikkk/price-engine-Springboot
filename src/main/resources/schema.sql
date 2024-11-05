CREATE TABLE IF NOT EXISTS product (
   id LONG PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   units_per_carton INT NOT NULL,
   carton_price DECIMAL(10, 2) NOT NULL
);


