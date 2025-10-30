-- load_data.sql
-- SQL script to load cleaned CSV data into a target database table.

-- This script assumes the target table structure is already defined
-- and matches the columns in the cleaned CSV file.

-- Placeholder for database name and table name, to be replaced by etl_config.pl values
-- USE <db_name>;
-- LOAD DATA INFILE '<output_cleaned_csv_file>'
-- INTO TABLE <target_table>
-- FIELDS TERMINATED BY ',' ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS; -- Skip header row

-- Example of how the final LOAD DATA INFILE statement might look:
-- LOAD DATA INFILE '/path/to/your/cleaned_data.csv'
-- INTO TABLE your_target_table
-- FIELDS TERMINATED BY ',' ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS;
