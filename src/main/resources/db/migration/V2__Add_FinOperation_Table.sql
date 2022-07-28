create table fin_operations (id  bigserial not null, date date,
                            operation varchar(255) not null,
                            amount numeric(19, 2) not null,
                            user_id int8 not null,
                            primary key (id));

alter table fin_operations add constraint FK_fin_operations_to_users foreign key (user_id) references users;

