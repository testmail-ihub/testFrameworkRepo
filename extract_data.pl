#!/usr/bin/perl

use strict;
use warnings;
use DBI;
use Text::CSV;
use File::Basename;

# Load configuration
my $config_file = dirname(__FILE__) . '/etl_config.pl';
die "Configuration file not found: $config_file" unless -e $config_file;
do $config_file;

# Database connection details from config
my $db_host = $CONFIG{db_host};
my $db_name = $CONFIG{db_name};
my $db_user = $CONFIG{db_user};
my $db_pass = $CONFIG{db_pass};
my $source_table = $CONFIG{source_table};
my $output_csv = $CONFIG{output_csv};

# Connect to the database
my $dsn = "DBI:mysql:database=$db_name;host=$db_host";
my $dbh = DBI->connect($dsn, $db_user, $db_pass, { RaiseError => 1, AutoCommit => 1 })
    or die $DBI::errstr;

print "Connected to database $db_name on $db_host\n";

# Prepare SQL query
my $sql = "SELECT * FROM $source_table";
my $sth = $dbh->prepare($sql)
    or die $dbh->errstr;

# Execute query
$sth->execute()
    or die $sth->errstr;

print "Executing query: $sql\n";

# Open CSV file for writing
my $csv = Text::CSV->new({ binary => 1, auto_diag => 1 })
    or die "Cannot use Text::CSV: " . Text::CSV->error_diag();

open my $fh, ">:encoding(utf8)", $output_csv
    or die "Cannot open $output_csv: $!";

# Write CSV header
my @column_names = @{ $sth->{NAME} };
$csv->print($fh, \@column_names);
$csv->print($fh, ["\n"]); # Add newline after header

# Fetch and write data rows
while (my $row = $sth->fetchrow_arrayref()) {
    $csv->print($fh, $row);
    $csv->print($fh, ["\n"]); # Add newline after each row
}

$sth->finish();
$dbh->disconnect();
close $fh;

print "Data extracted from $source_table to $output_csv successfully.\n";

1;