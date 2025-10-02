create database agenda_mg;
use agenda_mg;

create table Eventos;

select * from Eventos;

select * from admin;

drop table usuarios;

drop table admin;

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

INSERT INTO admin (email, senha, nome) VALUES
('admin@agendamg.com', '$2a$12$WYdbsGjlIIl2ja9qxVQgE.nz3.blEyFcp5OgXPovHh7hXTMjir/JW', 'Administrador Principal');

create table IF NOT EXISTS admin (
    id int auto_increment primary key,
    email varchar(255) not null unique,
    senha varchar(255) not null,
    nome varchar(255) not null,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo BOOLEAN DEFAULT TRUE,
    
    INDEX idx_email (email)
);
