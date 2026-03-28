--
-- PostgreSQL database cluster dump
--

-- Started on 2026-03-28 18:42:24

\restrict zmyKvKGQ3Hhv0iL3l5uYBHJObQ6k36qhezL6GwBGpnHpmFyuYouMAhOnFrRhDHP

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








\unrestrict zmyKvKGQ3Hhv0iL3l5uYBHJObQ6k36qhezL6GwBGpnHpmFyuYouMAhOnFrRhDHP

--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

\restrict xbVYWiYfa2Cf7NN5f32rewVo7pIdIggX1v6iQybZwKAYeMxNQZNa8cNkB2rRc4F

-- Dumped from database version 18.3
-- Dumped by pg_dump version 18.3

-- Started on 2026-03-28 18:42:25

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2026-03-28 18:42:25

--
-- PostgreSQL database dump complete
--

\unrestrict xbVYWiYfa2Cf7NN5f32rewVo7pIdIggX1v6iQybZwKAYeMxNQZNa8cNkB2rRc4F

--
-- Database "cinema" dump
--

--
-- PostgreSQL database dump
--

\restrict dllXWTpdcUtvfmwvVL1UYnxGGimo7wYob7MTPFSIiTUaVne8snJ67iJFsnvMyKb

-- Dumped from database version 18.3
-- Dumped by pg_dump version 18.3

-- Started on 2026-03-28 18:42:25

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4923 (class 1262 OID 16428)
-- Name: cinema; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE cinema WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';


ALTER DATABASE cinema OWNER TO postgres;

\unrestrict dllXWTpdcUtvfmwvVL1UYnxGGimo7wYob7MTPFSIiTUaVne8snJ67iJFsnvMyKb
\connect cinema
\restrict dllXWTpdcUtvfmwvVL1UYnxGGimo7wYob7MTPFSIiTUaVne8snJ67iJFsnvMyKb

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 16430)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    idade integer NOT NULL,
    cpf character varying(11) NOT NULL
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16429)
-- Name: cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cliente_id_seq OWNER TO postgres;

--
-- TOC entry 4924 (class 0 OID 0)
-- Dependencies: 219
-- Name: cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;


--
-- TOC entry 222 (class 1259 OID 16441)
-- Name: ingresso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ingresso (
    id integer NOT NULL,
    filme character varying(100) NOT NULL,
    sessao character varying(20) NOT NULL,
    id_cliente integer NOT NULL
);


ALTER TABLE public.ingresso OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16440)
-- Name: ingresso_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ingresso_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.ingresso_id_seq OWNER TO postgres;

--
-- TOC entry 4925 (class 0 OID 0)
-- Dependencies: 221
-- Name: ingresso_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ingresso_id_seq OWNED BY public.ingresso.id;


--
-- TOC entry 4760 (class 2604 OID 16433)
-- Name: cliente id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);


--
-- TOC entry 4761 (class 2604 OID 16444)
-- Name: ingresso id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingresso ALTER COLUMN id SET DEFAULT nextval('public.ingresso_id_seq'::regclass);


--
-- TOC entry 4915 (class 0 OID 16430)
-- Dependencies: 220
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (id, nome, idade, cpf) FROM stdin;
1	Ana Lucia	45	12345678901
2	Ana Lucia	45	12345678901
3	Vitoria Abila	17	10987654321
\.


--
-- TOC entry 4917 (class 0 OID 16441)
-- Dependencies: 222
-- Data for Name: ingresso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ingresso (id, filme, sessao, id_cliente) FROM stdin;
\.


--
-- TOC entry 4926 (class 0 OID 0)
-- Dependencies: 219
-- Name: cliente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_seq', 3, true);


--
-- TOC entry 4927 (class 0 OID 0)
-- Dependencies: 221
-- Name: ingresso_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ingresso_id_seq', 1, false);


--
-- TOC entry 4763 (class 2606 OID 16439)
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- TOC entry 4765 (class 2606 OID 16450)
-- Name: ingresso ingresso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingresso
    ADD CONSTRAINT ingresso_pkey PRIMARY KEY (id);


