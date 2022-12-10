drop table if exists "ITEM" cascade;
drop table if exists "CLIENT" cascade;
drop table if exists "PROVIDER" cascade;
drop table if exists "TYPE" cascade;
drop table if exists "SELLING" cascade;
drop table if exists "DELIVERY" cascade;

CREATE TABLE "ITEM" (
  "id" serial CHECK ("id" >= 0) PRIMARY KEY NOT NULL,
  "name" varchar(255) DEFAULT '',
  "description" varchar(255) DEFAULT '',
  "type" int DEFAULT 0,
  "balance" int DEFAULT 0
) ;

CREATE TABLE "CLIENT"(
	"id" serial CHECK ("id">=0) PRIMARY KEY NOT NULL,
	"name" varchar(255) DEFAULT '',
        "login" varchar(255) UNIQUE DEFAULT '',
        "password" varchar(255) DEFAULT '',
        "role" smallint DEFAULT 0
);

CREATE TABLE "TYPE"(
	"id" serial CHECK ("id">=0) PRIMARY KEY NOT NULL,
	"name" varchar(255) DEFAULT '',
	"description" varchar(255) DEFAULT ''
);

CREATE TABLE "PROVIDER"(
	"id" serial CHECK ("id">=0) PRIMARY KEY NOT NULL,
	"name" varchar(255) DEFAULT ''
);

CREATE TABLE "SELLING"(
	"id" serial CHECK ("id">=0) PRIMARY KEY NOT NULL,
	"item" int CHECK ("item">=0) DEFAULT 0,
	"client" int CHECK ("client">=0) DEFAULT 0,
	"number" int CHECK ("number">=0) DEFAULT 0,
	"date" date DEFAULT NULL,
	"price" int CHECK ("price">=0) DEFAULT 0,
        "status" smallint CHECK ("status">=0) DEFAULT 1
);

CREATE TABLE "DELIVERY"(
	"id" serial CHECK ("id">=0) PRIMARY KEY NOT NULL,
	"item" int CHECK ("item">=0) DEFAULT 0,
	"provider" int CHECK ("provider">=0) DEFAULT 0,
	"datestart" date DEFAULT NULL,
	"dateend" date DEFAULT NULL,
	"number" int CHECK ("number">=0) DEFAULT 0,
	"status" smallint CHECK ("status">=0) DEFAULT 0,
	"price" int CHECK ("price">=0) DEFAULT 0
);