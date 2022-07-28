insert into users (id, first_name, last_name, balance) VALUES (5000, 'For', 'Test', 10000);
insert into users (id, first_name, last_name, balance) VALUES (5001, 'Ivan', 'Temnogorov', 300);
insert into users (id, first_name, last_name, balance) VALUES (5002, 'For', 'Test', 10000);

insert into fin_operations (id, date, operation, amount, user_id) values (5100, '2022-07-05', 'withdraw', 1000, 5000);
insert into fin_operations (id, date, operation, amount, user_id) values (5101, '2022-07-06', 'deposit', 1000, 5000);
insert into fin_operations (id, date, operation, amount, user_id, recipient_id)
    values (5102, '2022-07-07', 'transfer', 1000, 5001, 5000);