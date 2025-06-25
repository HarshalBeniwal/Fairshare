# Fairshare


Used liquibase to manage database migrations.
# Liquibase
Liquibase is a database schema change management tool that allows you to define, track, and apply changes to your database schema in a controlled and versioned manner. It uses XML, YAML, JSON, or SQL files to describe the changes.
# Liquibase Commands
mvn liquibase:diff "-Dliquibase.diffChangeLogFile=src/main/resources/db/changelogs/0001_add_init.sql"  -Pgenerate-db-migration