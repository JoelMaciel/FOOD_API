insert into kitchen (id, name) values (1, 'Thai');
insert into kitchen (id, name) values (2, 'Indian');
insert into kitchen (id, name) values (3, 'Argentina');
insert into kitchen (id, name) values (4, 'Brazilian');

insert into state (id, name) values  (1, 'Minas Gerais');
insert into state (id, name) values  (2, 'Sao Paulo');
insert into state (id, name) values  (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'Sao Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);

insert into restaurant (id, name, freight_rate, kitchen_id, creation_date, update_date, address_city_id, address_zip_code, address_street, address_number, address_district, address_complement ) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro', null);
insert into restaurant (id, name, freight_rate, kitchen_id, creation_date, update_date) values (2,'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurant (id ,name, freight_rate, kitchen_id, creation_date, update_date) values (3,'Tuk Tuk Food Indian', 15, 2, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, freight_rate, kitchen_id, creation_date, update_date) values (4,'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp);
insert into restaurant (id ,name, freight_rate, kitchen_id, creation_date, update_date) values (5,'Uncle Sams Diner', 11, 4, utc_timestamp, utc_timestamp);
insert into restaurant (id ,name, freight_rate, kitchen_id, creation_date, update_date) values (6, 'Marias bar', 6, 4, utc_timestamp, utc_timestamp);

insert into form_payment (id, description) values (1, 'Credit card');
insert into form_payment (id, description) values (2, 'Debit card');
insert into form_payment (id, description) values (3, 'Money');

insert into permission (id, name, description) values (1, 'CONSULT_KITCHENS', 'Allows consulting kitchens');
insert into permission (id, name, description) values (2, 'EDIT_KITCHENS', 'Allows editing kitchens');

insert into restaurant_form_payment (restaurant_Id, form_payment_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into product (name, description, price, active, restaurant_Id) values ('Pork with sweet and sour sauce', 'Delicious pork with special sauce', 78.90, 1, 1);
insert into product (name, description, price, active, restaurant_Id) values ('Thai shrimp', '16 large shrimp in hot sauce', 110, 1, 1);
insert into product (name, description, price, active, restaurant_Id) values ('Spicy salad with grilled meat', 'Salad leaves with thinly sliced grilled beef and our special red pepper sauce', 87.20, 1, 2) ;
insert into product (name, description, price, active, restaurant_Id) values ('Garlic Naan', 'Traditional Indian bread with garlic topping', 21, 1, 3);
insert into product (name, description, price, active, restaurant_Id) values ('Murg Curry', 'Chicken cubes prepared with curry sauce and spices', 43, 1, 3);
insert into product (name, description, price, active, restaurant_Id) values ('Bife Ancho', 'Tender and juicy cut, two fingers thick, taken from the front of the sirloin steak', 79, 1, 4);
insert into product (name, description, price, active, restaurant_Id) values ('T-Bone', 'Very tasty cut, with a T-shaped bone, with sirloin steak on one side and filet mignon on the other', 89 , 1, 4);
insert into product (name, description, price, active, restaurant_Id) values ('Sanduíche X-Tudo', 'Sandubão with lots of cheese, beef hamburger, bacon, egg, salad and mayonnaise', 19, 1, 5);
insert into product (name, description, price, active, restaurant_Id) values ('Termite skewer', 'Flour, cassava and vinaigrette', 8, 1, 6);