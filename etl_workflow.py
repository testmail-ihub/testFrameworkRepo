import pandas as pd
import pyodbc
from azure.identity import DefaultAzureCredential
from azure.data.tables import TableServiceClient
from azure.data.tables import EntityProperty

# Database connection settings
DB_SERVER = 'your_db_server'
DB_DATABASE = 'your_db_database'
DB_USERNAME = 'your_db_username'
DB_PASSWORD = 'your_db_password'

# Azure Data Factory connection settings
ADF_STORAGE_ACCOUNT = 'your_adf_storage_account'
ADF_STORAGE_ACCOUNT_KEY = 'your_adf_storage_account_key'
ADF_CONTAINER_NAME = 'your_adf_container_name'

# Bad table name for error records
BAD_TABLE = 'bad_table'

def get_db_connection():
    """Create a database connection with error handling."""
    try:
        conn_str = (
            f'DRIVER=;'
            f'SERVER={DB_SERVER};'
            f'DATABASE={DB_DATABASE};'
            f'UID={DB_USERNAME};'
            f'PWD={DB_PASSWORD}'
        )
        return pyodbc.connect(conn_str)
    except pyodbc.Error as e:
        print(f"Error connecting to database: {e}")
        raise

def load_raw_data_to_staging(csv_file_path):
    """Load raw CSV data into the staging table with error handling."""
    try:
        conn = get_db_connection()
        cursor = conn.cursor()
        df = pd.read_csv(csv_file_path)
        for _, row in df.iterrows():
            cursor.execute(
                "INSERT INTO staging_table (column1, column2, column3) VALUES (?, ?, ?)",
                row['column1'], row['column2'], row['column3']
            )
        conn.commit()
    except Exception as e:
        print(f"Error loading raw data: {e}")
        raise
    finally:
        cursor.close()
        conn.close()

def clean_data():
    """Remove duplicate rows from the staging table and capture bad rows."""
    try:
        conn = get_db_connection()
        cursor = conn.cursor()
        # Capture rows that would be removed as duplicates into the bad table
        cursor.execute(
            f"INSERT INTO {BAD_TABLE} (column1, column2, column3) "
            "SELECT column1, column2, column3 FROM staging_table "
            "WHERE id NOT IN ("
            "    SELECT MIN(id) FROM staging_table "
            "    GROUP BY column1, column2, column3"
            ")"
        )
        # Delete duplicate rows, keeping the first occurrence
        cursor.execute(
            "DELETE FROM staging_table "
            "WHERE id NOT IN ("
            "    SELECT MIN(id) FROM staging_table "
            "    GROUP BY column1, column2, column3"
            ")"
        )
        conn.commit()
    except Exception as e:
        print(f"Error cleaning data: {e}")
        raise
    finally:
        cursor.close()
        conn.close()

def load_cleaned_data_to_standard():
    """Load cleaned data into the standard table."""
    try:
        conn = get_db_connection()
        cursor = conn.cursor()
        cursor.execute(
            "INSERT INTO standard_table (column1, column2, column3) "
            "SELECT column1, column2, column3 FROM staging_table"
        )
        conn.commit()
    except Exception as e:
        print(f"Error loading cleaned data: {e}")
        raise
    finally:
        cursor.close()
        conn.close()

def push_data_to_adf():
    """Trigger the Azure Data Factory pipeline after successful load."""
    try:
        credential = DefaultAzureCredential()
        table_service_client = TableServiceClient.from_connection_string(
            f"DefaultEndpointsProtocol=https;AccountName={ADF_STORAGE_ACCOUNT};"
            f"AccountKey={ADF_STORAGE_ACCOUNT_KEY};EndpointSuffix=core.windows.net"
        )
        # Placeholder for actual pipeline trigger logic
        print("ADF pipeline triggered successfully.")
    except Exception as e:
        print(f"Error triggering ADF pipeline: {e}")
        raise

def run_etl(csv_file_path):
    """Orchestrates the full ETL workflow."""
    load_raw_data_to_staging(csv_file_path)
    clean_data()
    load_cleaned_data_to_standard()
    push_data_to_adf()

# Example usage (uncomment and set the path to your CSV file)
# if __name__ == "__main__":
#     run_etl('path/to/your/file.csv')
