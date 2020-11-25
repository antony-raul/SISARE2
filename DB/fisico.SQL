CREATE DATABASE sisaredb

CREATE TABLE funcionario (
matricula INTEGER PRIMARY KEY,
nome VARCHAR(100),
rua VARCHAR(100),
numero INTEGER,
ativo BOOL
)

CREATE TABLE email_func (
email VARCHAR(100) PRIMARY KEY,
matricula_fk INTEGER,
FOREIGN KEY(matricula_fk) REFERENCES funcionario (matricula)
)

CREATE TABLE telefone (
telefone VARCHAR(11) PRIMARY KEY,
matricula_fk INTEGER,
FOREIGN KEY(matricula_fk) REFERENCES funcionario (matricula)
)

CREATE TABLE responsavel (
nome VARCHAR(100),
id_resp INTEGER PRIMARY KEY
)

CREATE TABLE aluno (
matricula INTEGER PRIMARY KEY,
id_resp_fk INTEGER
FOREIGN KEY(id_resp_fk) REFERENCES responsavel (id_resp)
)

CREATE TABLE publico_externo (
numero_documento VARCHAR(10) PRIMARY KEY,
id_resp INTEGER
FOREIGN KEY(id_resp_fk) REFERENCES responsavel (id_resp)
)

CREATE TABLE email_aluno (
email VARCHAR(100) PRIMARY KEY,
matricula_fk INTEGER,
FOREIGN KEY(matricula_fk) REFERENCES funcionario (matricula)
)



CREATE TABLE tipo_item (
tipo INTEGER PRIMARY KEY,
nome VARCHAR()
)

CREATE TABLE itens_locacao (
id INTEGER PRIMARY KEY,
nome VARCHAR(100),
quantidade INTEGER,
status BOOL,
ativo BOOL,
tipo_fk INTEGER
FOREIGN KEY(tipo_fk) REFERENCES tipo_item (tipo)
)

CREATE TABLE emprestimo (
situacao VARCHAR(100),
sata_emprestimo DATETIME,
hora_devolucao DATETIME,
hora_emprestimo DATETIME,
id_resp_fk INTEGER,
matricula_func_fk Número(4),
id_item_loc INTEGER,
FOREIGN KEY(id_resp_fk) REFERENCES responsavel (id_resp)
FOREIGN KEY(matricula_func_FK) REFERENCES funcionario (matricula)
FOREIGN KEY(id_item_loc) REFERENCES itens_locacao (id)
)

