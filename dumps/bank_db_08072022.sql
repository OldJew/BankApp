--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1 (Debian 14.1-5)
-- Dumped by pg_dump version 14.1 (Debian 14.1-5)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
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
-- Name: fin_operations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fin_operations (
    id bigint NOT NULL,
    date date,
    operation character varying(255) NOT NULL,
    value numeric(19,2) NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.fin_operations OWNER TO postgres;

--
-- Name: fin_operations_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fin_operations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fin_operations_id_seq OWNER TO postgres;

--
-- Name: fin_operations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.fin_operations_id_seq OWNED BY public.fin_operations.id;


--
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    balance numeric(19,2),
    first_name character varying(255),
    last_name character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: fin_operations id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fin_operations ALTER COLUMN id SET DEFAULT nextval('public.fin_operations_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: fin_operations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fin_operations (id, date, operation, value, user_id) FROM stdin;
1	2022-07-07	withdraw	1000.00	1
2	2022-07-07	deposit	1000.00	1
3	2022-07-07	withdraw	1000.00	1
4	2022-07-07	deposit	1000.00	1
5	2022-07-07	withdraw	1000.00	1
6	2022-07-07	deposit	1000.00	1
7	2022-07-07	withdraw	1000.00	1
8	2022-07-07	deposit	1000.00	1
9	2022-07-07	withdraw	1000.00	1
10	2022-07-07	deposit	1000.00	1
11	2022-07-07	withdraw	10000.00	1
12	2022-07-07	deposit	1000.00	1
\.


--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	Init DB Default Data	SQL	V1__Init_DB_Default_Data.sql	-1576749870	postgres	2022-07-07 22:52:56.215733	15	t
2	2	Add FinOperation Table	SQL	V2__Add_FinOperation_Table.sql	-1704833504	postgres	2022-07-07 22:52:56.254934	36	t
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, balance, first_name, last_name) FROM stdin;
2	100.00	Foo	Bar
3	5000.00	Ivan	Petrov
4	7000.00	Petr	Ivanov
5	5000.00	John	Dow
6	50000.00	Isaac	Asimov
7	0.00	Caliban	Robot
1	1000.00	Test	Testerov
\.


--
-- Name: fin_operations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.fin_operations_id_seq', 12, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 7, true);


--
-- Name: fin_operations fin_operations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fin_operations
    ADD CONSTRAINT fin_operations_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- Name: fin_operations fk_fin_operations_to_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fin_operations
    ADD CONSTRAINT fk_fin_operations_to_users FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: TABLE fin_operations; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.fin_operations TO "user";


--
-- Name: SEQUENCE fin_operations_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.fin_operations_id_seq TO "user";


--
-- Name: TABLE flyway_schema_history; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.flyway_schema_history TO "user";


--
-- Name: TABLE users; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.users TO "user";


--
-- Name: SEQUENCE users_id_seq; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON SEQUENCE public.users_id_seq TO "user";


--
-- PostgreSQL database dump complete
--

