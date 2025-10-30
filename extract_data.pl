#!/usr/bin/perl

use strict;
use warnings;
use DBI;
use Text::CSV;
use File::Basename;
use Cwd 'abs_path';

# Determine the script's directory
my $script_dir = dirname(abs_path($0));

# Load configuration from etl_config.pl
my $config_path = "$script_dir/etl_config.pl";
die "Error: Configuration file not found at $config_path\n" unless -e $config_path;
do $config_path;

# Database connection parameters from config
my $db_host = $CONFIG{db_host};
my $db_name = $CONFIG{db_name};
my $db_user = $CONFIG{db_user};
my $db_pass = $CONFIG{db_pass};
my $db_port = $CONFIG{db_port};
my $source_table = $CONFIG{source_table};
my $output_csv_file = $CONFIG{output_csv_file};

# Construct DSN
my $dsn = "DBI:mysql:database=$db_name;host=$db_host;port=$db_port";

# Connect to the database
my $dbh = DBI->connect($dsn, $db_user, $db_pass, { RaiseError => 1, AutoCommit => 1 })
    or die $DBI::errstr;

print "Successfully connected to database: $db_name\n";

# Prepare SQL query to extract data
my $sql = "SELECT * FROM $source_table";
my $sth = $dbh->prepare($sql);
$sth->execute();

# Open CSV file for writing
my $csv = Text::CSV->new({ binary => 1, auto_diag => 1, eol => "\n" })
    or die "Cannot use Text::CSV: ".Text::CSV->error_diag();

open my $fh, ">:encoding(utf8)", $output_csv_file or die "Cannot open $output_csv_file: $!";

# Write CSV header
my @column_names = @{ $sth->{NAME} };
$csv->print($fh, \@column_names);

# Write data rows
while (my @row = $sth->fetchrow_array()) {
    $csv->print($fh, \@row);
}

$sth->finish();
$dbh->disconnect();
close $fh;

print "Data extracted from $source_table to $output_csv_file successfully.\n";

exit 0;
