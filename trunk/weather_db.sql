-- ** NB! Don't create db by running script from MS Windows cmd - it gives problems with encoding...
-- DROP DATABASE IF EXISTS weather_db;
-- CREATE DATABASE weather_db ENCODING='UTF8' CONNECTION LIMIT = -1;
-- \c weather_db

-- Table that contains weather information for different cities
CREATE TABLE weather_info
(
  weather_id serial NOT NULL,
  weather_type_id integer NOT NULL,
  min_temp integer NOT NULL,
  max_temp integer NOT NULL,
  wind integer,
  description text,
  date date NOT NULL,
  city_id integer NOT NULL,
  day_part_id integer,
  CONSTRAINT pk_weather_id PRIMARY KEY (weather_id)
);

-- Table containing list of different types of weather (i.e. 'sunny', 'rainy' etc)
CREATE TABLE weather_types
(
  weather_type_id serial NOT NULL,
  weather_type varchar NOT NULL,
  CONSTRAINT pk_weather_types PRIMARY KEY (weather_type_id),
  CONSTRAINT uq_weather_types UNIQUE (weather_type)
);

-- List of cities
CREATE TABLE cities
(
  city_id serial NOT NULL,
  city_name varchar NOT NULL,
  CONSTRAINT pk_cities PRIMARY KEY (city_id)
);

-- Table containing definitions of different parts of day
-- It's intuitive to start from "Night", assigning "1" to its id and s.o.
CREATE TABLE day_parts
(
  day_part_id integer,
  day_part_name varchar,
  CONSTRAINT pk_day_parts PRIMARY KEY (day_part_id)
);

INSERT INTO day_parts (day_part_id, day_part_name) VALUES (1, 'night');
INSERT INTO day_parts (day_part_id, day_part_name) VALUES (2, 'morning');
INSERT INTO day_parts (day_part_id, day_part_name) VALUES (3, 'afternoon');
INSERT INTO day_parts (day_part_id, day_part_name) VALUES (4, 'evening');

INSERT INTO cities VALUES (DEFAULT, 'Tallinn');
INSERT INTO cities VALUES (DEFAULT, 'Narva');
INSERT INTO cities VALUES (DEFAULT, 'Tartu');
INSERT INTO cities VALUES (DEFAULT, 'Pärnu');
INSERT INTO cities VALUES (DEFAULT, 'Keila');

INSERT INTO weather_types (weather_type) VALUES ('sunny');
INSERT INTO weather_types (weather_type) VALUES ('rainy');
INSERT INTO weather_types (weather_type) VALUES ('windy');
INSERT INTO weather_types (weather_type) VALUES ('dry');
INSERT INTO weather_types (weather_type) VALUES ('light wind');
INSERT INTO weather_types (weather_type) VALUES ('stormy');
INSERT INTO weather_types (weather_type) VALUES ('sunny and dry');
INSERT INTO weather_types (weather_type) VALUES ('warm');
INSERT INTO weather_types (weather_type) VALUES ('flurries');



INSERT INTO weather_info (weather_type_id, min_temp, max_temp, wind, description, date, city_id, day_part_id)
				VALUES (2, 0, 5, 10, 'Rainy weather ...', '2012-04-01', 1, 1);
INSERT INTO weather_info (weather_type_id, min_temp, max_temp, wind, description, date, city_id, day_part_id)
				VALUES (1, 0, 6, 13, 'Sunny, dry weather ...', '2012-04-01', 1, 2);
INSERT INTO weather_info (weather_type_id, min_temp, max_temp, wind, description, date, city_id, day_part_id)
				VALUES (1, 0, 8, 15, 'Sunny, dry weather ...', '2012-04-01', 1, 3);
INSERT INTO weather_info (weather_type_id, min_temp, max_temp, wind, description, date, city_id, day_part_id)
				VALUES (1, 0, 5, 12, 'Sunny, dry weather ...', '2012-04-01', 1, 4);
INSERT INTO weather_info (weather_type_id, min_temp, max_temp, wind, description, date, city_id, day_part_id)
				VALUES (5, 0, 4, 8, 'The weather is ...', '2012-04-02', 1, 1);
INSERT INTO weather_info (weather_type_id, min_temp, max_temp, wind, description, date, city_id, day_part_id)
				VALUES (3, 0, 6, 10, 'Some description here ...', '2012-04-02', 1, 2);
INSERT INTO weather_info (weather_type_id, min_temp, max_temp, wind, description, date, city_id, day_part_id)
				VALUES (3, 0, 9, 14, 'Some description here ...', '2012-04-02', 1, 3);
INSERT INTO weather_info (weather_type_id, min_temp, max_temp, wind, description, date, city_id, day_part_id)
				VALUES (3, 0, 10, 11, 'Some description here ...', '2012-04-02', 1, 4);
INSERT INTO weather_info (weather_type_id, min_temp, max_temp, wind, description, date, city_id, day_part_id)
				VALUES (4, 0, 6, 10, 'Quiet, dry weather ...', '2012-04-03', 1, 1);
INSERT INTO weather_info (weather_type_id, min_temp, max_temp, wind, description, date, city_id, day_part_id)
				VALUES (1, 0, 10, 16, 'Quiet weather ...', '2012-04-03', 1, 2);
INSERT INTO weather_info (weather_type_id, min_temp, max_temp, wind, description, date, city_id, day_part_id)
				VALUES (1, 0, 7, 13, 'Mostly sunny and ...', '2012-04-03', 1, 3);
INSERT INTO weather_info (weather_type_id, min_temp, max_temp, wind, description, date, city_id, day_part_id)
				VALUES (7, 0, 8, 12, 'Mostly ...', '2012-04-03', 4, 4);
				
ALTER TABLE weather_info ADD CONSTRAINT fk_weather_info_ref_cities FOREIGN KEY (city_id)
      REFERENCES cities (city_id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE weather_info ADD CONSTRAINT fk_weather_info_ref_day_parts FOREIGN KEY (day_part_id)
      REFERENCES day_parts (day_part_id);
ALTER TABLE weather_info ADD CONSTRAINT fk_weather_info_ref_weather_types FOREIGN KEY (weather_type_id)
      REFERENCES weather_types (weather_type_id);
