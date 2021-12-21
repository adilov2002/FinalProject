--
-- PostgreSQL database dump
--

-- Dumped from database version 12.7
-- Dumped by pg_dump version 12.7

-- Started on 2021-12-21 11:29:07

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
-- TOC entry 209 (class 1259 OID 28356)
-- Name: t_categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_categories (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.t_categories OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 28354)
-- Name: t_categories_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_categories_id_seq OWNER TO postgres;

--
-- TOC entry 2909 (class 0 OID 0)
-- Dependencies: 208
-- Name: t_categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_categories_id_seq OWNED BY public.t_categories.id;


--
-- TOC entry 211 (class 1259 OID 28372)
-- Name: t_groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_groups (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.t_groups OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 28370)
-- Name: t_groups_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_groups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_groups_id_seq OWNER TO postgres;

--
-- TOC entry 2910 (class 0 OID 0)
-- Dependencies: 210
-- Name: t_groups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_groups_id_seq OWNED BY public.t_groups.id;


--
-- TOC entry 214 (class 1259 OID 28395)
-- Name: t_news; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_news (
    id bigint NOT NULL,
    content character varying(255),
    published_date date DEFAULT CURRENT_DATE,
    title character varying(255),
    newstype_id bigint
);


ALTER TABLE public.t_news OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 28393)
-- Name: t_news_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_news_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_news_id_seq OWNER TO postgres;

--
-- TOC entry 2911 (class 0 OID 0)
-- Dependencies: 213
-- Name: t_news_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_news_id_seq OWNED BY public.t_news.id;


--
-- TOC entry 216 (class 1259 OID 28406)
-- Name: t_newstype; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_newstype (
    id bigint NOT NULL,
    type character varying(255)
);


ALTER TABLE public.t_newstype OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 28404)
-- Name: t_newstype_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_newstype_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_newstype_id_seq OWNER TO postgres;

--
-- TOC entry 2912 (class 0 OID 0)
-- Dependencies: 215
-- Name: t_newstype_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_newstype_id_seq OWNED BY public.t_newstype.id;


--
-- TOC entry 203 (class 1259 OID 28320)
-- Name: t_places; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_places (
    id bigint NOT NULL,
    address character varying(255),
    description character varying(255),
    name character varying(255),
    category_id bigint
);


ALTER TABLE public.t_places OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 28318)
-- Name: t_places_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_places_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_places_id_seq OWNER TO postgres;

--
-- TOC entry 2913 (class 0 OID 0)
-- Dependencies: 202
-- Name: t_places_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_places_id_seq OWNED BY public.t_places.id;


--
-- TOC entry 205 (class 1259 OID 28328)
-- Name: t_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_roles (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.t_roles OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 28326)
-- Name: t_roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_roles_id_seq OWNER TO postgres;

--
-- TOC entry 2914 (class 0 OID 0)
-- Dependencies: 204
-- Name: t_roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_roles_id_seq OWNED BY public.t_roles.id;


--
-- TOC entry 207 (class 1259 OID 28336)
-- Name: t_users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_users (
    id bigint NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    role_id bigint
);


ALTER TABLE public.t_users OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 28334)
-- Name: t_users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_users_id_seq OWNER TO postgres;

--
-- TOC entry 2915 (class 0 OID 0)
-- Dependencies: 206
-- Name: t_users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_users_id_seq OWNED BY public.t_users.id;


--
-- TOC entry 212 (class 1259 OID 28378)
-- Name: t_users_t_groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_users_t_groups (
    users_id bigint NOT NULL,
    groups_id bigint NOT NULL
);


ALTER TABLE public.t_users_t_groups OWNER TO postgres;

--
-- TOC entry 2733 (class 2604 OID 28359)
-- Name: t_categories id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_categories ALTER COLUMN id SET DEFAULT nextval('public.t_categories_id_seq'::regclass);


--
-- TOC entry 2734 (class 2604 OID 28375)
-- Name: t_groups id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_groups ALTER COLUMN id SET DEFAULT nextval('public.t_groups_id_seq'::regclass);


