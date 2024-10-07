-- Create countries table in hbtn_0e_14_africa with some data
CREATE DATABASE IF NOT EXISTS hbtn_0e_14_africa;
USE hbtn_0e_14_africa;
CREATE TABLE IF NOT EXISTS countries ( 
    id INT NOT NULL AUTO_INCREMENT, 
    name VARCHAR(256) NOT NULL,
    PRIMARY KEY (id)
);
INSERT INTO countries (name) VALUES ("Morocco"), ("Egypt"), ("Ivory Coast"), ("Ethiopia"), ("Nigeria");

CREATE TABLE IF NOT EXISTS cities ( 
    id INT NOT NULL AUTO_INCREMENT, 
    country_id INT NOT NULL,
    name VARCHAR(256) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(country_id) REFERENCES countries(id)
);
INSERT INTO cities (country_id, name) VALUES (1, "Rabat"), (1, "Casablanca"), (1, "Tangier"), (1, "Fes"), (1, "Marrakech");
INSERT INTO cities (country_id, name) VALUES (2, "Cairo"), (2, "Alexandria");
INSERT INTO cities (country_id, name) VALUES (3, "Abidjan"), (3, "Abobo"), (3, "Bouake");
INSERT INTO cities (country_id, name) VALUES (4, "Addis Ababa");
INSERT INTO cities (country_id, name) VALUES (5, "Lagos"), (5, "Kano"), (5, "Ibadan"), (5, "Kaduna");