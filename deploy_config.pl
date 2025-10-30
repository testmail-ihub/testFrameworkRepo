use strict;
use warnings;

package DeployConfig;

our $VERSION = '1.0';

# Database connection details
our $DB_HOST = $ENV{DB_HOST} || 'localhost';
our $DB_PORT = $ENV{DB_PORT} || '3306';
our $DB_NAME = $ENV{DB_NAME} || 'mydatabase';
our $DB_USER = $ENV{DB_USER} || 'dbuser';
our $DB_PASS = $ENV{DB_PASS} || 'dbpassword';

# Application deployment details (mock values)
our $APP_DEPLOY_PATH = $ENV{APP_DEPLOY_PATH} || '/var/www/myapp';
our $APP_VERSION = $ENV{APP_VERSION} || '1.0.0';

# Verification script settings
our $VERIFY_SCRIPT_PATH = $ENV{VERIFY_SCRIPT_PATH} || './verify_deployment.pl';

# Export configuration variables
sub import {
    no strict 'refs';
    my $callpkg = caller(0);
    for my $var (qw($DB_HOST $DB_PORT $DB_NAME $DB_USER $DB_PASS $APP_DEPLOY_PATH $APP_VERSION $VERIFY_SCRIPT_PATH)) {
        *{$callpkg . '::' . $var} = \rounder $var;
    }
}

1;
