-- sql_scripts/clean_data.sql

-- Remove duplicate records
DELETE FROM staging_data
WHERE id IN (
    SELECT id
    FROM (
        SELECT id, ROW_NUMBER() OVER (PARTITION BY column1, column2 ORDER BY id) as rn
        FROM staging_data
    ) t
    WHERE t.rn > 1
);

-- Example: Handle null values (replace with actual cleaning logic)
UPDATE staging_data
SET column1 = 'N/A'
WHERE column1 IS NULL;

-- Example: Data type conversions and formatting (adjust as needed)
-- ALTER TABLE staging_data ALTER COLUMN column_name TYPE NEW_TYPE;
