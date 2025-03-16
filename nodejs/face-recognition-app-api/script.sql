-- create database face-recognition-app;
-- \c face-recognition-app;

drop table if exists login;
create table login(
    id serial primary key,
    hash varchar(100) not null,
    email text not null
);

drop table if exists users;
create table users(
	id serial primary key,
	name varchar(100) not null,
	email text unique not null,
	entries bigint default 0,
	joined timestamp default current_timestamp
);

insert into users(name, email, password)
values('test', 'test@gmail.com', '$2a$10$ir7NEq60R.YwrtqZwtV6SuS/PHp7LYmcbUOdet4Jhpo5ovME8GnrS');

-- select * from users order by id;