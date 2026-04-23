CREATE TABLE fornecedor(
    id bigint not null auto_increment,
    nome VARCHAR(155),
    cnpj VARCHAR(155),
    telefone VARCHAR(15),

    primary key(id)
);