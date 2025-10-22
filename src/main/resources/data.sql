-- Seed data for api-busvans executed by Spring Boot after JPA (dev profile)
-- Requires:
--   spring.jpa.defer-datasource-initialization=true
--   spring.sql.init.mode=always

SET NAMES utf8mb4;
SET time_zone = '+00:00';

START TRANSACTION;

-- Optional: clean slate on each startup to avoid duplicates in dev
SET FOREIGN_KEY_CHECKS = 0;
DELETE FROM ticket;
DELETE FROM passagem;
DELETE FROM pagamento;
DELETE FROM cliente;
DELETE FROM veiculo_rota;
DELETE FROM preco_passagem;
DELETE FROM rota_cidade;
DELETE FROM rota;
DELETE FROM localizacao_veiculo;
DELETE FROM veiculo;
DELETE FROM empresa;
DELETE FROM cidade;
DELETE FROM usuario;
SET FOREIGN_KEY_CHECKS = 1;

-- 1) Cidades
INSERT INTO cidade (nome) VALUES
  ('São Paulo'),
  ('Rio de Janeiro'),
  ('Belo Horizonte'),
  ('Campinas');

SET @cidade_sp := (SELECT id FROM cidade WHERE nome = 'São Paulo' LIMIT 1);
SET @cidade_rj := (SELECT id FROM cidade WHERE nome = 'Rio de Janeiro' LIMIT 1);
SET @cidade_bh := (SELECT id FROM cidade WHERE nome = 'Belo Horizonte' LIMIT 1);
SET @cidade_ca := (SELECT id FROM cidade WHERE nome = 'Campinas' LIMIT 1);

-- 2) Empresa
INSERT INTO empresa (nome, cnpj, email, contato) VALUES
  ('Viação M4', '12.345.678/0001-99', 'contato@m4.com', '11999999999');
SET @empresa_m4 := LAST_INSERT_ID();

-- 3) Veículos (tipo: V=Van, O=Ônibus)
INSERT INTO veiculo (placa, modelo, ano, TIPO, ID_EMPRESA) VALUES
  ('ABC-1234', 'Mercedes Sprinter', 2020, 'V', @empresa_m4);
SET @veiculo_van := LAST_INSERT_ID();
INSERT INTO veiculo (placa, modelo, ano, TIPO, ID_EMPRESA) VALUES
  ('XYZ-9876', 'Marcopolo Paradiso', 2018, 'O', @empresa_m4);
SET @veiculo_onibus := LAST_INSERT_ID();

-- 4) Rotas (SP -> RJ) e (RJ -> BH)
INSERT INTO rota (LOCAL_PARTIDA, LOCAL_CHEGADA) VALUES
  (@cidade_sp, @cidade_rj);
SET @rota_sp_rj := LAST_INSERT_ID();
INSERT INTO rota (LOCAL_PARTIDA, LOCAL_CHEGADA) VALUES
  (@cidade_rj, @cidade_bh);
SET @rota_rj_bh := LAST_INSERT_ID();

-- 5) Possíveis cidades de partida/chegada (mesma tabela rota_cidade)
INSERT INTO rota_cidade (ID_ROTA, ID_CIDADE) VALUES
  (@rota_sp_rj, @cidade_sp),  -- partida possível
  (@rota_sp_rj, @cidade_ca),  -- partida alternativa
  (@rota_sp_rj, @cidade_rj),  -- chegada possível
  (@rota_rj_bh, @cidade_rj),  -- partida possível
  (@rota_rj_bh, @cidade_bh);  -- chegada possível

-- 6) Preços de passagem
INSERT INTO preco_passagem (LOCAL_PARTIDA, LOCAL_CHEGADA, preco, DURACAO_VIAGEM) VALUES
  (@cidade_sp, @cidade_rj, 120.00, 6.5),
  (@cidade_rj, @cidade_bh, 150.00, 7.0);

