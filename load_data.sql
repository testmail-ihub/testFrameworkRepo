LOAD DATA INFILE 'cleaned_data.csv'
INTO TABLE target_table  -- Replace target_table with your actual table name
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS; -- Ignore header row