ALTER TABLE pacientes
    RENAME TO patients;

ALTER TABLE patients
    CHANGE COLUMN nome name varchar(100) not null;

ALTER TABLE patients
    CHANGE COLUMN email email varchar(100) not null unique;

ALTER TABLE patients
    CHANGE COLUMN cpf cpf varchar(14) not null unique;

ALTER TABLE patients
    CHANGE COLUMN logradouro address varchar(100) not null;

ALTER TABLE patients
    CHANGE COLUMN bairro neighborhood varchar(100) not null;

ALTER TABLE patients
    CHANGE COLUMN cep zip_code varchar(9) not null;

ALTER TABLE patients
    CHANGE COLUMN complemento complement varchar(100);

ALTER TABLE patients
    CHANGE COLUMN numero number varchar(20);

ALTER TABLE patients
    CHANGE COLUMN uf state char(2) not null;

ALTER TABLE patients
    CHANGE COLUMN cidade city varchar(100) not null;

ALTER TABLE patients
    CHANGE COLUMN telefone phone varchar(20) not null;

ALTER TABLE patients
    CHANGE COLUMN ativo active tinyint not null;
