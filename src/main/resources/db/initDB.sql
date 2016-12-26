DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS samples CASCADE ;
DROP TABLE IF EXISTS sample_families;
DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS rheology CASCADE ;
DROP TABLE IF EXISTS gels CASCADE ;
DROP TABLE IF EXISTS sample_components;
DROP TABLE IF EXISTS sample_applications;
DROP TABLE IF EXISTS basic_mud_tests;
DROP TABLE IF EXISTS basic_powder_tests;

DROP SEQUENCE IF EXISTS user_seq;
DROP SEQUENCE IF EXISTS samples_seq;
DROP SEQUENCE IF EXISTS help_seq;

CREATE SEQUENCE user_seq START 1;
CREATE SEQUENCE samples_seq START 200000;
CREATE SEQUENCE help_seq START 10000000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
  name       VARCHAR NOT NULL
);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE samples
(
  id              INTEGER PRIMARY KEY DEFAULT nextval('samples_seq'),
  name            VARCHAR NOT NULL,
  family_id       INTEGER,
  id_in_family    INTEGER,
  weight          FLOAT,
  arrived         DATE,
  notes           VARCHAR,
  vendor_id       INTEGER,
  manufacturer_id INTEGER
);
CREATE UNIQUE INDEX id_in_family_idx ON samples(id_in_family);

CREATE TABLE sample_families
(
  id                  INTEGER PRIMARY KEY DEFAULT nextval('help_seq'),
  name                VARCHAR NOT NULL,
  initial_family_id   INTEGER,
  current_family_id   INTEGER
);
CREATE UNIQUE INDEX family_name_idx ON sample_families(name);

CREATE TABLE companies
(
  id           INTEGER PRIMARY KEY DEFAULT nextval('help_seq'),
  name         VARCHAR NOT NULL
);
CREATE UNIQUE INDEX company_name_idx ON companies(name);

CREATE TABLE sample_components
(
  id           INTEGER PRIMARY KEY DEFAULT nextval('help_seq'),
  name         VARCHAR NOT NULL,
  percent      FLOAT,
  sample_id    INTEGER NOT NULL,
  CONSTRAINT sample_component_idx UNIQUE(sample_id, name),
  FOREIGN KEY (sample_id) REFERENCES samples (id) ON DELETE CASCADE
);

CREATE TABLE sample_applications
(
  id           INTEGER PRIMARY KEY DEFAULT nextval('help_seq'),
  name         VARCHAR NOT NULL,
  sample_id    INTEGER NOT NULL,
  CONSTRAINT sample_applications_idx UNIQUE(sample_id, name),
  FOREIGN KEY (sample_id) REFERENCES samples (id) ON DELETE CASCADE
);

CREATE TABLE basic_mud_tests
(
  id INTEGER PRIMARY KEY DEFAULT nextval('help_seq'),
  ph FLOAT,
  mudweight FLOAT,
  viscosity INT,
  fluid_loss FLOAT,
  sample_id INT,
  FOREIGN KEY (sample_id) REFERENCES samples (id) ON DELETE CASCADE,
  sample_content FLOAT
);

CREATE TABLE rheology
(
  test_id      INTEGER,
  FOREIGN KEY (test_id) REFERENCES basic_mud_tests (id) ON DELETE CASCADE,
  temp         INTEGER,
  rpm600       INTEGER,
  rpm300       INTEGER,
  rpm200       INTEGER,
  rpm100       INTEGER,
  rpm60        INTEGER,
  rpm30        INTEGER,
  rpm6         INTEGER,
  rpm3         INTEGER
);

CREATE TABLE gels
(
  test_id       INTEGER,
  FOREIGN KEY (test_id) REFERENCES basic_mud_tests (id) ON DELETE CASCADE,
  temp          INTEGER,
  after10s      INTEGER,
  after60s      INTEGER,
  after600s     INTEGER
);

CREATE TABLE basic_powder_tests
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('help_seq'),
  sample_id     INTEGER,
  sg            INTEGER,
  bulk_sg       INTEGER,
  wetness       FLOAT,
  appearence    VARCHAR,
  water_soluble BOOLEAN
);


