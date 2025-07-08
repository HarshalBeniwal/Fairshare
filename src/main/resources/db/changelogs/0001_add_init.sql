-- liquibase formatted sql

-- changeset DELL:1751970996551-1
ALTER TABLE users ADD "fullName" VARCHAR(150) NOT NULL;

-- changeset DELL:1751970996551-2
ALTER TABLE users DROP COLUMN full_name;

