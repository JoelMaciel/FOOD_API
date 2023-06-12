insert into kitchen (id, name) values (1, 'Thai');
insert into kitchen (id, name) values (2, 'Indian');

insert into state (id, name) values  (1, 'Minas Gerais');
insert into state (id, name) values  (2, 'Sao Paulo');
insert into state (id, name) values  (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'Sao Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);

insert into restaurant (id, name, freight_rate, kitchen_id, address_city_id, address_zip_code, address_street, address_number, address_district, address_complement ) values (1, 'Thai Gourmet', 10, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', null);
insert into restaurant (id, name, freight_rate, kitchen_id) values (2,'Thai Delivery', 9.50, 1);
insert into restaurant (id ,name, freight_rate, kitchen_id) values (3,'Tuk Tuk Food Indian', 15, 2);

insert into form_payment (id, description) values (1, 'Credit card');
insert into form_payment (id, description) values (2, 'Debit card');
insert into form_payment (id, description) values (3, 'Money');

insert into permission (id, name, description) values (1, 'CONSULT_KITCHENS', 'Allows consulting kitchens');
insert into permission (id, name, description) values (2, 'EDIT_KITCHENS', 'Allows editing kitchens');

insert into restaurant_form_payment (restaurant_Id, form_payment_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);
