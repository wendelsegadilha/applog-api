CREATE TABLE cliente (
	id bigint not null AUTO_INCREMENT,
    nome varchar(60) not null,
    email varchar(255) not null,
    telefone varchar(20) not null,
    primary key (id)
);