--
-- TOC entry 4766 (class 2606 OID 16451)
-- Name: ingresso fk_cliente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingresso
    ADD CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) REFERENCES public.cliente(id);


-- Completed on 2026-03-28 18:42:26

--
-- PostgreSQL database dump complete
--

\unrestrict dllXWTpdcUtvfmwvVL1UYnxGGimo7wYob7MTPFSIiTUaVne8snJ67iJFsnvMyKb

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

\restrict e0png1A8uMjT9jTIo96uaW9HGyCRJ2ThgiOXyg6mDLeZ2gWhpoe91eHxS7BwUgz

-- Dumped from database version 18.3
-- Dumped by pg_dump version 18.3

-- Started on 2026-03-28 18:42:26

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2026-03-28 18:42:26

--
-- PostgreSQL database dump complete
--

\unrestrict e0png1A8uMjT9jTIo96uaW9HGyCRJ2ThgiOXyg6mDLeZ2gWhpoe91eHxS7BwUgz

--
-- Database "store01" dump
--

--
-- PostgreSQL database dump
--

\restrict 3mjqUj6XOqW4esFDZJA48KUxFbcHvtk6fEqfmhWrxXKtT5dtbVYN60m0lSPtW3I

-- Dumped from database version 18.3
-- Dumped by pg_dump version 18.3

-- Started on 2026-03-28 18:42:26

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4934 (class 1262 OID 16388)
-- Name: store01; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE store01 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';


ALTER DATABASE store01 OWNER TO postgres;

\unrestrict 3mjqUj6XOqW4esFDZJA48KUxFbcHvtk6fEqfmhWrxXKtT5dtbVYN60m0lSPtW3I
\connect store01
\restrict 3mjqUj6XOqW4esFDZJA48KUxFbcHvtk6fEqfmhWrxXKtT5dtbVYN60m0lSPtW3I

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 16389)
-- Name: clientes; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA clientes;


ALTER SCHEMA clientes OWNER TO postgres;

--
-- TOC entry 7 (class 2615 OID 16417)
-- Name: produtos; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA produtos;


ALTER SCHEMA produtos OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 224 (class 1259 OID 16404)
-- Name: dados_clientes; Type: TABLE; Schema: clientes; Owner: postgres
--

CREATE TABLE clientes.dados_clientes (
    id integer NOT NULL,
    nome character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    telefone integer NOT NULL
);


ALTER TABLE clientes.dados_clientes OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16403)
-- Name: dados_clientes_id_seq; Type: SEQUENCE; Schema: clientes; Owner: postgres
--

CREATE SEQUENCE clientes.dados_clientes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE clientes.dados_clientes_id_seq OWNER TO postgres;

--
-- TOC entry 4935 (class 0 OID 0)
-- Dependencies: 223
-- Name: dados_clientes_id_seq; Type: SEQUENCE OWNED BY; Schema: clientes; Owner: postgres
--

ALTER SEQUENCE clientes.dados_clientes_id_seq OWNED BY clientes.dados_clientes.id;


--
-- TOC entry 226 (class 1259 OID 16419)
-- Name: dados_produtos; Type: TABLE; Schema: produtos; Owner: postgres
--

CREATE TABLE produtos.dados_produtos (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    descricao character varying(150),
    preco integer NOT NULL
);


ALTER TABLE produtos.dados_produtos OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16418)
-- Name: dados_produtos_id_seq; Type: SEQUENCE; Schema: produtos; Owner: postgres
--

CREATE SEQUENCE produtos.dados_produtos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE produtos.dados_produtos_id_seq OWNER TO postgres;

--
-- TOC entry 4936 (class 0 OID 0)
-- Dependencies: 225
-- Name: dados_produtos_id_seq; Type: SEQUENCE OWNED BY; Schema: produtos; Owner: postgres
--

ALTER SEQUENCE produtos.dados_produtos_id_seq OWNED BY produtos.dados_produtos.id;


--
-- TOC entry 222 (class 1259 OID 16391)
-- Name: dados_clientes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dados_clientes (
    id integer NOT NULL,
    nome character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    telefone integer NOT NULL
);


