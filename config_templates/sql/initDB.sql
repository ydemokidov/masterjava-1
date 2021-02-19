DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cities;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS user_groups;

DROP SEQUENCE IF EXISTS user_seq;
DROP SEQUENCE IF EXISTS city_seq;
DROP SEQUENCE IF EXISTS group_seq;
DROP SEQUENCE IF EXISTS user_group_seq;
DROP SEQUENCE IF EXISTS project_seq;

DROP TYPE IF EXISTS user_flag;
DROP TYPE IF EXISTS group_type;

CREATE SEQUENCE city_seq START 100;
CREATE TABLE cities(
                       id INTEGER PRIMARY KEY DEFAULT nextval('city_seq'),
                       short_name TEXT NOT NULL,
                       name TEXT NOT NULL
);

CREATE SEQUENCE project_seq START 100;
CREATE TABLE projects(
                         id INTEGER PRIMARY KEY DEFAULT nextval('project_seq'),
                         name TEXT NOT NULL
);

CREATE TYPE group_type AS ENUM ('REGISTERING','CURRENT','FINISHED');
CREATE SEQUENCE group_seq START 100;
CREATE TABLE groups(
                       id INTEGER PRIMARY KEY DEFAULT nextval('group_seq'),
                       name TEXT NOT NULL,
                       type group_type NOT NULL,
                       project_id INTEGER,
                       CONSTRAINT fk_project
                           FOREIGN KEY (project_id)
                               REFERENCES projects(id)
);

CREATE SEQUENCE user_group_seq START 100;
CREATE TABLE user_groups(
                            id INTEGER PRIMARY KEY DEFAULT nextval('user_group_seq'),
                            user_id INTEGER,
                            group_id INTEGER
);

CREATE TYPE user_flag AS ENUM ('active', 'deleted', 'superuser');
CREATE SEQUENCE user_seq START 100000;
CREATE TABLE users (
                       id        INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
                       full_name TEXT NOT NULL,
                       email     TEXT NOT NULL,
                       flag      user_flag NOT NULL,
                       city_id   INTEGER,
                       CONSTRAINT fk_city
                           FOREIGN KEY (city_id)
                               REFERENCES cities(id)
);
CREATE UNIQUE INDEX email_idx ON users (email);
