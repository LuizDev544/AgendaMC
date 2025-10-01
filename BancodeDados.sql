create database agenda_mg;
use agenda_mg;

create table Eventos;

select * from Eventos;

select * from usuarios;

drop table usuarios;

INSERT INTO Eventos 
(nome_evento, descricao_do_evento, data_do_evento, local_do_evento, preco_do_evento, 
capacidade_de_pessoas_no_evento, tipo_do_evento, apresentador_do_evento, duracao_do_evento)
VALUES 
('Show de Rock', 
    'Apresentação da banda RockStars com músicas clássicas e autorais.', 
    '2025-11-15', 
    'Arena São Paulo', 
    150.00, 
    5000, 
    'Show Musical', 
    'Banda RockStars', 
    '3 horas'
);

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'USER') DEFAULT 'USER'
);

INSERT INTO usuarios (email, senha, role)
VALUES ('Admin@gmail.com', '$2a$10$MkOOugHH5rf63pvdrHZL5euEsqKuiLJckZE9Mqm.Ksxg3IFd2ugmq', 'ADMIN');

UPDATE usuarios
SET role = 'ROLE_ADMIN'
WHERE role = 'ADMIN';

UPDATE usuarios SET role = 'ROLE_ADMIN' WHERE email = 'Admin@gmail.com';