DROP TABLE IF EXISTS users;
create table users (
    username varchar(50) not null primary key,
    password varchar(120) not null,
    userrole varchar(120) not null
);

--insert into users (username, password, role) values 
--('admin', 'admin','ADMIN');