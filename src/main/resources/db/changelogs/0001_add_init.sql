-- liquibase formatted sql

-- changeset codespace:1751125148242-1
ALTER TABLE expense ADD split_mode VARCHAR(255);

-- changeset codespace:1751125148242-2
ALTER TABLE expense DROP COLUMN "splitMode";

