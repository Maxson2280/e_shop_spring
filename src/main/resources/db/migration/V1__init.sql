SET search_path = boot;
-- 1 добавить поле name в авторизацию и бд - и авторизовыватся по имени +
-- 2 создать файл миграции - второй, в нем сделать создание товаров и вывод их на страницу с товарами +
CREATE TABLE products (
                          id serial,
                          title varchar(100),
                          price int,
                          quantity int,
                          sold int,
                          view int,
                          PRIMARY KEY (id)
);

CREATE TABLE users (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled boolean NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE authorities (
    username varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,
    CONSTRAINT authorities_idx UNIQUE (username, authority),
    CONSTRAINT authorities_ibfk_1
    FOREIGN KEY (username)
    REFERENCES users (username)
);

insert into users values ('admin', '{noop}admin','вася', true);
insert into users values ('user', '{noop}user', 'колян', true);


insert into authorities values ('admin', 'ROLE_ADMIN');
insert into authorities values ('admin', 'ROLE_USER');
insert into authorities values ('user', 'ROLE_USER');
