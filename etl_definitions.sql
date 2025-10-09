CREATE TABLE staging_table (
    id INT,
    name NVARCHAR(255),
    email NVARCHAR(255),
    phone NVARCHAR(20),
    address NVARCHAR(255),
    city NVARCHAR(100),
    state NVARCHAR(100),
    zip_code NVARCHAR(20),
    country NVARCHAR(100),
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE standard_table (
    id INT PRIMARY KEY,
    name NVARCHAR(255),
    email NVARCHAR(255) UNIQUE,
    phone NVARCHAR(20),
    address NVARCHAR(255),
    city NVARCHAR(100),
    state NVARCHAR(100),
    zip_code NVARCHAR(20),
    country NVARCHAR(100),
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE bad_table (
    id INT,
    error_message NVARCHAR(255),
    error_timestamp DATETIME
);

WITH cte AS (
    SELECT *,
        ROW_NUMBER() OVER (PARTITION BY id ORDER BY created_at DESC) AS rn
    FROM staging_table
)
INSERT INTO standard_table (id, name, email, phone, address, city, state, zip_code, country, created_at, updated_at)
SELECT id, name, email, phone, address, city, state, zip_code, country, created_at, updated_at
FROM cte
WHERE rn = 1;

INSERT INTO #duplicates
SELECT *
FROM cte
WHERE rn > 1;

DELETE FROM staging_table
WHERE id IN (SELECT id FROM #duplicates);

INSERT INTO bad_table (id, error_message, error_timestamp)
SELECT id, 'Duplicate record', GETDATE()
FROM #duplicates;

CREATE INDEX idx_staging_id ON staging_table (id);
CREATE INDEX idx_staging_email ON staging_table (email);
CREATE INDEX idx_standard_id ON standard_table (id);
CREATE INDEX idx_standard_email ON standard_table (email);