-- 7) Veículo em rota e horários
INSERT INTO veiculo_rota (ID_VEICULO, ID_ROTA, DIAS_SEMANA, HORA_SAIDA) VALUES
  (@veiculo_van, @rota_sp_rj, 'SEG,TER,QUA,QUI,SEX', '08:00:00');
SET @veiculo_rota_1 := LAST_INSERT_ID();
INSERT INTO veiculo_rota (ID_VEICULO, ID_ROTA, DIAS_SEMANA, HORA_SAIDA) VALUES
  (@veiculo_onibus, @rota_rj_bh, 'SEX,SAB,DOM', '22:00:00');
SET @veiculo_rota_2 := LAST_INSERT_ID();

-- 8) Cliente
INSERT INTO cliente (nome, cpf, DATA_NASCIMENTO, email, contato) VALUES
  ('John Doe', '12345678901', '1990-01-01', 'john@doe.com', 11988887777);
SET @cliente_john := LAST_INSERT_ID();

-- 9) Pagamentos (CC = Cartão de Crédito, SUC = Sucesso)
INSERT INTO pagamento (NOME_CARTAO, NUMERO_CARTAO, VALIDADE_CARTAO, cvv, CHAVE_PIX, FORMA_PAGAMENTO, STATUS_PAGAMENTO, VALOR) VALUES
  ('JOHN DOE', 4111111111111111, '12/28', 123, NULL, 'CC', 'SUC', 120.00);
SET @pagamento_cc := LAST_INSERT_ID();
INSERT INTO pagamento (NOME_CARTAO, NUMERO_CARTAO, VALIDADE_CARTAO, cvv, CHAVE_PIX, FORMA_PAGAMENTO, STATUS_PAGAMENTO, VALOR) VALUES
  (NULL, NULL, NULL, NULL, 'chave-pix-john@example.com', 'PX', 'PEN', 150.00);
SET @pagamento_px := LAST_INSERT_ID();

-- 10) Passagens
INSERT INTO passagem (ID_CLIENTE, ID_VEICULO_ROTA, quantidade, DATA_HORA_COMPRA, DATA_VIAGEM, ID_PAGAMENTO) VALUES
  (@cliente_john, @veiculo_rota_1, 1, NOW(), DATE_ADD(CURDATE(), INTERVAL 4 DAY), @pagamento_cc);
SET @passagem_1 := LAST_INSERT_ID();
INSERT INTO passagem (ID_CLIENTE, ID_VEICULO_ROTA, quantidade, DATA_HORA_COMPRA, DATA_VIAGEM, ID_PAGAMENTO) VALUES
  (@cliente_john, @veiculo_rota_2, 2, NOW(), DATE_ADD(CURDATE(), INTERVAL 10 DAY), @pagamento_px);
SET @passagem_2 := LAST_INSERT_ID();

-- 11) Tickets (Status: D = Disponivel)
INSERT INTO ticket (id, status, ID_PASSAGEM) VALUES
  ('TCK-0001', 'D', @passagem_1),
  ('TCK-0002', 'D', @passagem_2),
  ('TCK-0003', 'D', @passagem_2);

-- 12) Localização de veículo (usar UPDATE no veículo para linkar localização)
INSERT INTO localizacao_veiculo (ID_VEICULO, latitude, longitude, atualizacao) VALUES
  (@veiculo_van, -23562890, -46637720, NOW());  -- São Paulo (aprox)
SET @loc_van := LAST_INSERT_ID();
INSERT INTO localizacao_veiculo (ID_VEICULO, latitude, longitude, atualizacao) VALUES
  (@veiculo_onibus, -22806430, -43172800, NOW()); -- Rio de Janeiro (aprox)
SET @loc_onibus := LAST_INSERT_ID();

UPDATE veiculo SET ID_LOCALIZACAO = @loc_van   WHERE id = @veiculo_van;
UPDATE veiculo SET ID_LOCALIZACAO = @loc_onibus WHERE id = @veiculo_onibus;

-- 13) Usuário
INSERT INTO usuario (nome, email, senha, data_cadastro) VALUES
  ('Admin', 'admin@acme.com', 'admin123', CURDATE());

COMMIT;
