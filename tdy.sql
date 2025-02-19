create database SDDD;
use SDDD;


Select * from consumer_port;
Select * from seller_port;


-- Create Consumer Table
CREATE TABLE Consumer_port (
    c_port_id INT PRIMARY KEY NOT NULL,
    cpassword VARCHAR(64) NOT NULL,  -- Store SHA-256 hashed passwords
    clocation VARCHAR(255),
    crole VARCHAR(50)
);

-- Create Seller Table
CREATE TABLE Seller_port (
    s_port_id INT PRIMARY KEY NOT NULL,
    spassword VARCHAR(64) NOT NULL,  -- Store SHA-256 hashed passwords
    slocation VARCHAR(255),
    srole VARCHAR(50)
);

-- View table structures
DESC Consumer_port;
DESC Seller_port;

-- Drop existing procedure before recreating it
DROP PROCEDURE IF EXISTS register_user;

DELIMITER &&
CREATE PROCEDURE register_user(
    IN uport_id INT,
    IN upassword VARCHAR(64),
    IN ucnfpassword VARCHAR(64),
    IN ulocation VARCHAR(50),
    IN urole VARCHAR(50)
)
BEGIN
    -- Check if passwords match
    IF upassword = ucnfpassword THEN

        -- Register as Consumer
        IF urole = 'consumer' THEN
            INSERT INTO Consumer_port (c_port_id, cpassword, clocation, crole) 
            VALUES (uport_id, sha2(upassword, 256), ulocation, urole);
            SELECT 'Consumer registered successfully' AS message;

        -- Register as Seller
        ELSEIF urole = 'seller' THEN
            INSERT INTO Seller_port (s_port_id, spassword, slocation, srole) 
            VALUES (uport_id, sha2(upassword, 256), ulocation, urole);
            SELECT 'Seller registered successfully' AS message;

        ELSE
            -- Handle invalid role input
            SELECT 'Invalid role! Please enter either "consumer" or "seller"' AS message;
        END IF;

    ELSE
        -- Handle password mismatch
        SELECT 'Password and confirm password do not match' AS message;
    END IF;
END &&
DELIMITER ;

-- Test the register_user procedure
-- CALL register_user(101, 'mypassword', 'mypassword', 'Mumbai', 'consumer');
-- CALL register_user(201, 'sellerpass', 'sellerpass', 'Delhi', 'seller');

-- View inserted data
-- SELECT * FROM Consumer_port;
-- SELECT * FROM Seller_port;

-- Drop existing login procedure before recreating it
DROP PROCEDURE IF EXISTS login_user;

DELIMITER &&
CREATE PROCEDURE login_user(
    IN uport_id INT,
    IN upassword VARCHAR(64),
    IN urole VARCHAR(50)
)
BEGIN
    DECLARE user_exists INT;

    -- Login as Consumer
    IF urole = 'consumer' THEN
        SELECT COUNT(*) INTO user_exists 
        FROM Consumer_port 
        WHERE c_port_id = uport_id AND cpassword = sha2(upassword, 256);
        
        IF user_exists > 0 THEN
            SELECT 'Login successful! Redirecting to Consumer Dashboard' AS message;
        ELSE
            SELECT 'Invalid Consumer credentials! Please check your port ID, password, or role.' AS message;
        END IF;

    -- Login as Seller
    ELSEIF urole = 'seller' THEN
        SELECT COUNT(*) INTO user_exists 
        FROM Seller_port 
        WHERE s_port_id = uport_id AND spassword = sha2(upassword, 256);
        
        IF user_exists > 0 THEN
            SELECT 'Login successful! Redirecting to Seller Dashboard' AS message;
        ELSE
            SELECT 'Invalid Seller credentials! Please check your port ID, password, or role.' AS message;
        END IF;

    -- Handle invalid role input
    ELSE
        SELECT 'Invalid role! Please enter either "consumer" or "seller"' AS message;
    END IF;
END &&
DELIMITER ;

-- Test the login_user procedure
-- CALL login_user(101, 'mypassword', 'consumer');
-- CALL login_user(201, 'sellerpass', 'seller');

