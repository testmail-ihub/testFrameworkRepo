# etl_script.py

import pandas as pd
import sqlalchemy

def extract_csv(file_path):
    """Extracts data from a CSV file."""
    try:
        df = pd.read_csv(file_path)
        return df
    except Exception as e:
        print(f"Error extracting CSV: {e}")
        return None

def load_to_staging(df, db_connection_str, table_name):
    """Loads DataFrame into a staging table in the database."""
    try:
        engine = sqlalchemy.create_engine(db_connection_str)
        df.to_sql(table_name, engine, if_exists='replace', index=False)
        print(f"Successfully loaded data to staging table '{table_name}'.")
        return True
    except Exception as e:
        print(f"Error loading to staging table: {e}")
        return False

if __name__ == "__main__":
    # Configuration (replace with actual values or environment variables)
    CSV_FILE_PATH = "your_input.csv"  # Placeholder for your CSV file
    DB_CONNECTION_STRING = "sqlite:///etl_database.db"  # Example: SQLite database
    STAGING_TABLE_NAME = "staging_data"

    # 1. Extract raw CSV data
    raw_data_df = extract_csv(CSV_FILE_PATH)

    if raw_data_df is not None:
        # 2. Load raw CSV data into a staging table
        load_to_staging(raw_data_df, DB_CONNECTION_STRING, STAGING_TABLE_NAME)
