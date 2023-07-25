-- SCHEMA: order_service

-- DROP SCHEMA IF EXISTS order_service ;

CREATE SCHEMA IF NOT EXISTS order_service
    AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA order_service
    IS 'standard order_service schema';

GRANT USAGE ON SCHEMA order_service TO PUBLIC;

GRANT ALL ON SCHEMA order_service TO pg_database_owner;