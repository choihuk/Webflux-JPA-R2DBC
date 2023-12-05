CREATE TABLE users
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL
);

CREATE TABLE coupon
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(255) NOT NULL UNIQUE,
    type VARCHAR(255) NOT NULL,
    discount DOUBLE NOT NULL,
    amount BIGINT NOT NULL,
    created_at DATETIME NOT NULL
);

CREATE TABLE coupon_wallet
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    coupon_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL,
    used_at DATETIME,
    FOREIGN KEY (coupon_id) REFERENCES coupon(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

DROP TABLE IF EXISTS coupon_wallet;
DROP TABLE IF EXISTS coupon;