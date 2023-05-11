-- テーブル削除
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISITS customers;
DROP TABLE IF EXISITS orders;
DROP TABLE IF EXISITS order_details;

-- カテゴリーテーブル
CREATE TABLE categories
(
   id SERIAL PRIMARY KEY,
   name TEXT
);
-- 商品テーブル
CREATE TABLE items
(
   id SERIAL PRIMARY KEY,
   category_id INTEGER,
   name TEXT,
   price INTEGER
);

-- 顧客テーブル --