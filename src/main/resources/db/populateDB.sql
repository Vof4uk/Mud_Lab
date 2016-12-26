DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM sample_families;
DELETE FROM samples;
DELETE FROM sample_applications;
DELETE FROM companies;
DELETE FROM sample_components;
DELETE FROM gels;
DELETE FROM rheology;
DELETE FROM basic_mud_tests;
DELETE FROM basic_powder_tests;
ALTER SEQUENCE user_seq RESTART WITH 1;
ALTER SEQUENCE help_seq RESTART WITH 10000000;
ALTER SEQUENCE samples_seq RESTART WITH 200000;

INSERT INTO users (name) VALUES
  ('unauthorized'),
  ('assist'),
  ('chiefLab'),
  ('boss'),
  ('admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ADMIN', 5),
  ('LAB_CHIEF', 3),
  ('LAB_ASSIST', 2),
  ('BOSS', 4);

INSERT INTO sample_families (name, initial_family_id, current_family_id) VALUES
  ('polymers', 5000, 5000),
  ('ligno', 8000, 8000),
  ('other', 2000, 2000);

INSERT INTO companies (name) VALUES
  ('garage1'),
  ('bazar1'),
  ('garage2'),
  ('olx.com');

INSERT INTO samples (name, family_id, id_in_family, weight, arrived, notes, vendor_id, manufacturer_id) VALUES
  ('barazan D', 10000000, 5000, 0.9, current_date, 'some notes', 10000004, 10000003),
  ('УЩР', 10000001, 8000, 1.1, current_date, 'other notes', 10000006, 10000005);

INSERT INTO sample_applications (name, sample_id) VALUES
  ('viscosifier' , 200000),
  ('thinner', 200001),
  ('defloc', 200001);

INSERT INTO sample_components (name, percent, sample_id) VALUES
  ('xanthan gum', 100, 200000),
  ('potassium humate', 90, 200001),
  ('caustic potassium', 10, 200001);

INSERT INTO basic_mud_tests
(ph, mudweight, viscosity, fluid_loss, sample_id, sample_content)
VALUES
  (8.9, 1.03, 24, 10.1, 200000, 0.5),
  (7.2, 1.03, 16, 15, 200001, 2);

INSERT INTO rheology (test_id, temp, rpm600, rpm300, rpm200, rpm100, rpm60, rpm30, rpm6, rpm3) VALUES
    (10000013, 50, 45, 31, 25, 19, NULL, NULL, 5, 4),
    (10000014, 50, 33, 21, 12, 10, NULL, NULL, 3, 2);

INSERT INTO gels(test_id, temp, after10s, after60s, after600s) VALUES
    (10000014, 50, 4, 8, 15),
    (10000013, 50, 5, 10, 21);

INSERT INTO basic_powder_tests( sample_id, sg, bulk_sg, wetness, appearence, water_soluble) VALUES
  (200000, 1500, 800, 5.6, 'some polymer', TRUE ),
  (200001, 1800, 450, 8, 'some sort of ligno', TRUE );