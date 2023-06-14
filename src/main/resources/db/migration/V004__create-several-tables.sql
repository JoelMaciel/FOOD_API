create table cluster (
	id bigint not null auto_increment,
    name varchar(60) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;

create table cluster_permission (
	cluster_id bigint not null,
    permission_id bigint not null,

    primary key (cluster_id, permission_id)
) engine=InnoDB default charset=utf8;

create table form_payment (
	id bigint not null auto_increment,
    description varchar(60) not null,

    primary key (id)
  ) engine=InnoDB default charset=utf8;


create table permission (
	id bigint not null auto_increment,
	name varchar(60) not null,
    description varchar(80) not null,

   primary key (id)
  ) engine=InnoDB default charset=utf8;

create table product (
	id bigint not null auto_increment,
	restaurant_id bigint not null,
	name varchar(60) not null,
    description text not null,
    price decimal(10,2) not null,
	active tinyint(1) not null,

    primary key (id)
 ) engine=InnoDB default charset=utf8;

create table restaurant (
	id bigint not null auto_increment,
	kitchen_id bigint not null,
    name varchar(60) not null,
    freight_rate decimal(19,2) not null,
    creation_date datetime not null,
    update_date datetime not null,

    address_complement varchar(80),
    address_district varchar(80),
    address_number varchar(20),
    address_street varchar(60),
    address_zip_code varchar(9),
    address_city_id bigint,

    primary key (id)
 ) engine=InnoDB default charset=utf8;

create table restaurant_form_payment (
 restaurant_id bigint not null,
 form_payment_id bigint not null,

 primary key (restaurant_id, form_payment_id)
) engine=InnoDB default charset=utf8;

create table user (
	id bigint not null auto_increment,
    creation_date datetime not null,
    email varchar(80) not null,
    name varchar(255) not null,
    password varchar(255) not null,

    primary key (id)
  ) engine=InnoDB default charset=utf8;

create table user_cluster (
	user_id bigint not null,
    cluster_id bigint not null
 ) engine=InnoDB default charset=utf8;


alter table cluster_permission add constraint FK_cluster_permission_permission
foreign key (permission_id) references permission (id);

alter table cluster_permission add constraint FK_cluster_permission_cluster
foreign key (cluster_id) references cluster (id);

alter table product add constraint FK_product_restaurant
foreign key (restaurant_id) references restaurant (id);

alter table restaurant add constraint FK_restaurant_city
foreign key (address_city_id) references city (id);

alter table restaurant add constraint FK_restaurant_kitchen
foreign key (kitchen_id) references kitchen (id);

alter table restaurant_form_payment add constraint FK_form_payment_restaurant
foreign key (form_payment_id) references form_payment (id);

alter table restaurant_form_payment add constraint FK_restaurant_form_payment
foreign key (restaurant_id) references restaurant (id);

alter table user_cluster add constraint FK_cluster_user
foreign key (cluster_id) references cluster (id);

alter table user_cluster add constraint FK_user_cluster
foreign key (user_id) references user (id);