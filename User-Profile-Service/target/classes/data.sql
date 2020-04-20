DROP TABLE IF EXISTS userdetail;
CREATE TABLE userdetail (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_Name VARCHAR(250) NOT NULL,
  last_Name VARCHAR(250) NOT NULL,
  age int,
  address VARCHAR(250),
  mobile_Number NUMBER
);

insert into userdetail(first_name, last_name, age, address, mobile_number) values 
('Arun', 'bhardwaj', 32, 'IaaaT', 333);