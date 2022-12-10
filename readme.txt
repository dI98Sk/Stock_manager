--Сначала создаёшь базу productturnover пустую. После этого выполняешь следующий код:

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


--После создания таблиц заполняем тестовыми данными базу
--Выполняем следующие запросы:

INSERT INTO "TYPE"(name, description) VALUES ('Type ', 'TypeDesc ');

INSERT INTO "CLIENT"(name, login, password, role) VALUES ('Name', 'adminUser', 'f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae', '99') RETURNING id;

INSERT INTO "CLIENT"(name, login, password, role) VALUES ('Name1', 'user', 'f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae', '1') RETURNING id;

INSERT INTO "CLIENT"(name, login, password, role) VALUES ('SalerName', 'saler', 'f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae', '2') RETURNING id;

INSERT INTO "ITEM"(name, description, type, balance) VALUES ('ItemName ', 'Desc', '1', '0') RETURNING id;

INSERT INTO "PROVIDER"(name) VALUES ('ProviderName') RETURNING id;

INSERT INTO "DELIVERY"(item, provider, number, datestart, dateend, status, price) VALUES ('1', '1', '11', 'Sun May 17 18:33:04 MSK 2020', 'Sun May 17 18:33:04 MSK 2020', '1', '143') RETURNING id;

INSERT INTO "SELLING"(item, client, number, date, price, status) VALUES ('1', '1', '3000', 'Sun May 17 18:15:07 MSK 2020', '4400', '1') RETURNING id;

--Теперь ты сможешь входить под административной учетной записью. Login: adminUser Password: 111

--Создан обычный пользователь. Login: user Password: 111