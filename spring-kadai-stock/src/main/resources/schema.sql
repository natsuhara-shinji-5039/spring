-- 各種テーブル削除
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_details;
DROP TABLE IF EXISTS stocks;
DROP TABLE IF EXISTS products;

-- 商品テーブル
CREATE TABLE products
(
   id SERIAL PRIMARY KEY,
   name TEXT,
   price INTEGER
);
-- 在庫テーブル
CREATE TABLE stocks
(
   id SERIAL PRIMARY KEY,
   product_id INTEGER,
   create_time TIMESTAMP not null,
   issue INTEGER,
   storing INTEGER
);
