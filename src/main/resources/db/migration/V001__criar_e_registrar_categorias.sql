CREATE TABLE categoria (
	id bigserial NOT NULL, 
	name VARCHAR(50) NOT NULL,
	CONSTRAINT pk_categoria_id PRIMARY KEY (id)
);

INSERT INTO categoria (name) values ('Lazer');
INSERT INTO categoria (name) values ('Alimentação');
INSERT INTO categoria (name) values ('Supermercado');
INSERT INTO categoria (name) values ('Farmácia');
INSERT INTO categoria (name) values ('Outros');