ALTER TABLE public.dados_clientes OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16390)
-- Name: dados_clientes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.dados_clientes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.dados_clientes_id_seq OWNER TO postgres;

--
-- TOC entry 4937 (class 0 OID 0)
-- Dependencies: 221
-- Name: dados_clientes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.dados_clientes_id_seq OWNED BY public.dados_clientes.id;


--
-- TOC entry 4768 (class 2604 OID 16407)
-- Name: dados_clientes id; Type: DEFAULT; Schema: clientes; Owner: postgres
--

ALTER TABLE ONLY clientes.dados_clientes ALTER COLUMN id SET DEFAULT nextval('clientes.dados_clientes_id_seq'::regclass);


--
-- TOC entry 4769 (class 2604 OID 16422)
-- Name: dados_produtos id; Type: DEFAULT; Schema: produtos; Owner: postgres
--

ALTER TABLE ONLY produtos.dados_produtos ALTER COLUMN id SET DEFAULT nextval('produtos.dados_produtos_id_seq'::regclass);


--
-- TOC entry 4767 (class 2604 OID 16394)
-- Name: dados_clientes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dados_clientes ALTER COLUMN id SET DEFAULT nextval('public.dados_clientes_id_seq'::regclass);


--
-- TOC entry 4926 (class 0 OID 16404)
-- Dependencies: 224
-- Data for Name: dados_clientes; Type: TABLE DATA; Schema: clientes; Owner: postgres
--

COPY clientes.dados_clientes (id, nome, email, telefone) FROM stdin;
\.


--
-- TOC entry 4928 (class 0 OID 16419)
-- Dependencies: 226
-- Data for Name: dados_produtos; Type: TABLE DATA; Schema: produtos; Owner: postgres
--

COPY produtos.dados_produtos (id, nome, descricao, preco) FROM stdin;
\.


--
-- TOC entry 4924 (class 0 OID 16391)
-- Dependencies: 222
-- Data for Name: dados_clientes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dados_clientes (id, nome, email, telefone) FROM stdin;
\.


--
-- TOC entry 4938 (class 0 OID 0)
-- Dependencies: 223
-- Name: dados_clientes_id_seq; Type: SEQUENCE SET; Schema: clientes; Owner: postgres
--

SELECT pg_catalog.setval('clientes.dados_clientes_id_seq', 1, false);


--
-- TOC entry 4939 (class 0 OID 0)
-- Dependencies: 225
-- Name: dados_produtos_id_seq; Type: SEQUENCE SET; Schema: produtos; Owner: postgres
--

SELECT pg_catalog.setval('produtos.dados_produtos_id_seq', 1, false);


--
-- TOC entry 4940 (class 0 OID 0)
-- Dependencies: 221
-- Name: dados_clientes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dados_clientes_id_seq', 1, false);


--
-- TOC entry 4773 (class 2606 OID 16415)
-- Name: dados_clientes dados_clientes_pkey; Type: CONSTRAINT; Schema: clientes; Owner: postgres
--

ALTER TABLE ONLY clientes.dados_clientes
    ADD CONSTRAINT dados_clientes_pkey PRIMARY KEY (id);


--
-- TOC entry 4775 (class 2606 OID 16427)
-- Name: dados_produtos dados_produtos_pkey; Type: CONSTRAINT; Schema: produtos; Owner: postgres
--

ALTER TABLE ONLY produtos.dados_produtos
    ADD CONSTRAINT dados_produtos_pkey PRIMARY KEY (id);


--
-- TOC entry 4771 (class 2606 OID 16402)
-- Name: dados_clientes dados_clientes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dados_clientes
    ADD CONSTRAINT dados_clientes_pkey PRIMARY KEY (id);


-- Completed on 2026-03-28 18:42:27

--
-- PostgreSQL database dump complete
--

\unrestrict 3mjqUj6XOqW4esFDZJA48KUxFbcHvtk6fEqfmhWrxXKtT5dtbVYN60m0lSPtW3I

-- Completed on 2026-03-28 18:42:27

--
-- PostgreSQL database cluster dump complete
--

