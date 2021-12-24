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

CREATE TABLE public.t_categories
(
    id   bigint                 NOT NULL primary key,
    name character varying(255) NOT NULL
);


ALTER TABLE public.t_categories
    OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 28354)
-- Name: t_categories_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_categories_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_categories_id_seq
    OWNER TO postgres;

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

CREATE TABLE public.t_groups
(
    id   bigint NOT NULL primary key,
    name character varying(255)
);


ALTER TABLE public.t_groups
    OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 28370)
-- Name: t_groups_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_groups_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_groups_id_seq
    OWNER TO postgres;

--
-- TOC entry 2910 (class 0 OID 0)
-- Dependencies: 210
-- Name: t_groups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_groups_id_seq OWNED BY public.t_groups.id;


CREATE TABLE public.t_newstype
(
    id   bigint NOT NULL primary key,
    type character varying(255)
);


ALTER TABLE public.t_newstype
    OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 28404)
-- Name: t_newstype_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_newstype_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_newstype_id_seq
    OWNER TO postgres;

--
-- TOC entry 2912 (class 0 OID 0)
-- Dependencies: 215
-- Name: t_newstype_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_newstype_id_seq OWNED BY public.t_newstype.id;


--
-- TOC entry 214 (class 1259 OID 28395)
-- Name: t_news; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_news
(
    id             bigint NOT NULL primary key,
    content        character varying(255),
    published_date date DEFAULT CURRENT_DATE,
    title          character varying(255),
    newstype_id    bigint,
    foreign key (newstype_id) references public.t_newstype (id)
);


ALTER TABLE public.t_news
    OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 28393)
-- Name: t_news_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_news_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_news_id_seq
    OWNER TO postgres;

--
-- TOC entry 2911 (class 0 OID 0)
-- Dependencies: 213
-- Name: t_news_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_news_id_seq OWNED BY public.t_news.id;


--
-- TOC entry 203 (class 1259 OID 28320)
-- Name: t_places; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.t_places
(
    id          bigint NOT NULL primary key,
    address     character varying(255),
    description character varying(255),
    name        character varying(255),
    category_id bigint,
    foreign key (category_id) references public.t_categories (id)
);


ALTER TABLE public.t_places
    OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 28318)
-- Name: t_places_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_places_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_places_id_seq
    OWNER TO postgres;

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

CREATE TABLE public.t_roles
(
    id   bigint                 NOT NULL primary key,
    name character varying(255) NOT NULL
);


ALTER TABLE public.t_roles
    OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 28326)
-- Name: t_roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_roles_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_roles_id_seq
    OWNER TO postgres;

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

CREATE TABLE public.t_users
(
    id       bigint                 NOT NULL primary key,
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL unique ,
    role_id  bigint,
    foreign key (role_id) references public.t_roles (id)
);


ALTER TABLE public.t_users
    OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 28334)
-- Name: t_users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_users_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_users_id_seq
    OWNER TO postgres;


CREATE TABLE public.t_users_t_groups
(
    users_id  bigint NOT NULL,
    groups_id bigint NOT NULL,
    foreign key (users_id) references public.t_users (id),
    foreign key (groups_id) references public.t_groups (id)
);


ALTER TABLE public.t_users_t_groups
    OWNER TO postgres;


CREATE TABLE public.t_customlog
(
    id  bigint       not null primary key,
    log varchar(255) not null
);


ALTER TABLE public.t_customlog
    OWNER TO postgres;