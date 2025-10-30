-- load_data.sql
-- SQL script to load cleaned CSV data into a target database table.

-- Load configuration (assuming variables are set in the environment or by the calling script)
-- In a real scenario, these would be passed as parameters or read from a config file.
-- For this example, we'll use placeholders that `run_etl.sh` will replace.

-- Ensure the target table exists and has the correct schema.
-- This is a placeholder for your actual table creation/alteration SQL.
-- Example: CREATE TABLE IF NOT EXISTS target_table (
--    id INT PRIMARY KEY,
--    name VARCHAR(255),
--    value DECIMAL(10, 2)
-- );

-- Disable foreign key checks and unique checks for faster loading
SET FOREIGN_KEY_CHECKS = 0;
SET UNIQUE_CHECKS = 0;

-- Load data from the cleaned CSV file into the target table
LOAD DATA INFILE '@cleaned_csv_path@'
INTO TABLE @target_table_name@
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS; -- Ignore header row

-- Re-enable foreign key checks and unique checks
SET FOREIGN_KEY_CHECKS = 1;
SET UNIQUE_CHECKS = 1;

-- You might want to add error handling or logging here in a more robust system.
