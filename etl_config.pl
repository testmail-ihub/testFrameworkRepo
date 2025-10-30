# etl_config.pl
# Configuration file for the ETL pipeline

# Database credentials and connection details
$CONFIG{db_host} = 'localhost';
$CONFIG{db_name} = 'your_database_name';
$CONFIG{db_user} = 'your_username';
$CONFIG{db_pass} = 'your_password';
$CONFIG{db_port} = 3306; # Default MySQL port

# Table names
$CONFIG{source_table} = 'source_data_table';
$CONFIG{target_table} = 'target_data_table';

# File paths
$CONFIG{output_csv_file} = 'raw_data.csv';
$CONFIG{output_cleaned_csv_file} = 'cleaned_data.csv';

1; # Required for Perl modules
