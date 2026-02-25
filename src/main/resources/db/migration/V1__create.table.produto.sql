CREATE TABLE produto(

    id bigint not null auto_increment,
    item varchar(100) not null,
    marca varchar(100) not null,
    preco varchar(11) not null,
    categoria varchar(100) not null,
    estoque varchar(100) not null,
    nome varchar(100) not null,
    cnpj varchar(100) not null,
    telefone varchar(100) not null,


    primary key(id)
);