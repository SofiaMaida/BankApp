create database BankRota;
use BankRota;

create table Person (
id int not null auto_increment,
name varchar(100) not null,
last_name varchar(100) not null,
number_doc int not null,
documentation_id int not null,

primary key(id),
foreign key(documentation_id) references Documentation(id)
);

create table Documentation (
id int not null auto_increment,
documentation_type varchar(45) not null,

primary key(id)
);

create table Contact (
id int not null auto_increment,
number_phone int not null,
person_id int not null,

primary key(id),
foreign key(person_id) references Person(id)
);

create table Account (
id int not null auto_increment,
number_account varchar (100) not null,
balance int,
person_id int not null,
type_account_id int not null,

primary key(id),
foreign key(person_id) references Person(id),
foreign key(type_account_id) references Type_account(id)
);

create table Type_account (
id int not null auto_increment,
type_account varchar (45) not null,

primary key(id)
);


create table Movements (
id int not null auto_increment,
move_date datetime not null,
amount int,
description varchar(200) not null,
account_id int not null,
type_movements_id int not null,

primary key(id),
foreign key(account_id) references Account(id),
foreign key(type_movements_id) references Type_movements(id)
);

create table Type_movements (
id int not null auto_increment,
type_move varchar(45) not null,

primary key(id)
);

insert into Person (id, name, last_name, number_doc, documentation_id) values (1, "Maria", "Gomez", "20702084", 1);
insert into Person (id, name, last_name, number_doc, documentation_id) values (2, "Eduardo", "Lopez", "37952834", 2);

insert into Documentation (id, documentation_type) values (1, "DNI");
insert into Documentation (id, documentation_type) values (2, "Pasaporte");
insert into Documentation (id, documentation_type) values (3, "CUIL");
insert into Documentation (id, documentation_type) values (4, "Extranjero");

/*insert into Contact (id, number_phone, person_id) values (1, 1127304256, 1);
insert into Contact (id, number_phone, person_id) values (2, 1156541758, 2);

insert into Account (id, number_account, balance, person_id, type_account_id) values (1, "AR AR25 0064 0482 25 536398", 1, 1, 1);
insert into Account (id, number_account, balance, person_id, type_account_id) values (2, "EU AR25 0064 0482 25 536397", 2, 2, 2);
insert into Account (id, number_account, balance, person_id, type_account_id) values (3, "DO AR25 0064 0482 25 536396", 3, 3, 3);*/

insert into Type_account (id, type_account) values (1, "Cuenta corriente PESOS");
insert into Type_account (id, type_account) values (2, "Cuenta corriente EURO");
insert into Type_account (id, type_account) values (3, "Cuenta corriente DOLAR");

insert into Movements (id, move_date, amount, description, account_id, type_movements_id) values (1, 21/02/2020, 500, "Compra en Carrefour", 1, 1);
insert into Movements (id, move_date, amount, description, account_id, type_movements_id) values (2, 15/01/2020, 1200, "Falabella", 2, 2);

insert into Type_movements (id, type_move) values (1, "Débito");
insert into Type_movements (id, type_move) values (2, "Crédito");

/*insert into Transaction (id, amount, movements_id) values (1, 40000, 1);
insert into Transaction (id, amount, movements_id) values (2, 30000, 2);*/