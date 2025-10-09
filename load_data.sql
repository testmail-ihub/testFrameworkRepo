LOAD DATA INFILE '$ETL_CONFIG->{load_input_file}'
INTO TABLE $ETL_CONFIG->{target_table}
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@ETL_CONFIG->{target_table_columns}@)
SET id = NULL;

-- Note: Replace @ETL_CONFIG->{target_table_columns}@ with the actual column names in the target table.
-- For example: (@ETL_CONFIG->{target_table_columns}@) might be (column1, column2, column3)
-- Also, ensure that the column order in the CSV file matches the order specified in the SET statement.