-- Seed data for api-busvans (MySQL)
-- Usage (PowerShell):
--   mysql -u root -p busvans < src/main/resources/db/_migration/V_002__seed.sql

SET NAMES utf8mb4;
SET time_zone = '+00:00';

START TRANSACTION;

-- Optional: clear existing data (uncomment if you want a clean slate)
-- SET FOREIGN_KEY_CHECKS = 0;
-- DELETE FROM `ROTA_CIDADE`;
-- DELETE FROM `VEICULO_ROTA`;
-- DELETE FROM `PRECO_PASSAGEM`;
-- DELETE FROM `LOCALIZACAO_VEICULO`;
-- DELETE FROM `ticket`;
-- DELETE FROM `passagem`;
-- DELETE FROM `pagamento`;
-- DELETE FROM `cliente`;
-- DELETE FROM `veiculo`;
-- DELETE FROM `empresa`;
-- DELETE FROM `rota`;
-- DELETE FROM `cidade`;
-- DELETE FROM `usuario`;
-- SET FOREIGN_KEY_CHECKS = 1;

-- 1) Cidades
INSERT INTO `cidade` (`nome`) VALUES
  ('São Paulo'),
  ('Rio de Janeiro'),
  ('Belo Horizonte'),
  ('Campinas');

SET @cidade_sp := 1;   -- São Paulo
SET @cidade_rj := 2;   -- Rio de Janeiro
SET @cidade_bh := 3;   -- Belo Horizonte
SET @cidade_ca := 4;   -- Campinas

-- 2) Empresa
INSERT INTO `empresa` (`nome`, `cnpj`, `email`, `contato`) VALUES
  ('Viação M4', '12.345.678/0001-99', 'contato@m4.com', '11999999999');
SET @empresa_m4 := LAST_INSERT_ID();

-- 3) Veículos (Tipo: V=Van, O=Ônibus)
INSERT INTO `veiculo` (`placa`, `modelo`, `ano`, `TIPO`, `ID_EMPRESA`) VALUES
  ('ABC-1234', 'Mercedes Sprinter', 2020, 'V', @empresa_m4);
SET @veiculo_van := LAST_INSERT_ID();
INSERT INTO `veiculo` (`placa`, `modelo`, `ano`, `TIPO`, `ID_EMPRESA`) VALUES
  ('XYZ-9876', 'Marcopolo Paradiso', 2018, 'O', @empresa_m4);
SET @veiculo_onibus := LAST_INSERT_ID();

-- 4) Rotas (SP -> RJ) e (RJ -> BH)
INSERT INTO `rota` (`LOCAL_PARTIDA`, `LOCAL_CHEGADA`) VALUES
  (@cidade_sp, @cidade_rj);
SET @rota_sp_rj := LAST_INSERT_ID();
INSERT INTO `rota` (`LOCAL_PARTIDA`, `LOCAL_CHEGADA`) VALUES
  (@cidade_rj, @cidade_bh);
SET @rota_rj_bh := LAST_INSERT_ID();

-- 5) Possíveis cidades de partida/chegada (mesma tabela ROTA_CIDADE para ambas as listas)
-- Nota: ambas propriedades em Rota usam a mesma join table "ROTA_CIDADE".
INSERT INTO `rota_cidade` (`id_rota`, `id_cidade`) VALUES
  (@rota_sp_rj, @cidade_sp),  -- partida possível
  (@rota_sp_rj, @cidade_ca),  -- partida alternativa
  (@rota_sp_rj, @cidade_rj),  -- chegada possível
  (@rota_rj_bh, @cidade_rj),  -- partida possível
  (@rota_rj_bh, @cidade_bh);  -- chegada possível

-- 6) Preços de passagem
INSERT INTO `preco_passagem` (`local_partida`, `local_chegada`, `preco`, `duracao_viagem`) VALUES
  (@cidade_sp, @cidade_rj, 120.00, 6.5),
  (@cidade_rj, @cidade_bh, 150.00, 7.0);

-- 7) Veículo em rota e horários
INSERT INTO `veiculo_rota` (`id_veiculo`, `id_rota`, `dias_semana`, `hora_saida`) VALUES
  (@veiculo_van, @rota_sp_rj, 'SEG,TER,QUA,QUI,SEX', '08:00:00');
