-- Drop Beneficiary table
DROP TABLE IF EXISTS Beneficiary CASCADE;

-- Drop User table
DROP TABLE IF EXISTS "Users" CASCADE;

-- Drop Account table
DROP TABLE IF EXISTS Account CASCADE;

CREATE TABLE Account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userId BIGINT NOT NULL,
    accountNumber VARCHAR(255) NOT NULL,
    accountType VARCHAR(255) NOT NULL,
    balance DOUBLE NOT NULL
);

-- Create User table
CREATE TABLE IF NOT EXISTS "Users" (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(20) NOT NULL,
    account_id INT,
    FOREIGN KEY (account_id) REFERENCES Account(id)
);

-- Create Beneficiary table
CREATE TABLE IF NOT EXISTS Beneficiary (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    account_number VARCHAR(255) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES "Users"(id)
);

CREATE TABLE IF NOT EXISTS Statements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    accountId BIGINT NOT NULL,
    statementDate DATE NOT NULL,
    statementDetails VARCHAR(255) NOT NULL,
    FOREIGN KEY (accountId) REFERENCES Account(id)
);

CREATE TABLE IF NOT EXISTS TransactionHistory (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    accountId BIGINT NOT NULL,
    transactionDate DATE NOT NULL,
    amount DOUBLE NOT NULL,
    transactionType VARCHAR(255) NOT NULL,
    FOREIGN KEY (accountId) REFERENCES Account(id)
);

-- Insert into Account table
INSERT INTO Account (userId, accountNumber, accountType, balance) VALUES
(1, '1234567890', 'Savings', 1000.00),
(2, '0987654321', 'Checking', 2000.00),
(3, '1122334455', 'Savings', 1500.00),
(4, '2233445566', 'Checking', 2500.00),
(5, '3344556677', 'Savings', 3000.00),
(6, '4455667788', 'Checking', 3500.00),
(7, '5566778899', 'Savings', 4000.00),
(8, '6677889900', 'Checking', 4500.00),
(9, '7788990011', 'Savings', 5000.00),
(10, '8899001122', 'Checking', 5500.00);

-- Insert into Users table
INSERT INTO "Users" (username, password, email, mobile_number, account_id) VALUES
('john_doe', 'password123', 'john@example.com', '1234567890', 1),
('jane_doe', 'password456', 'jane@example.com', '0987654321', 2),
('alice', 'password789', 'alice@example.com', '1111222233', 3),
('bob', 'password101', 'bob@example.com', '2222333344', 4),
('charlie', 'password102', 'charlie@example.com', '3333444455', 5),
('david', 'password103', 'david@example.com', '4444555566', 6),
('eve', 'password104', 'eve@example.com', '5555666677', 7),
('frank', 'password105', 'frank@example.com', '6666777788', 8),
('grace', 'password106', 'grace@example.com', '7777888899', 9),
('heidi', 'password107', 'heidi@example.com', '8888999900', 10);

-- Insert into Beneficiary table
INSERT INTO Beneficiary (name, account_number, user_id) VALUES
('Alice', '111122223333', 1),
('Bob', '444455556666', 2),
('Charlie', '777788889999', 3),
('David', '000011112222', 4),
('Eve', '333344445555', 5),
('Frank', '666677778888', 6),
('Grace', '999900001111', 7),
('Heidi', '222233334444', 8),
('Ivan', '555566667777', 9),
('Judy', '888899990000', 10);

-- Insert into Statements table
INSERT INTO Statements (accountId, statementDate, statementDetails) VALUES
(1, '2023-01-01', 'Statement details 1'),
(2, '2023-01-01', 'Statement details 2'),
(3, '2023-01-01', 'Statement details 3'),
(4, '2023-01-01', 'Statement details 4'),
(5, '2023-01-01', 'Statement details 5'),
(6, '2023-01-01', 'Statement details 6'),
(7, '2023-01-01', 'Statement details 7'),
(8, '2023-01-01', 'Statement details 8'),
(9, '2023-01-01', 'Statement details 9'),
(10, '2023-01-01', 'Statement details 10');

-- Insert into TransactionHistory table
INSERT INTO TransactionHistory (accountId, transactionDate, amount, transactionType) VALUES
(1, '2023-01-02', 100.00, 'Deposit'),
(2, '2023-01-02', 200.00, 'Withdrawal'),
(3, '2023-01-02', 150.00, 'Deposit'),
(4, '2023-01-02', 250.00, 'Withdrawal'),
(5, '2023-01-02', 300.00, 'Deposit'),
(6, '2023-01-02', 350.00, 'Withdrawal'),
(7, '2023-01-02', 400.00, 'Deposit'),
(8, '2023-01-02', 450.00, 'Withdrawal'),
(9, '2023-01-02', 500.00, 'Deposit'),
(10, '2023-01-02', 550.00, 'Withdrawal');
