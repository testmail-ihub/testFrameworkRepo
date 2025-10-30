-- sql_scripts/load_standard_table.sql

INSERT INTO standard_data (id, column1, column2)
SELECT id, column1, column2
FROM staging_data
WHERE NOT EXISTS (
    SELECT 1 FROM bad_data WHERE bad_data.id = staging_data.id
);

-- Example: Create standard table if it doesn't exist
CREATE TABLE IF NOT EXISTS standard_data (
    id INT PRIMARY KEY,
    column1 VARCHAR(255),
    column2 VARCHAR(255),
    -- Add more columns as per your cleaned data structure
    load_timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
);
