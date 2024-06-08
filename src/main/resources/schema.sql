CREATE TABLE IF NOT EXISTS Cidade (
                        id SERIAL PRIMARY KEY,
                        nome VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Cliente (
                         id SERIAL PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         cpf VARCHAR(11) NOT NULL,
                         DATA_NASCIMENTO DATE,
                         email VARCHAR(255),
                         contato BIGINT
);

CREATE TABLE IF NOT EXISTS Empresa (
                         id SERIAL PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         cnpj VARCHAR(14) NOT NULL,
                         email VARCHAR(255),
                         contato VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS LOCALIZACAO_VEICULO (
                                     id SERIAL PRIMARY KEY,
                                     latitude DOUBLE PRECISION NOT NULL,
                                     longitude DOUBLE PRECISION NOT NULL,
                                     atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Veiculo (
                         ID SERIAL PRIMARY KEY,
                         placa VARCHAR(255) NOT NULL,
                         modelo VARCHAR(255) NOT NULL,
                         ano INTEGER NOT NULL,
                         TIPO VARCHAR(255) NOT NULL,
                         ID_EMPRESA BIGINT,
                         ID_LOCALIZACAO BIGINT,
                         CONSTRAINT fk_empresa FOREIGN KEY (ID_EMPRESA) REFERENCES Empresa (id),
                         CONSTRAINT fk_localizacao FOREIGN KEY (ID_LOCALIZACAO) REFERENCES LOCALIZACAO_VEICULO (id)
);

CREATE TABLE IF NOT EXISTS Rota (
                      id SERIAL PRIMARY KEY,
                      LOCAL_PARTIDA_ID BIGINT,
                      LOCAL_CHEGADA_ID BIGINT,
                      CONSTRAINT fk_local_partida FOREIGN KEY (LOCAL_PARTIDA_ID) REFERENCES Cidade (id),
                      CONSTRAINT fk_local_chegada FOREIGN KEY (LOCAL_CHEGADA_ID) REFERENCES Cidade (id)
);

CREATE TABLE IF NOT EXISTS ROTA_CIDADE (
                             ID_ROTA BIGINT,
                             ID_CIDADE BIGINT,
                             CONSTRAINT fk_rota FOREIGN KEY (ID_ROTA) REFERENCES Rota (id),
                             CONSTRAINT fk_cidade FOREIGN KEY (ID_CIDADE) REFERENCES Cidade (id)
);


CREATE TABLE IF NOT EXISTS VEICULO_ROTA (
                              id SERIAL PRIMARY KEY,
                              ID_VEICULO BIGINT,
                              ID_ROTA BIGINT,
                              DIAS_SEMANA VARCHAR(255),
                              HORA_SAIDA TIME,
                              CONSTRAINT fk_veiculo FOREIGN KEY (ID_VEICULO) REFERENCES Veiculo (ID),
                              CONSTRAINT fk_rota FOREIGN KEY (ID_ROTA) REFERENCES Rota (id)
);


CREATE TABLE IF NOT EXISTS Usuario (
                         id SERIAL PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         senha VARCHAR(255) NOT NULL,
                         data_cadastro DATE
);

CREATE TABLE IF NOT EXISTS Pagamento (
                           id SERIAL PRIMARY KEY,
                           NOME_CARTAO VARCHAR(255),
                           NUMERO_CARTAO BIGINT,
                           VALIDADE_CARTAO VARCHAR(255),
                           cvv INTEGER,
                           CHAVE_PIX VARCHAR(255),
                           FORMA_PAGAMENTO VARCHAR(255),
                           STATUS_PAGAMENTO VARCHAR(255),
                           VALOR NUMERIC(19,2)
);

CREATE TABLE IF NOT EXISTS Passagem (
                          id SERIAL PRIMARY KEY,
                          id_cliente INTEGER NOT NULL REFERENCES Cliente(id),
                          id_veiculo_rota INTEGER NOT NULL REFERENCES VEICULO_ROTA(id),
                          quantidade INTEGER NOT NULL,
                          data_hora_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          data_viagem DATE NOT NULL,
                          id_pagamento INTEGER UNIQUE REFERENCES Pagamento(id)
);


CREATE TABLE IF NOT EXISTS Ticket (
                        id VARCHAR(255) PRIMARY KEY,
                        status VARCHAR(255),
                        ID_PASSAGEM BIGINT,
                        CONSTRAINT fk_passagem FOREIGN KEY (ID_PASSAGEM) REFERENCES Passagem (id)
);

CREATE TABLE IF NOT EXISTS PRECO_PASSAGEM (
                                id SERIAL PRIMARY KEY,
                                LOCAL_PARTIDA BIGINT,
                                LOCAL_CHEGADA BIGINT,
                                preco NUMERIC(19,2),
                                DURACAO_VIAGEM DOUBLE PRECISION,
                                CONSTRAINT fk_local_partida FOREIGN KEY (LOCAL_PARTIDA) REFERENCES Cidade (id),
                                CONSTRAINT fk_local_chegada FOREIGN KEY (LOCAL_CHEGADA) REFERENCES Cidade (id)
);


