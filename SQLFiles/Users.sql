drop table LINKEDU.USERS;

create table LINKEDU.USERS (
  EMAIL						VARCHAR(50) primary key,
  FIRSTNAME                  VARCHAR(50),
  LASTNAME                  VARCHAR(50),
  PASSWORD					VARCHAR(50)
);

insert into LINKEDU.USERS values
('Dbailey01@gmail.com', 'Douglas','Bailey','0ldsch00l');
