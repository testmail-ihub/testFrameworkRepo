#!/usr/bin/perl

use strict;
use warnings;
use Text::CSV;
use Tie::File;

require './etl_config.pl';

# Open raw data CSV for reading
open my $in_fh, '<', $RAW_DATA_CSV or die "Cannot open $RAW_DATA_CSV: $!";
my $csv_in = Text::CSV->new({ binary => 1, auto_diag => 1 });

# Read header
my @header = @{ $csv_in->getline($in_fh) };

# Process data, remove duplicates and trim spaces
my %seen;
my @cleaned_data;

while (my @row = @{ $csv_in->getline($in_fh) }) {
    # Trim spaces from each field
    s/^\s+|\s+$//g for @row;
    
    # Create a unique key for duplicate checking (e.g., join all fields)
    my $key = join('|', @row);
    unless ($seen{$key}) {
        push @cleaned_data, \@row;
        $seen{$key} = 1;
    }
}
close $in_fh;

# Open cleaned data CSV for writing
open my $out_fh, '>', $CLEANED_DATA_CSV or die "Cannot open $CLEANED_DATA_CSV: $!";
my $csv_out = Text::CSV->new({ binary => 1, auto_diag => 1 });

# Write header
$csv_out->print($out_fh, \@header);

# Write cleaned data
foreach my $row_ref (@cleaned_data) {
    $csv_out->print($out_fh, $row_ref);
}
close $out_fh;

print "Data transformed and saved to $CLEANED_DATA_CSV\n";
