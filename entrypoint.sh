#!/bin/sh
# Wait for PostgreSQL to be ready
./wait-for-it.sh postgres:5432 --timeout=30 --strict -- echo "PostgreSQL is up"

# Run the application
exec java -jar app.jar
