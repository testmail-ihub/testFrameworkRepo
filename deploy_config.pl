#!/usr/bin/perl
use strict;
use warnings;

# Define environment variables and credentials needed for deployment
my %config = (
    'DB_HOST' => 'db.example.com',
    'DB_PORT' => 5432,
    'DB_USER' => 'deploy_user',
    'DB_PASSWORD' => 'your_secure_password',
    'APP_ENV' => 'DEV',
    'APP_NAME' => 'my_app',
    'LOG_LEVEL' => 'DEBUG',
);

# Export environment variables for use by other scripts
foreach my $key (keys %config) {
    $ENV{$key} = $config{$key};
    print "export $key=$config{$key}\n";
}

1;