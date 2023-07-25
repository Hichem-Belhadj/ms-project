-- SCHEMA: inventory_service

-- DROP SCHEMA IF EXISTS inventory_service ;

CREATE SCHEMA IF NOT EXISTS inventory_service
    AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA inventory_service
    IS 'standard inventory_service schema';

GRANT USAGE ON SCHEMA inventory_service TO PUBLIC;

GRANT ALL ON SCHEMA inventory_service TO pg_database_owner;