drop table products;
create table if not exists products (
                                        id SERIAL NOT NULL PRIMARY KEY,
                                        name varchar NOT NULL,
                                        type varchar(25) not null,
                                        weight decimal,
                                        cost decimal
);
INSERT INTO products (id, name, type, weight, cost) VALUES
(1,'Помидоры','Пищевая',1.0,99.50),
(2,'Огурцы','Пищевая',1.0,59.50),
(3,'Fairy','Бытовая',1.0,60.70),
(4,'Fairy','Бытовая',2.0,110.0);