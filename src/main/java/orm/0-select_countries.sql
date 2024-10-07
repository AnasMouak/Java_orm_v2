-- Create countries table in hbtn_0e_0_africa with some data
CREATE DATABASE IF NOT EXISTS hbtn_0e_0_africa;
USE hbtn_0e_0_africa;
CREATE TABLE IF NOT EXISTS countries ( 
    id INT NOT NULL AUTO_INCREMENT, 
    name VARCHAR(256) NOT NULL,
    PRIMARY KEY (id)
);
INSERT INTO countries (name) VALUES ("Morocco"), ("Egypt"), ("Ivory Coast"), ("Ethiopia"), ("Nigeria");