--
-- TOC entry 2735 (class 2604 OID 28398)
-- Name: t_news id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_news ALTER COLUMN id SET DEFAULT nextval('public.t_news_id_seq'::regclass);


--
-- TOC entry 2737 (class 2604 OID 28409)
-- Name: t_newstype id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_newstype ALTER COLUMN id SET DEFAULT nextval('public.t_newstype_id_seq'::regclass);


--
-- TOC entry 2730 (class 2604 OID 28323)
-- Name: t_places id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_places ALTER COLUMN id SET DEFAULT nextval('public.t_places_id_seq'::regclass);


--
-- TOC entry 2731 (class 2604 OID 28331)
-- Name: t_roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_roles ALTER COLUMN id SET DEFAULT nextval('public.t_roles_id_seq'::regclass);


--
-- TOC entry 2732 (class 2604 OID 28339)
-- Name: t_users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_users ALTER COLUMN id SET DEFAULT nextval('public.t_users_id_seq'::regclass);


--
-- TOC entry 2896 (class 0 OID 28356)
-- Dependencies: 209
-- Data for Name: t_categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.t_categories (id, name) FROM stdin;
1	SportComplex
2	Library
3	Stadion
4	TRC
\.


--
-- TOC entry 2898 (class 0 OID 28372)
-- Dependencies: 211
-- Data for Name: t_groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.t_groups (id, name) FROM stdin;
\.


--
-- TOC entry 2901 (class 0 OID 28395)
-- Dependencies: 214
-- Data for Name: t_news; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.t_news (id, content, published_date, title, newstype_id) FROM stdin;
\.


--
-- TOC entry 2903 (class 0 OID 28406)
-- Dependencies: 216
-- Data for Name: t_newstype; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.t_newstype (id, type) FROM stdin;
\.


--
-- TOC entry 2890 (class 0 OID 28320)
-- Dependencies: 203
-- Data for Name: t_places; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.t_places (id, address, description, name, category_id) FROM stdin;
1	Abay 171	The library for Abay poet	Abay's Library	2
2	Bayzakova 24	\N	Pheonix	1
3	Nazarbayev 15	\N	Kairat Stadion	3
4	Panfilov 45	null	Panfilov Library	2
5	Baikonyr 89	\N	Promenade	4
6	Satpayev 77	\N	Dostyk Plaza	4
\.


--
-- TOC entry 2892 (class 0 OID 28328)
-- Dependencies: 205
-- Data for Name: t_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.t_roles (id, name) FROM stdin;
1	ROLE_ADMIN
2	ROLE_USER
\.


--
-- TOC entry 2894 (class 0 OID 28336)
-- Dependencies: 207
-- Data for Name: t_users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.t_users (id, password, username, role_id) FROM stdin;
\.


--
-- TOC entry 2899 (class 0 OID 28378)
-- Dependencies: 212
-- Data for Name: t_users_t_groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.t_users_t_groups (users_id, groups_id) FROM stdin;
\.


--
-- TOC entry 2916 (class 0 OID 0)
-- Dependencies: 208
-- Name: t_categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_categories_id_seq', 3, true);


--
-- TOC entry 2917 (class 0 OID 0)
-- Dependencies: 210
-- Name: t_groups_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_groups_id_seq', 1, false);


--
-- TOC entry 2918 (class 0 OID 0)
-- Dependencies: 213
-- Name: t_news_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_news_id_seq', 1, false);


--
-- TOC entry 2919 (class 0 OID 0)
-- Dependencies: 215
-- Name: t_newstype_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_newstype_id_seq', 1, false);


--
-- TOC entry 2920 (class 0 OID 0)
-- Dependencies: 202
-- Name: t_places_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_places_id_seq', 6, true);


--
-- TOC entry 2921 (class 0 OID 0)
-- Dependencies: 204
-- Name: t_roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_roles_id_seq', 1, false);


