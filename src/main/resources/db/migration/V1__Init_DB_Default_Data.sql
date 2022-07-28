
create table users (id  bigserial not null,
                    balance numeric(19, 2),
                    first_name varchar(255),
                    last_name varchar(255),
                    primary key (id));

insert into users (first_name, last_name, balance) VALUES ('Test', 'Testerov', 10000);
insert into users (first_name, last_name, balance) VALUES ('Foo', 'Bar', 100);
insert into users (first_name, last_name, balance) VALUES ('Ivan', 'Petrov', 5000);
insert into users (first_name, last_name, balance) VALUES ('Petr', 'Ivanov', 7000);
insert into users (first_name, last_name, balance) VALUES ('John', 'Dow', 5000);
insert into users (first_name, last_name, balance) VALUES ('Isaac', 'Asimov', 50000);
insert into users (first_name, last_name, balance) VALUES ('Caliban', 'Robot', 0);
