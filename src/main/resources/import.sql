insert into kitchen (id, name) values (1, 'Thai');
insert into kitchen (id, name) values (2, 'Indian');

insert into restaurant (name, freight_rate, kitchen_id) values ('Thai Gourmet', 10, 1);
insert into restaurant (name, freight_rate, kitchen_id) values ('Thai Delivery', 9.50, 1);
insert into restaurant (name, freight_rate, kitchen_id) values ('Tuk Tuk Food Indian', 15, 2);


insert into state (id, name) values  (1, 'Minas Gerais');
insert into state (id, name) values   (2, 'Sao Paulo');
insert into state (id, name) values  (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'Sao Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values  (5, 'Fortaleza', 3);

insert into form_payment (id, description) values (1, 'Credit card');
insert into form_payment (id, description) values  (2, 'Debit card');
insert into form_payment (id, description) values  (3, 'Money');

insert into permission (id, name, description) values (1, 'CONSULT_KITCHENS', 'Allows consulting kitchens');
insert into permission (id, name, description) values (2, 'EDIT_KITCHENS', 'Allows editing kitchens');