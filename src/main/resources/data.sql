-- Seed data for api-busvans executed by Spring Boot after JPA (dev profile)
-- Requires:
--   spring.jpa.defer-datasource-initialization=true
--   spring.sql.init.mode=always

-- Intentionally left blank. Seeding is manual now.
-- See: src/main/resources/db/_migration/V_002__seed.sql

SET @cidade_sp := (SELECT id FROM cidade WHERE nome = 'São Paulo' LIMIT 1);
SET @cidade_rj := (SELECT id FROM cidade WHERE nome = 'Rio de Janeiro' LIMIT 1);
SET @cidade_bh := (SELECT id FROM cidade WHERE nome = 'Belo Horizonte' LIMIT 1);
SET @cidade_ca := (SELECT id FROM cidade WHERE nome = 'Campinas' LIMIT 1);

-- 2) Empresa
INSERT INTO empresa (nome, cnpj, email, contato)
SELECT 'Viação M4', '12.345.678/0001-99', 'contato@m4.com', '11999999999'
WHERE NOT EXISTS (SELECT 1 FROM empresa WHERE cnpj = '12.345.678/0001-99');
SET @empresa_m4 := (SELECT id FROM empresa WHERE cnpj = '12.345.678/0001-99' LIMIT 1);

-- 3) Veículos (tipo: V=Van, O=Ônibus)
INSERT INTO veiculo (placa, modelo, ano, TIPO, ID_EMPRESA)
SELECT 'ABC-1234', 'Mercedes Sprinter', 2020, 'V', @empresa_m4
WHERE NOT EXISTS (SELECT 1 FROM veiculo WHERE placa = 'ABC-1234');
SET @veiculo_van := (SELECT id FROM veiculo WHERE placa = 'ABC-1234' LIMIT 1);
INSERT INTO veiculo (placa, modelo, ano, TIPO, ID_EMPRESA)
SELECT 'XYZ-9876', 'Marcopolo Paradiso', 2018, 'O', @empresa_m4
WHERE NOT EXISTS (SELECT 1 FROM veiculo WHERE placa = 'XYZ-9876');
SET @veiculo_onibus := (SELECT id FROM veiculo WHERE placa = 'XYZ-9876' LIMIT 1);

-- 4) Rotas (SP -> RJ) e (RJ -> BH)
INSERT INTO rota (LOCAL_PARTIDA, LOCAL_CHEGADA)
SELECT @cidade_sp, @cidade_rj
WHERE NOT EXISTS (SELECT 1 FROM rota WHERE LOCAL_PARTIDA = @cidade_sp AND LOCAL_CHEGADA = @cidade_rj);
SET @rota_sp_rj := (SELECT id FROM rota WHERE LOCAL_PARTIDA = @cidade_sp AND LOCAL_CHEGADA = @cidade_rj LIMIT 1);
INSERT INTO rota (LOCAL_PARTIDA, LOCAL_CHEGADA)
SELECT @cidade_rj, @cidade_bh
WHERE NOT EXISTS (SELECT 1 FROM rota WHERE LOCAL_PARTIDA = @cidade_rj AND LOCAL_CHEGADA = @cidade_bh);
SET @rota_rj_bh := (SELECT id FROM rota WHERE LOCAL_PARTIDA = @cidade_rj AND LOCAL_CHEGADA = @cidade_bh LIMIT 1);

-- 5) Possíveis cidades de partida/chegada (mesma tabela rota_cidade)
INSERT INTO rota_cidade (ID_ROTA, ID_CIDADE)
SELECT @rota_sp_rj, @cidade_sp WHERE NOT EXISTS (
  SELECT 1 FROM rota_cidade WHERE ID_ROTA = @rota_sp_rj AND ID_CIDADE = @cidade_sp
);
INSERT INTO rota_cidade (ID_ROTA, ID_CIDADE)
SELECT @rota_sp_rj, @cidade_ca WHERE NOT EXISTS (
  SELECT 1 FROM rota_cidade WHERE ID_ROTA = @rota_sp_rj AND ID_CIDADE = @cidade_ca
);
INSERT INTO rota_cidade (ID_ROTA, ID_CIDADE)
SELECT @rota_sp_rj, @cidade_rj WHERE NOT EXISTS (
  SELECT 1 FROM rota_cidade WHERE ID_ROTA = @rota_sp_rj AND ID_CIDADE = @cidade_rj
);
INSERT INTO rota_cidade (ID_ROTA, ID_CIDADE)
SELECT @rota_rj_bh, @cidade_rj WHERE NOT EXISTS (
  SELECT 1 FROM rota_cidade WHERE ID_ROTA = @rota_rj_bh AND ID_CIDADE = @cidade_rj
);
INSERT INTO rota_cidade (ID_ROTA, ID_CIDADE)
SELECT @rota_rj_bh, @cidade_bh WHERE NOT EXISTS (
  SELECT 1 FROM rota_cidade WHERE ID_ROTA = @rota_rj_bh AND ID_CIDADE = @cidade_bh
);

