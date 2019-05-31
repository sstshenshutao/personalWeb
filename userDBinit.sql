-- !set default! grammar

-- CREATE TABLE table_name
-- (
--   column1 datatype [ NULL | NOT NULL ],
--   column2 datatype [ NULL | NOT NULL ],
--   ...

--   CONSTRAINT fk_column
--     FOREIGN KEY (column1, column2, ... column_n)
--     REFERENCES parent_table (column1, column2, ... column_n)
--     ON DELETE SET NULL
-- );
-- ------------test part----------------
-- //table user_login:		ok
-- create table user_login
--    (
--     uid int primary key not null,
--     password varchar(255) not null,
--     CONSTRAINT fk_column 
--     	FOREIGN KEY (uid)
--     	REFERENCES user (uid)
--     	on delete cascade
--    );
-- //test
-- insert into user values(1,"a");
-- insert into user values(2,"b");
-- insert into user values(3,"c");
-- insert into user values(4,"d");

-- insert into user_security(uid,username,password,email,handy_number) values(1,"a","passwd1","e1","h1");
-- insert into user_security(uid,username,password,email,handy_number) values(2,"b","passwd2","e2","h2");
-- insert into user_security(uid,username,password,email,handy_number) values(3,"c","passwd3","e3","h3");
-- insert into user_security(uid,username,password,email,handy_number) values(4,"d","passwd4","e4","h4");

-- insert into user_login values(1,1,1,1,1);
-- insert into user_login values(2,0,0,0,0);
-- insert into user_login values(3,0,0,0,0);
-- insert into user_login values(4,0,0,0,0);
-- --------------test part end--------------




-- ---------------instructions:--------------
-- check the current time_zone and the session time_zone
SET GLOBAL time_zone ="+02:00";
SELECT @@global.time_zone, @@session.time_zone;
-- build tables
create table user
   (
   uid int unique not null,
   username varchar(20) unique not null,
   primary key (uid,username)
   );


create table user_security
	(
	uid int unique not null,
	username varchar(20) unique not null,
	email varchar(50) unique not null,
	question1 varchar(255) default "",
	answer1 varchar(255) default "",
	question2 varchar(255) default "",
	answer2 varchar(255) default "",
	handy_number varchar(20) unique not null,
	password varchar(255) not null,
	primary key (uid),
    	FOREIGN KEY (uid,username)
    	REFERENCES user (uid,username)
    	on delete cascade
    	on update cascade
	);

create table user_level
	(
	uid int primary key not null,
	manage_level int default 0,
	access_level int default 0,
		FOREIGN KEY (uid)
		REFERENCES user (uid)
		on delete cascade
		on update cascade
	);

create table user_money
	(
	uid int primary key not null,
	virtual_money int default 0,
		FOREIGN KEY (uid)
		REFERENCES user (uid)
		on delete cascade
		on update cascade
	);

create table user_info
	(
	uid int unique not null,
	username varchar(20) unique not null,
	handy_number varchar(255) default "",
 	email varchar(255) default "",
    hobbys varchar(255) default "",
    sex int default 0,
    age int default 0,
    job varchar(255) default "",
    nickname varchar(255) default "",
    homepage varchar(255) default "",
    head_photo BLOB,
    true_name varchar(255) default "",
    residence varchar(255) default "",
    country varchar(255) default "",
    birthday varchar(255) default "",
    primary key (uid),
    	FOREIGN KEY (uid,username)
		REFERENCES user (uid,username)
		on delete cascade
		on update cascade
  	);

create table user_login
(
	uid int,
	username_allow TINYINT(1) default 0,
	email_allow  TINYINT(1) default 0,
	handy_number_allow  TINYINT(1) default 0,
	login_allow  TINYINT(1) default 0,
	primary key (uid),
	FOREIGN KEY (uid)
		REFERENCES user(uid)
	on delete cascade
    on update cascade
);