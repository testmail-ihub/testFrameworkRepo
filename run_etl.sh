#!/bin/bash

# Run the extraction script
perl extract_data.pl
if [ $? -ne 0 ]; then
    echo "Error during data extraction. Exiting."
    exit 1
fi

# Run the transformation script
perl transform_data.pl
if [ $? -ne 0 ]; then
    echo "Error during data transformation. Exiting."
    exit 1
fi

# Run the SQL load script
mysql -u your_username -p'your_password' source_db < load_data.sql # Replace with your actual DB credentials and database name
if [ $? -ne 0 ]; then
    echo "Error during data loading. Exiting."
    exit 1
fi

echo "ETL process completed successfully."
