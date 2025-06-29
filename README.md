FairShare – Splitwise-Lite 💸
FairShare is a Spring Boot application that mimics core expense‑splitting features of apps like Splitwise, built with Docker, PostgreSQL, and CI/CD deployment.

📌 Key Features
User & Group Management

Register users and create groups.

Manage group membership and permissions.

Expense Tracking & Splitting

Log expenses with details: amount, payer, description.

Support shared participants and multiple splitting modes (e.g., equal, percentage).

Automatically calculate per‑user share using ExpenseSplit entities.

Balance & Debt Simplification

Compute real-time balances: who owes whom and how much.

Net out mutual debts (e.g., A owes B ₹200, B owes A ₹50 → net A owes B ₹150).

Tech Stack & Architecture

Spring Boot, JPA/Hibernate, PostgreSQL → clean domain modeling with @ManyToMany, @OneToMany, @ManyToOne.

Containerized with Docker & Docker Compose.

CI/CD with GitHub Actions → deploy to Render (or alternates like Fly.io, Railway, Zeabur).

Migrations via Liquibase / Flyway; environment variables for configuration.




Used liquibase to manage database migrations.
# Liquibase
Liquibase is a database schema change management tool that allows you to define, track, and apply changes to your database schema in a controlled and versioned manner. It uses XML, YAML, JSON, or SQL files to describe the changes.
# Liquibase Commands
mvn liquibase:diff "-Dliquibase.diffChangeLogFile=src/main/resources/db/changelogs/0001_add_init.sql"  -Pgenerate-db-migration


#Used Swagger Open API to document the REST API.
http://localhost:8080/swagger-ui/index.html
