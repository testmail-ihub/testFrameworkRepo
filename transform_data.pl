#!/usr/bin/perl

use strict;
use warnings;
use Text::CSV;
use File::Basename;
use Cwd 'abs_path';

# Determine the script's directory
my $script_dir = dirname(abs_path($0));

# Load configuration from etl_config.pl
my $config_path = "$script_dir/etl_config.pl";
die "Error: Configuration file not found at $config_path\n" unless -e $config_path;
do $config_path;

my $input_csv_file = $CONFIG{output_csv_file}; # Output of extract_data.pl
my $output_cleaned_csv_file = $CONFIG{output_cleaned_csv_file};

# Open input CSV file
open my $in_fh, "<:encoding(utf8)", $input_csv_file or die "Cannot open $input_csv_file: $!";

# Create a new CSV parser
my $csv = Text::CSV->new({ binary => 1, auto_diag => 1, eol => "\n" })
    or die "Cannot use Text::CSV: ".Text::CSV->error_diag();

# Read header
my @header = @{ $csv->getline($in_fh) };

# Prepare for duplicate checking and trimming
my %seen;
my @cleaned_rows;

# Add header to cleaned rows
push @cleaned_rows, \@header;

# Process data rows
while (my @row = @{ $csv->getline($in_fh) }) {
    # Trim spaces from each field
    my @trimmed_row = map { s/^\s+|\s+$//gr } @row;

    # Create a unique key for duplicate checking
    my $row_key = join("|", @trimmed_row);

    # Add row if not a duplicate
    unless ($seen{$row_key}) {
        push @cleaned_rows, \@trimmed_row;
        $seen{$row_key} = 1;
    }
}

close $in_fh;

# Open output CSV file for writing
open my $out_fh, ">:encoding(utf8)", $output_cleaned_csv_file or die "Cannot open $output_cleaned_csv_file: $!";

# Write cleaned data to output CSV
foreach my $row_ref (@cleaned_rows) {
    $csv->print($out_fh, $row_ref);
}

close $out_fh;

print "Data transformed (cleaned and normalized) from $input_csv_file to $output_cleaned_csv_file successfully.\n";

exit 0;
