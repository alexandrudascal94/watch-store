drop table if exists discount CASCADE
drop table if exists product CASCADE

create table discount (id bigint not null, price bigint, units integer not null, primary key (id))
create table product (id bigint not null, name varchar(255), price bigint, discount_id bigint default null, primary key (id))
alter table product add constraint product_discount foreign key (discount_id) references discount