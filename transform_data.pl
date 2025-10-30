#!/usr/bin/perl

use strict;
use warnings;
use Text::CSV;
use File::Basename;
use List::Util qw(uniq);

# Load configuration
my $config_file = dirname(__FILE__) . '/etl_config.pl';
die "Configuration file not found: $config_file" unless -e $config_file;
do $config_file;

# Configuration details from config
my $input_csv = $CONFIG{output_csv}; # Output of extract_data.pl is input here
my $cleaned_csv = $CONFIG{cleaned_csv};

# Open input CSV file for reading
my $csv_in = Text::CSV->new({ binary => 1, auto_diag => 1 })
    or die "Cannot use Text::CSV: " . Text::CSV->error_diag();

open my $fh_in, "<:encoding(utf8)", $input_csv
    or die "Cannot open $input_csv: $!";

# Open output CSV file for writing
my $csv_out = Text::CSV->new({ binary => 1, auto_diag => 1 })
    or die "Cannot use Text::CSV: " . Text::CSV->error_diag();

open my $fh_out, ">:encoding(utf8)", $cleaned_csv
    or die "Cannot open $cleaned_csv: $!";

my @header;
my %seen_rows;

# Read header
if (my $row = $csv_in->getline($fh_in)) {
    @header = @$row;
    $csv_out->print($fh_out, \@header);
    $csv_out->print($fh_out, ["\n"]);
}

# Process data rows
while (my $row = $csv_in->getline($fh_in)) {
    # Trim spaces from each field
    my @cleaned_row = map { s/^\s+|\s+$//gr } @$row;

    # Convert row to a string for duplicate checking
    my $row_string = join("|", @cleaned_row);

    # Remove duplicates
    unless ($seen_rows{$row_string}) {
        $csv_out->print($fh_out, \@cleaned_row);
        $csv_out->print($fh_out, ["\n"]);
        $seen_rows{$row_string} = 1;
    }
}

$csv_in->eof or $csv_in->error_diag();

close $fh_in;
close $fh_out;

print "Data transformed from $input_csv to $cleaned_csv successfully (duplicates removed, spaces trimmed).\n";

1;