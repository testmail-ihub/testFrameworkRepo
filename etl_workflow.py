import pandas as pd
import sqlalchemy
from azure.identity import DefaultAzureCredential
from azure.mgmt.datafactory import DataFactoryManagementClient

# Database connection setup
DATABASE_URI = 'your_database_connection_string'
engine = sqlalchemy.create_engine(DATABASE_URI)

# Load CSV data into staging table
def load_csv_to_staging(csv_path, staging_table):
    df = pd.read_csv(csv_path)
    df.to_sql(staging_table, engine, if_exists='replace', index=False)
    print(f"Loaded CSV data into {staging_table}")

# Clean data using SQL (remove duplicates, separate errors)
def clean_data(staging_table, cleaned_table, bad_table):
    with engine.connect() as conn:
        # Remove duplicates and insert into cleaned_table
        conn.execute(f"""
            INSERT INTO {cleaned_table}
            SELECT DISTINCT * FROM {staging_table}
        """)
        # Find error records (example: NULLs in required columns)
        error_df = pd.read_sql(f"SELECT * FROM {staging_table} WHERE column1 IS NULL OR column2 IS NULL", conn)
        if not error_df.empty:
            error_df.to_sql(bad_table, engine, if_exists='replace', index=False)
            print(f"Error records pushed to {bad_table}")
    print(f"Cleaned data loaded into {cleaned_table}")

# Push final data to Azure Data Factory (ADF)
def push_to_adf(resource_group, factory_name, pipeline_name, parameters):
    credential = DefaultAzureCredential()
    adf_client = DataFactoryManagementClient(credential, 'your_subscription_id')
    run_response = adf_client.pipelines.create_run(
        resource_group_name=resource_group,
        factory_name=factory_name,
        pipeline_name=pipeline_name,
        parameters=parameters
    )
    print(f"ADF pipeline run ID: {run_response.run_id}")

if __name__ == "__main__":
    csv_path = 'your_csv_file.csv'
    staging_table = 'staging_table'
    cleaned_table = 'standard_table'
    bad_table = 'bad_table'
    load_csv_to_staging(csv_path, staging_table)
    clean_data(staging_table, cleaned_table, bad_table)
    # Example ADF push
    push_to_adf('your_resource_group', 'your_factory_name', 'your_pipeline_name', {'inputTable': cleaned_table})