SET @veiculo_rota_1 := LAST_INSERT_ID();
INSERT INTO `veiculo_rota` (`id_veiculo`, `id_rota`, `dias_semana`, `hora_saida`) VALUES
  (@veiculo_onibus, @rota_rj_bh, 'SEX,SAB,DOM', '22:00:00');
SET @veiculo_rota_2 := LAST_INSERT_ID();

-- 8) Cliente
INSERT INTO `cliente` (`nome`, `cpf`, `DATA_NASCIMENTO`, `email`, `contato`) VALUES
  ('John Doe', '12345678901', '1990-01-01', 'john@doe.com', 11988887777);
SET @cliente_john := LAST_INSERT_ID();

-- 9) Pagamentos (CC = Cartão de Crédito, SUC = Sucesso)
INSERT INTO `pagamento` (`nome_cartao`, `numero_cartao`, `validade_cartao`, `cvv`, `chave_pix`, `forma_pagamento`, `status_pagamento`, `valor`) VALUES
  ('JOHN DOE', 4111111111111111, '12/28', 123, NULL, 'CC', 'SUC', 120.00);
SET @pagamento_cc := LAST_INSERT_ID();
INSERT INTO `pagamento` (`nome_cartao`, `numero_cartao`, `validade_cartao`, `cvv`, `chave_pix`, `forma_pagamento`, `status_pagamento`, `valor`) VALUES
  (NULL, NULL, NULL, NULL, 'chave-pix-john@example.com', 'PX', 'PEN', 150.00);
SET @pagamento_px := LAST_INSERT_ID();

-- 10) Passagens
INSERT INTO `passagem` (`id_cliente`, `id_veiculo_rota`, `quantidade`, `data_hora_compra`, `data_viagem`, `id_pagamento`) VALUES
  (@cliente_john, @veiculo_rota_1, 1, NOW(), DATE_ADD(CURDATE(), INTERVAL 4 DAY), @pagamento_cc);
SET @passagem_1 := LAST_INSERT_ID();
INSERT INTO `passagem` (`id_cliente`, `id_veiculo_rota`, `quantidade`, `data_hora_compra`, `data_viagem`, `id_pagamento`) VALUES
  (@cliente_john, @veiculo_rota_2, 2, NOW(), DATE_ADD(CURDATE(), INTERVAL 10 DAY), @pagamento_px);
SET @passagem_2 := LAST_INSERT_ID();

-- 11) Tickets (Status: D = Disponivel)
INSERT INTO `ticket` (`id`, `status`, `id_passagem`) VALUES
  ('TCK-0001', 'D', @passagem_1),
  ('TCK-0002', 'D', @passagem_2),
  ('TCK-0003', 'D', @passagem_2);

-- 12) Localização de veículo (usar UPDATE no veículo para linkar localização)
INSERT INTO `localizacao_veiculo` (`id_veiculo`, `latitude`, `longitude`, `atualizacao`) VALUES
  (@veiculo_van, -23562890, -46637720, NOW());  -- São Paulo (aprox)
SET @loc_van := LAST_INSERT_ID();
INSERT INTO `localizacao_veiculo` (`id_veiculo`, `latitude`, `longitude`, `atualizacao`) VALUES
  (@veiculo_onibus, -22806430, -43172800, NOW()); -- Rio de Janeiro (aprox)
SET @loc_onibus := LAST_INSERT_ID();

UPDATE `veiculo` SET `id_localizacao` = @loc_van   WHERE `ID` = @veiculo_van;
UPDATE `veiculo` SET `id_localizacao` = @loc_onibus WHERE `ID` = @veiculo_onibus;

-- 13) Usuário
INSERT INTO `usuario` (`nome`, `email`, `senha`, `data_cadastro`) VALUES
  ('Admin', 'admin@acme.com', 'admin123', CURDATE());

COMMIT;

-- Notes:
-- - Este script popula as tabelas principais e as join tables explicitamente definidas (ROTA_CIDADE).
-- - As coleções mapeadas por OneToMany sem mappedBy (Empresa.veiculos, Passagem.tickets) também têm FKs no lado "muitos";
--   aqui populamos os FKs (ex.: VEICULO.ID_EMPRESA, TICKET.ID_PASSAGEM) que atendem à maioria dos casos de consulta.
--   Se for necessário que Empresa.veiculos/Passagem.tickets carreguem via join table criada pelo JPA, ajuste conforme o nome real da join table no seu schema.
