use jordan;
drop table if exists card;
create table card (
  id int unsigned not null auto_increment,
  name varchar(40) not null,
  cardType varchar(20) not null,
  color varchar(5) not null,
  expansion varchar(30) not null,
  cIndex int(3) not null,
  primary key(id)
);