-- 6) Preços de passagem
INSERT INTO preco_passagem (LOCAL_PARTIDA, LOCAL_CHEGADA, preco, DURACAO_VIAGEM)
SELECT @cidade_sp, @cidade_rj, 120.00, 6.5
WHERE NOT EXISTS (
  SELECT 1 FROM preco_passagem WHERE LOCAL_PARTIDA = @cidade_sp AND LOCAL_CHEGADA = @cidade_rj
);
INSERT INTO preco_passagem (LOCAL_PARTIDA, LOCAL_CHEGADA, preco, DURACAO_VIAGEM)
SELECT @cidade_rj, @cidade_bh, 150.00, 7.0
WHERE NOT EXISTS (
  SELECT 1 FROM preco_passagem WHERE LOCAL_PARTIDA = @cidade_rj AND LOCAL_CHEGADA = @cidade_bh
);

-- 7) Veículo em rota e horários
INSERT INTO veiculo_rota (ID_VEICULO, ID_ROTA, DIAS_SEMANA, HORA_SAIDA)
SELECT @veiculo_van, @rota_sp_rj, 'SEG,TER,QUA,QUI,SEX', '08:00:00'
WHERE NOT EXISTS (
  SELECT 1 FROM veiculo_rota WHERE ID_VEICULO = @veiculo_van AND ID_ROTA = @rota_sp_rj AND HORA_SAIDA = '08:00:00'
);
SET @veiculo_rota_1 := (SELECT id FROM veiculo_rota WHERE ID_VEICULO = @veiculo_van AND ID_ROTA = @rota_sp_rj AND HORA_SAIDA = '08:00:00' LIMIT 1);
INSERT INTO veiculo_rota (ID_VEICULO, ID_ROTA, DIAS_SEMANA, HORA_SAIDA)
SELECT @veiculo_onibus, @rota_rj_bh, 'SEX,SAB,DOM', '22:00:00'
WHERE NOT EXISTS (
  SELECT 1 FROM veiculo_rota WHERE ID_VEICULO = @veiculo_onibus AND ID_ROTA = @rota_rj_bh AND HORA_SAIDA = '22:00:00'
);
SET @veiculo_rota_2 := (SELECT id FROM veiculo_rota WHERE ID_VEICULO = @veiculo_onibus AND ID_ROTA = @rota_rj_bh AND HORA_SAIDA = '22:00:00' LIMIT 1);

-- 8) Cliente
INSERT INTO cliente (nome, cpf, DATA_NASCIMENTO, email, contato)
SELECT 'John Doe', '12345678901', '1990-01-01', 'john@doe.com', 11988887777
WHERE NOT EXISTS (SELECT 1 FROM cliente WHERE cpf = '12345678901');
SET @cliente_john := (SELECT id FROM cliente WHERE cpf = '12345678901' LIMIT 1);

-- 9) Pagamentos (CC = Cartão de Crédito, SUC = Sucesso)
INSERT INTO pagamento (NOME_CARTAO, NUMERO_CARTAO, VALIDADE_CARTAO, cvv, CHAVE_PIX, FORMA_PAGAMENTO, STATUS_PAGAMENTO, VALOR)
SELECT 'JOHN DOE', 4111111111111111, '12/28', 123, NULL, 'CC', 'SUC', 120.00
WHERE NOT EXISTS (
  SELECT 1 FROM pagamento WHERE NUMERO_CARTAO = 4111111111111111 AND VALIDADE_CARTAO = '12/28'
);
SET @pagamento_cc := (SELECT id FROM pagamento WHERE NUMERO_CARTAO = 4111111111111111 AND VALIDADE_CARTAO = '12/28' LIMIT 1);
INSERT INTO pagamento (NOME_CARTAO, NUMERO_CARTAO, VALIDADE_CARTAO, cvv, CHAVE_PIX, FORMA_PAGAMENTO, STATUS_PAGAMENTO, VALOR)
SELECT NULL, NULL, NULL, NULL, 'chave-pix-john@example.com', 'PX', 'PEN', 150.00
WHERE NOT EXISTS (
  SELECT 1 FROM pagamento WHERE CHAVE_PIX = 'chave-pix-john@example.com'
);
SET @pagamento_px := (SELECT id FROM pagamento WHERE CHAVE_PIX = 'chave-pix-john@example.com' LIMIT 1);

