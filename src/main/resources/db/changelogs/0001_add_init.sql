-- liquibase formatted sql

-- changeset HarshalBeniwal:1750778275567-1
CREATE SEQUENCE  IF NOT EXISTS users_seq START WITH 1 INCREMENT BY 1;

-- changeset HarshalBeniwal:1750778275567-2
CREATE TABLE group_members (group_id UUID NOT NULL, user_id BIGINT NOT NULL);

-- changeset HarshalBeniwal:1750778275567-3
CREATE TABLE user_group (id UUID NOT NULL, group_name VARCHAR(255) NOT NULL, created_by BIGINT NOT NULL, CONSTRAINT "user_groupPK" PRIMARY KEY (id));

-- changeset HarshalBeniwal:1750778275567-4
CREATE TABLE users (id BIGINT NOT NULL, email VARCHAR(150) NOT NULL, user_name VARCHAR(100) NOT NULL, CONSTRAINT "usersPK" PRIMARY KEY (id));

-- changeset HarshalBeniwal:1750778275567-5
ALTER TABLE users ADD CONSTRAINT UC_USERSEMAIL_COL UNIQUE (email);

-- changeset HarshalBeniwal:1750778275567-6
ALTER TABLE group_members ADD CONSTRAINT "FKa0rnvuit5l8opskad2n72w9ki" FOREIGN KEY (group_id) REFERENCES user_group (id);

-- changeset HarshalBeniwal:1750778275567-7
ALTER TABLE group_members ADD CONSTRAINT "FKnr9qg33qt2ovmv29g4vc3gtdx" FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset HarshalBeniwal:1750778275567-8
ALTER TABLE user_group ADD CONSTRAINT fk_created_by_user FOREIGN KEY (created_by) REFERENCES users (id);