--
-- TOC entry 2922 (class 0 OID 0)
-- Dependencies: 206
-- Name: t_users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_users_id_seq', 1, false);


--
-- TOC entry 2749 (class 2606 OID 28361)
-- Name: t_categories t_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_categories
    ADD CONSTRAINT t_categories_pkey PRIMARY KEY (id);


--
-- TOC entry 2751 (class 2606 OID 28377)
-- Name: t_groups t_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_groups
    ADD CONSTRAINT t_groups_pkey PRIMARY KEY (id);


--
-- TOC entry 2755 (class 2606 OID 28403)
-- Name: t_news t_news_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_news
    ADD CONSTRAINT t_news_pkey PRIMARY KEY (id);


--
-- TOC entry 2757 (class 2606 OID 28411)
-- Name: t_newstype t_newstype_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_newstype
    ADD CONSTRAINT t_newstype_pkey PRIMARY KEY (id);


--
-- TOC entry 2739 (class 2606 OID 28325)
-- Name: t_places t_places_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_places
    ADD CONSTRAINT t_places_pkey PRIMARY KEY (id);


--
-- TOC entry 2741 (class 2606 OID 28333)
-- Name: t_roles t_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_roles
    ADD CONSTRAINT t_roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2745 (class 2606 OID 28344)
-- Name: t_users t_users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_users
    ADD CONSTRAINT t_users_pkey PRIMARY KEY (id);


--
-- TOC entry 2753 (class 2606 OID 28382)
-- Name: t_users_t_groups uk_fm0pqush0vo48f0lqyt6cexo2; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_users_t_groups
    ADD CONSTRAINT uk_fm0pqush0vo48f0lqyt6cexo2 UNIQUE (groups_id);


--
-- TOC entry 2743 (class 2606 OID 28346)
-- Name: t_roles uk_go0rigpiaq82tlqv1kqnwx2ya; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_roles
    ADD CONSTRAINT uk_go0rigpiaq82tlqv1kqnwx2ya UNIQUE (name);


--
-- TOC entry 2747 (class 2606 OID 28348)
-- Name: t_users uk_sp0e01od15gf4nu5ffu87qb9n; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_users
    ADD CONSTRAINT uk_sp0e01od15gf4nu5ffu87qb9n UNIQUE (username);


--
-- TOC entry 2761 (class 2606 OID 28388)
-- Name: t_users_t_groups fk50rv093sdm22drbgpfkim0y84; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_users_t_groups
    ADD CONSTRAINT fk50rv093sdm22drbgpfkim0y84 FOREIGN KEY (users_id) REFERENCES public.t_users(id);


--
-- TOC entry 2762 (class 2606 OID 28412)
-- Name: t_news fk84fyvnaxofue9vm0k6ret64i7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_news
    ADD CONSTRAINT fk84fyvnaxofue9vm0k6ret64i7 FOREIGN KEY (newstype_id) REFERENCES public.t_newstype(id);


--
-- TOC entry 2758 (class 2606 OID 28365)
-- Name: t_places fkhky0jhl5stk4exsnxuyo03238; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_places
    ADD CONSTRAINT fkhky0jhl5stk4exsnxuyo03238 FOREIGN KEY (category_id) REFERENCES public.t_categories(id);


--
-- TOC entry 2760 (class 2606 OID 28383)
-- Name: t_users_t_groups fkm0d730qk5fc374n5o8jngm1gg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_users_t_groups
    ADD CONSTRAINT fkm0d730qk5fc374n5o8jngm1gg FOREIGN KEY (groups_id) REFERENCES public.t_groups(id);


--
-- TOC entry 2759 (class 2606 OID 28349)
-- Name: t_users fkqv83xmq1wqpg9e1snck4lewvn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_users
    ADD CONSTRAINT fkqv83xmq1wqpg9e1snck4lewvn FOREIGN KEY (role_id) REFERENCES public.t_roles(id);


-- Completed on 2021-12-21 11:29:07

--
-- PostgreSQL database dump complete
--

