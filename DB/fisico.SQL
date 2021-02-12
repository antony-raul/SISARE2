// Tabelas usadas

CREATE DATABASE sisaredb;

CREATE TABLE user (
id INTEGER PRIMARY KEY,
userName VARCHAR(100),
pass VARCHAR(7)
);

CREATE TABLE funcionario (
matricula INTEGER PRIMARY KEY,
nome VARCHAR(100),
senha VARCHAR(7)
);

CREATE TABLE aluno (
matricula INTEGER PRIMARY KEY,
nome VARCHAR(100),
rua VARCHAR(100),
curso VARCHAR(100),
numero INTEGER,
ativo BOOL
);

CREATE TABLE itens_locacao (
id INTEGER PRIMARY KEY,
nome VARCHAR(100),
quantidade INTEGER,
status BOOL
);

CREATE TABLE emprestimo (
id INTEGER PRIMARY KEY,
status BOOLEAN,
data_emprestimo DATE,
data_devolucao DATE,
id_resp_fk INTEGER,
matricula_func_fk INTEGER(4),
id_item_loc INTEGER,
id_espacos_loc INTEGER,
FOREIGN KEY(id_resp_fk) REFERENCES aluno (matricula),
FOREIGN KEY(matricula_func_FK) REFERENCES funcionario (matricula),
FOREIGN KEY(id_item_loc) REFERENCES itens_locacao (id),
FOREIGN KEY(id_espacos_loc) REFERENCES espacos_locacao (id)
);

CREATE TABLE espacos_locacao (
id INTEGER PRIMARY KEY,
nome VARCHAR(100),
status BOOL,
);




// Tabelas não usadas


CREATE TABLE tipo_item (
tipo INTEGER PRIMARY KEY,
nome VARCHAR()
);

CREATE TABLE email_aluno (
email VARCHAR(100) PRIMARY KEY,
matricula_fk INTEGER,
FOREIGN KEY(matricula_fk) REFERENCES funcionario (matricula)
);

CREATE TABLE publico_externo (
numero_documento VARCHAR(10) PRIMARY KEY,
id_resp INTEGER
FOREIGN KEY(id_resp_fk) REFERENCES responsavel (id_resp)
);

CREATE TABLE responsavel (
nome VARCHAR(100),
id_resp INTEGER PRIMARY KEY
);

CREATE TABLE telefone (
telefone VARCHAR(11) PRIMARY KEY,
matricula_fk INTEGER,
FOREIGN KEY(matricula_fk) REFERENCES funcionario (matricula)
);

CREATE TABLE email_func (
email VARCHAR(100) PRIMARY KEY,
matricula_fk INTEGER,
FOREIGN KEY(matricula_fk) REFERENCES funcionario (matricula)
);