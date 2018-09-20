create schema bdoo;

create table bdoo.uf (
	id integer not null primary key,
	sigla char(2) not null,
	nome varchar(20) not null
);

create table bdoo.municipio (
	id integer not null primary key,
	nome varchar(30) not null,
	id_uf integer,
	foreign key(id_uf) references bdoo.uf(id) on delete cascade
);

create table bdoo.pessoa(
	id integer not null primary key,
	nome varchar(40) not null,
	id_municipio integer,
	foreign key(id_municipio) references bdoo.municipio(id)
);