# etl_config.pl
# Configuration file for the ETL pipeline

# Database connection details
$CONFIG{db_host} = 'localhost';
$CONFIG{db_name} = 'your_database_name';
$CONFIG{db_user} = 'your_username';
$CONFIG{db_pass} = 'your_password';

# File paths
$CONFIG{source_table} = 'your_source_table';
$CONFIG{output_csv} = 'extracted_data.csv';
$CONFIG{cleaned_csv} = 'cleaned_data.csv';
$CONFIG{log_file} = 'etl_pipeline.log';

1;