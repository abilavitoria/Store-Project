--
-- PostgreSQL database cluster dump
--

-- Started on 2026-03-28 18:48:53

\restrict 7TZyJ55GyL6aP5Gwn2SNxFlOVbYfDAPRZ9PnXK6GjlB7CIrgMeckPyHminq3nZ9

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:3/cYJp+MfF/B61HvWQC9Ew==$Qt0X6ulDBb5+/DFwrOw5/+HIg0qB3qL7iaI8reaKLAw=:MxHgwlghs5F+vg7Nt3Muh4pAK+zrvN3c3QoXLamKi3k=';

--
-- User Configurations
--








\unrestrict 7TZyJ55GyL6aP5Gwn2SNxFlOVbYfDAPRZ9PnXK6GjlB7CIrgMeckPyHminq3nZ9

-- Completed on 2026-03-28 18:48:53

--
-- PostgreSQL database cluster dump complete
--

