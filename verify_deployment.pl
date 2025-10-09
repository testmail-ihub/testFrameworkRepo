#!/usr/bin/perl
use strict;
use warnings;
use DBI;
use LWP::UserAgent;

# Load environment variables from deploy_config.pl
require './deploy_config.pl';

# Connect to the database
my $dbh = DBI->connect("DBI:Pg:database=$DB_NAME;host=$DB_HOST;port=$DB_PORT", $DB_USER, $DB_PASSWORD)
    or die "Cannot connect to database: $DBI::errstr";

# Query the migration_version table to get the latest applied version
my $sth = $dbh->prepare("SELECT version FROM migration_version ORDER BY version DESC LIMIT 1");
$sth->execute();
my ($latest_version) = $sth->fetchrow_array;
$sth->finish;

# Check the status of the mock application service
my $ua = LWP::UserAgent->new;
my $response = $ua->get("http://localhost:8080/health");
my $service_status = $response->is_success ? "Running" : "Not Running";

# Output the database version and service status
print "Database Version: $latest_version\n";
print "Service Status: $service_status\n";

# Exit with a non-zero code if any check fails
if (!$response->is_success || !$latest_version) {
    exit 1;
}

$dbh->disconnect;
