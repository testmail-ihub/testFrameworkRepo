-- sql_scripts/create_staging_table.sql

CREATE TABLE IF NOT EXISTS staging_data (
    id INT PRIMARY KEY,
    column1 VARCHAR(255),
    column2 VARCHAR(255),
    -- Add more columns as per your CSV structure
    load_timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
);
