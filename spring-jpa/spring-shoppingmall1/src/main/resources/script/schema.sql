CREATE TABLE IF NOT EXISTS carts (
    cart_id INT NOT NULL,
    user_id VARCHAR(50) NOT NULL,
    product_id INT NOT NULL,
    amount INT,

    PRIMARY KEY (cart_id, user_id, product_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
    );

CREATE TABLE IF NOT EXISTS users (
    user_id VARCHAR(50) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    user_password VARCHAR(200) NOT NULL,
    user_birth VARCHAR(8) NOT NULL,
    user_auth VARCHAR(10) NOT NULL,
    user_point INT NOT NULL DEFAULT 1000000,
    created_at TIMESTAMP NOT NULL,
    latest_login_at TIMESTAMP DEFAULT NULL,

    PRIMARY KEY (user_id)
    );

CREATE TABLE IF NOT EXISTS user_address (
    address_id INT NOT NULL,
    user_id VARCHAR(50) NOT NULL,
    address_info TEXT,

    PRIMARY KEY (address_id, user_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
    );

CREATE TABLE IF NOT EXISTS products (
    product_id INT NOT NULL,
    product_name VARCHAR(255),
    price DECIMAL(10,2),
    stock BIGINT,
    product_image TEXT,
    product_info VARCHAR(30),
    register_date DATE,
    latest_update_at DATE,

    PRIMARY KEY (product_id)
    );

CREATE TABLE IF NOT EXISTS product_categories (
    product_id INT NOT NULL,
    category_id INT NOT NULL,

    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id),
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
    );

CREATE TABLE IF NOT EXISTS categories (
    category_id INT NOT NULL,
    category_name VARCHAR(20),

    PRIMARY KEY (category_id)
    );

CREATE TABLE IF NOT EXISTS orders (
    order_id BIGINT NOT NULL AUTO
