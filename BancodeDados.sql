create database agenda_mg;
use agenda_mg;

select * from eventos;

INSERT INTO eventos (
    id,
    apresentador_do_evento,
    capacidade_de_pessoas_no_evento,
    data_do_evento,
    descricao_do_evento,
    duracao_do_evento,
    local_do_evento,
    nome_evento,
    preco_do_evento,
    tipo_do_evento
) VALUES (
    4,
    'João Silva',
    50,
    '2025-10-10',
    'Palestra sobre tecnologia',
    2,
    'Auditório Central',
    'Tech Day',
    100.00,
    'Palestra'
);