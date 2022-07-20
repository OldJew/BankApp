alter table fin_operations add column recipient_id int8;

alter table fin_operations add constraint FK_fin_operations_recipient_id_to_users foreign key (user_id)
        references "users";