-- sql_scripts/insert_bad_records.sql

INSERT INTO bad_data (id, column1, column2, error_message)
SELECT id, column1, column2, 'Validation Error' as error_message
FROM staging_data
WHERE NOT (column1 IS NOT NULL AND column2 IS NOT NULL); -- Example: condition for bad records
