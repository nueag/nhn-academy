CREATE TABLE IF NOT EXISTS `carts` (
                                       `cart_id` INT NOT NULL,
                                       `user_id` VARCHAR(50) NOT NULL,
                                       `product_id` INT NOT NULL,
                                       `amount` INT,

                                       PRIMARY KEY (`cart_id`)
);

CREATE TABLE IF NOT EXISTS `users` (
                                       `user_id` VARCHAR(50) NOT NULL,
                                       `user_name` VARCHAR(50) NOT NULL,
                                       `user_password` VARCHAR(200) NOT NULL,
                                       `user_birth` VARCHAR(8) NOT NULL,
                                       `user_auth` VARCHAR(10) NOT NULL,
                                       `user_point` INT NOT NULL,
                                       `created_at` DATETIME NOT NULL,
                                       `latest_login_at` DATETIME DEFAULT NULL,

                                       PRIMARY KEY (`user_id`)
);

CREATE TABLE IF NOT EXISTS `user_address` (
                                              `address_id` INT NOT NULL,
                                              `address_info` TEXT,
                                              `user_id` VARCHAR(50) NOT NULL,

                                              PRIMARY KEY (`address_id`)
);

CREATE TABLE IF NOT EXISTS `products` (
                                          `product_id` INT NOT NULL,
                                          `product_name` VARCHAR(255),
                                          `price` DECIMAL(10,2),
                                          `stock` BIGINT,
                                          `product_image` TEXT,
                                          `product_info` VARCHAR(30),
                                          `register_date` DATE,
                                          `latest_update_at` DATE,

                                          PRIMARY KEY (`product_id`)
);

CREATE TABLE IF NOT EXISTS `categories` (
                                            `category_id` INT NOT NULL,
                                            `category_name` VARCHAR(20),

                                            PRIMARY KEY (`category_id`)
);

CREATE TABLE IF NOT EXISTS `product_categories` (
                                                    `product_id` INT NOT NULL,
                                                    `category_id` INT NOT NULL,
                                                    PRIMARY KEY (`product_id`, `category_id`)
);

CREATE TABLE IF NOT EXISTS `orders` (
                                        `order_id` INT NOT NULL,
                                        `order_date` DATE,
                                        `user_id` VARCHAR(50) NOT NULL,

                                        PRIMARY KEY (`order_id`)
);
MERGE INTO `users` KEY (  `user_id` ) VALUES ( 'user','유저1','1234','19990101','유저',100000,'2023-01-01',null);

merge into `carts` key (`cart_id`) values (1,'user', 1, 3);
-- 카테고리
merge into `categories` key (`category_id`) values ( 1, '뷰티' );
merge into `categories` key (`category_id`) values ( 2, '문구/오피스' );
merge into `categories` key (`category_id`) values ( 3, '식품' );
merge into `categories` key (`category_id`) values ( 4, '크리스마스' );

-- 상품
merge into `products` key (`product_id`) values ( 1, 'name1', 10, 10, null, null, null, null );
merge into `products` key (`product_id`) values ( 2, 'name2', 10, 10, null, null, null, null );
merge into `products` key (`product_id`) values ( 3, 'name3', 10, 10, null, null, null, null );
merge into `products` key (`product_id`) values ( 4, 'name4', 10, 10, null, null, null, null );

-- 상품 카테고리
merge into `product_categories` key (`product_id`, `category_id`) values ( 1, 1 );
merge into `product_categories` key (`product_id`, `category_id`) values ( 2, 3 );
merge into `product_categories` key (`product_id`, `category_id`) values ( 3, 1 );
merge into `product_categories` key (`product_id`, `category_id`) values ( 4, 2 );

-- 주문
merge into `orders` key (`order_id`) values ( 1, '2023-10-23', 'user' );

-- 사용자 주소
merge into `user_address` key (`address_id`) values ( 1, null, 'user' );
