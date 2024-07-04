-- Table to store payment information
CREATE TABLE payments
(
    payment_id     INT AUTO_INCREMENT PRIMARY KEY,
    user_id        INT                                 NOT NULL,
    payment_type   VARCHAR(50)                         NOT NULL, -- Distinguish payment types
    amount         DECIMAL(10, 2)                      NOT NULL,
    payment_method VARCHAR(50)                         NOT NULL,
    payment_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- Table to store enrollment information
CREATE TABLE enrollments
(
    enrollment_id     INT AUTO_INCREMENT PRIMARY KEY,
    user_id           INT       NOT NULL,
    course_id         INT       NOT NULL,
    payment_id        INT       NOT NULL,
    registration_date TIMESTAMP NOT NULL,
    FOREIGN KEY (payment_id) REFERENCES payments (payment_id)
);

-- Table to store user subscription information
CREATE TABLE subscriptions
(
    subscription_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id         INT       NOT NULL,
    payment_id      INT       NOT NULL,
    start_date      TIMESTAMP NOT NULL,
    end_date        TIMESTAMP NOT NULL,
    FOREIGN KEY (payment_id) REFERENCES payments (payment_id)
);
