use strict;
use warnings;
use DBI;
use Text::CSV;

# Load configuration
use etl_config;

# Establish database connection
my $dsn = "DBI:$etl_config::db_type:$etl_config::db_host:$etl_config::db_port/$etl_config::db_name";
my $dbh = DBI->connect($dsn, $etl_config::db_user, $etl_config::db_password) or die "Cannot connect to database: $DBI::errstr";

# Prepare and execute query
my $query = $etl_config::extract_query;
my $sth = $dbh->prepare($query) or die "Cannot prepare query: $DBI::errstr";
$sth->execute() or die "Cannot execute query: $DBI::errstr";

# Fetch query results
my $csv = Text::CSV->new({ sep_char => ',' });
my $fh = IO::File->new($etl_config::extract_file, 'w') or die "Cannot open file: $!";
$csv->print($fh, [$etl_config::extract_headers]);
while (my $row = $sth->fetchrow_hashref()) {
    my @values;
    foreach my $header (@{$etl_config::extract_headers}) {
        push @values, $row->{$header};
    }
    $csv->print($fh, \@values);
}
$fh->close();

# Close database connection
$dbh->disconnect();