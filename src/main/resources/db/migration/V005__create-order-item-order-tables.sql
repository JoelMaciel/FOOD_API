create table purchase (
    id bigint not null auto_increment,
    subtotal decimal(10,2) not null,
    freight_rate decimal(10,2) not null,
    total_amount decimal(10,2) not null,

    restaurant_id bigint not null,
    user_client_id bigint not null,
    form_payment_id bigint not null,

    address_city_id bigint(20) not null,
    address_zip_code varchar(9) not null,
    address_street varchar(100) not null,
    address_number varchar(20) not null,
    address_complement varchar(60) null,
    address_district varchar(60) not null,

    status varchar(10) not null,
    creation_date datetime not null,
    confirmation_date datetime null,
    cancellation_date datetime null,
    delivery_date datetime null,

    primary key (id),

    constraint fk_purchase_address_city foreign key (address_city_id) references city (id),
    constraint fk_purchase_restaurant foreign key (restaurant_id) references restaurant (id),
    constraint fk_purchase_user_client foreign key (user_client_id) references user (id),
    constraint fk_purchase_form_payment foreign key (form_payment_id) references form_payment (id)
) engine=InnoDB default charset=utf8;

create table purchase_item (
    id bigint not null auto_increment,
    quantity smallint(6) not null,
    unit_price decimal(10,2) not null,
    total_price decimal(10,2) not null,
    observation varchar(255) null,
    purchase_id bigint not null,
    product_id bigint not null,

    primary key (id),
    unique key uk_item_purchase_product (purchase_id, product_id),

    constraint fk_item_purchase_purchase foreign key (purchase_id) references purchase (id),
    constraint fk_item_purchase_product foreign key (product_id) references product (id)
) engine=InnoDB default charset=utf8;