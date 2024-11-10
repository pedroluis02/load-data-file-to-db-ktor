create table customer(
    id bigint(11) not null auto_increment,
    code varchar(25) not null,
    name varchar(100) not null,
    last_name varchar(100),
    company varchar(100),
    city varchar(50),
    country varchar(50),
    phone varchar(30),
    phone2 varchar(30),
    email varchar(100),
    subscription_date date,
    website varchar(200),
    created_at datetime not null default current_timestamp,
    updated_at datetime not null default current_timestamp,
    primary key (id),
    unique key customer_code_index (code)
)