-- 10) Passagens
INSERT INTO passagem (ID_CLIENTE, ID_VEICULO_ROTA, quantidade, DATA_HORA_COMPRA, DATA_VIAGEM, ID_PAGAMENTO)
SELECT @cliente_john, @veiculo_rota_1, 1, NOW(), DATE_ADD(CURDATE(), INTERVAL 4 DAY), @pagamento_cc
WHERE NOT EXISTS (
  SELECT 1 FROM passagem WHERE ID_CLIENTE = @cliente_john AND ID_VEICULO_ROTA = @veiculo_rota_1 AND ID_PAGAMENTO = @pagamento_cc
);
SET @passagem_1 := (SELECT id FROM passagem WHERE ID_CLIENTE = @cliente_john AND ID_VEICULO_ROTA = @veiculo_rota_1 AND ID_PAGAMENTO = @pagamento_cc LIMIT 1);
INSERT INTO passagem (ID_CLIENTE, ID_VEICULO_ROTA, quantidade, DATA_HORA_COMPRA, DATA_VIAGEM, ID_PAGAMENTO)
SELECT @cliente_john, @veiculo_rota_2, 2, NOW(), DATE_ADD(CURDATE(), INTERVAL 10 DAY), @pagamento_px
WHERE NOT EXISTS (
  SELECT 1 FROM passagem WHERE ID_CLIENTE = @cliente_john AND ID_VEICULO_ROTA = @veiculo_rota_2 AND ID_PAGAMENTO = @pagamento_px
);
SET @passagem_2 := (SELECT id FROM passagem WHERE ID_CLIENTE = @cliente_john AND ID_VEICULO_ROTA = @veiculo_rota_2 AND ID_PAGAMENTO = @pagamento_px LIMIT 1);

-- 11) Tickets (Status: D = Disponivel)
INSERT INTO ticket (id, status, ID_PASSAGEM)
SELECT 'TCK-0001', 'D', @passagem_1
WHERE NOT EXISTS (SELECT 1 FROM ticket WHERE id = 'TCK-0001');
INSERT INTO ticket (id, status, ID_PASSAGEM)
SELECT 'TCK-0002', 'D', @passagem_2
WHERE NOT EXISTS (SELECT 1 FROM ticket WHERE id = 'TCK-0002');
INSERT INTO ticket (id, status, ID_PASSAGEM)
SELECT 'TCK-0003', 'D', @passagem_2
WHERE NOT EXISTS (SELECT 1 FROM ticket WHERE id = 'TCK-0003');

-- 12) Localização de veículo (usar UPDATE no veículo para linkar localização)
INSERT INTO localizacao_veiculo (ID_VEICULO, latitude, longitude, atualizacao)
SELECT @veiculo_van, -23562890, -46637720, NOW()
WHERE NOT EXISTS (SELECT 1 FROM localizacao_veiculo WHERE ID_VEICULO = @veiculo_van);
SET @loc_van := (SELECT id FROM localizacao_veiculo WHERE ID_VEICULO = @veiculo_van LIMIT 1);
INSERT INTO localizacao_veiculo (ID_VEICULO, latitude, longitude, atualizacao)
SELECT @veiculo_onibus, -22806430, -43172800, NOW()
WHERE NOT EXISTS (SELECT 1 FROM localizacao_veiculo WHERE ID_VEICULO = @veiculo_onibus);
SET @loc_onibus := (SELECT id FROM localizacao_veiculo WHERE ID_VEICULO = @veiculo_onibus LIMIT 1);

UPDATE veiculo SET ID_LOCALIZACAO = @loc_van   WHERE id = @veiculo_van AND (ID_LOCALIZACAO IS NULL OR ID_LOCALIZACAO <> @loc_van);
UPDATE veiculo SET ID_LOCALIZACAO = @loc_onibus WHERE id = @veiculo_onibus AND (ID_LOCALIZACAO IS NULL OR ID_LOCALIZACAO <> @loc_onibus);

-- 13) Usuário
INSERT INTO usuario (nome, email, senha, data_cadastro)
SELECT 'Admin', 'admin@acme.com', 'admin123', CURDATE()
WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE email = 'admin@acme.com');

COMMIT;
