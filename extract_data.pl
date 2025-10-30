#!/usr/bin/perl

use strict;
use warnings;
use DBI;
use Text::CSV;

require './etl_config.pl';

# Database connection
my $dsn = "DBI:mysql:database=$DB_NAME;host=$DB_HOST";
my $dbh = DBI->connect($dsn, $DB_USER, $DB_PASS, { RaiseError => 1, AutoCommit => 1 });

# Prepare CSV writer
my $csv = Text::CSV->new({ binary => 1, auto_diag => 1 });

# Open CSV file for writing
open my $fh, '>', $RAW_DATA_CSV or die "Cannot open $RAW_DATA_CSV: $!";

# Execute query and fetch data
my $sth = $dbh->prepare("SELECT * FROM source_table"); # Replace source_table with your actual table name
$sth->execute();

# Write header row
$csv->print($fh, [$sth->{NAME_lc}]);

# Write data rows
while (my @row = $sth->fetchrow_array) {
    $csv->print($fh, \@row);
}

close $fh;
$dbh->disconnect();

print "Data extracted to $RAW_DATA_CSV\n";
