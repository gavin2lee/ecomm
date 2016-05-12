CREATE DATABASE if not exists ecomm DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

create user 'gavin'@'%' identified by '123456';

create table pesudo_domain 
(oid varchar(255), create_at datetime, update_at datetime, name varchar(50), identity_id bigint);



alter table pesudo_domain add constraint pk_oid primary key(oid);

create index pesudo_domain_identity_id on pesudo_domain (identity_id);

create table if not exists weather_conditions (code int not null primary key, label varchar(255));

create table if not exists road_surface (code int not null primary key, label varchar(255));

CREATE TABLE IF NOT EXISTS police_force (code INT NOT NULL PRIMARY KEY, label VARCHAR(255));

CREATE TABLE IF NOT EXISTS light_conditions (code INT NOT NULL PRIMARY KEY, label VARCHAR(255));

CREATE TABLE IF NOT EXISTS district_authority (code INT NOT NULL PRIMARY KEY, label VARCHAR(255));

CREATE TABLE IF NOT EXISTS accident_severity (code INT NOT NULL PRIMARY KEY, label VARCHAR(255));

Accident_Index,
Longitude,
Latitude,
Police_Force,
Accident_Severity,
Number_of_Vehicles,
Number_of_Casualties,
Date,
Day_of_Week,
Time,
Local_Authority_(District),
Light_Conditions,
Weather_Conditions,
Road_Surface_Conditions

CREATE TABLE IF NOT EXISTS accident
(
	accident_index varchar(100) not null primary key,
	longitude decimal(10,2),
	latitude decimal(10,2),
	police_force int,
	accident_severity int,
	number_of_vehicles int,
	number_of_casualties int,
	accident_date date,
	day_of_week int,
	accident_time time,
	local_authority int,
	light_conditions int,
	weather_conditions int,
	road_surface_conditions int
);