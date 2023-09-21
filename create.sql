
DROP TABLE veiculos;
DROP TABLE clientes;

create table veiculos (
    id number generated always as identity PRIMARY KEY,
    marca varchar(200),
    modelo varchar(200),
    ano number,
    valor number(9,2)
);

create table clientes (
    id number generated always as identity PRIMARY KEY,
    nome varchar(200),
    email varchar(200),
    telefone varchar(200)
);