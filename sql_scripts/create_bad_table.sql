-- sql_scripts/create_bad_table.sql

CREATE TABLE IF NOT EXISTS bad_data (
    id INT PRIMARY KEY,
    column1 VARCHAR(255),
    column2 VARCHAR(255),
    -- Add more columns as per your CSV structure
    error_message VARCHAR(255),
    load_timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
);
