-- V1__create_initial_schema.sql
-- Schema completo para api-busvans em snake_case minúsculo

SET NAMES utf8mb4;
SET time_zone = '+00:00';
SET FOREIGN_KEY_CHECKS = 0;

-- 1) usuario
CREATE TABLE `usuario` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `senha` VARCHAR(255) NOT NULL,
  `data_cadastro` DATE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2) cidade
CREATE TABLE `cidade` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3) empresa
CREATE TABLE `empresa` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(200),
  `cnpj` VARCHAR(18),
  `email` VARCHAR(100),
  `contato` VARCHAR(45)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4) cliente
CREATE TABLE `cliente` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `nome` VARCHAR(200) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `contato` BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5) rota
CREATE TABLE `rota` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `local_partida` BIGINT NOT NULL,
  `local_chegada` BIGINT NOT NULL,
  CONSTRAINT `fk_rota_partida` FOREIGN KEY (`local_partida`) REFERENCES `cidade` (`id`),
  CONSTRAINT `fk_rota_chegada` FOREIGN KEY (`local_chegada`) REFERENCES `cidade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 6) veiculo
CREATE TABLE `veiculo` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `placa` VARCHAR(10),
  `modelo` VARCHAR(100),
  `ano` INT,
  `tipo` VARCHAR(20),
  `id_empresa` BIGINT,
  `id_localizacao` BIGINT,
  CONSTRAINT `fk_veiculo_empresa` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 7) localizacao_veiculo
CREATE TABLE `localizacao_veiculo` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `id_veiculo` BIGINT,
  `latitude` BIGINT,
  `longitude` BIGINT,
  `atualizacao` DATETIME,
  CONSTRAINT `fk_localizacao_veiculo` FOREIGN KEY (`id_veiculo`) REFERENCES `veiculo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Adiciona FK de veiculo -> localizacao_veiculo
ALTER TABLE `veiculo` ADD CONSTRAINT `fk_veiculo_localizacao` 
  FOREIGN KEY (`id_localizacao`) REFERENCES `localizacao_veiculo` (`id`);

-- 8) veiculo_rota
CREATE TABLE `veiculo_rota` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `id_veiculo` BIGINT NOT NULL,
  `id_rota` BIGINT NOT NULL,
  `dias_semana` VARCHAR(100),
  `hora_saida` TIME,
  CONSTRAINT `fk_veiculo_rota_veiculo` FOREIGN KEY (`id_veiculo`) REFERENCES `veiculo` (`id`),
  CONSTRAINT `fk_veiculo_rota_rota` FOREIGN KEY (`id_rota`) REFERENCES `rota` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 9) rota_cidade (join table para possiveislocaisChegada e possiveislocaisPartida)
CREATE TABLE `rota_cidade` (
  `id_rota` BIGINT NOT NULL,
  `id_cidade` BIGINT NOT NULL,
  PRIMARY KEY (`id_rota`, `id_cidade`),
  CONSTRAINT `fk_rota_cidade_rota` FOREIGN KEY (`id_rota`) REFERENCES `rota` (`id`),
  CONSTRAINT `fk_rota_cidade_cidade` FOREIGN KEY (`id_cidade`) REFERENCES `cidade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 10) preco_passagem
CREATE TABLE `preco_passagem` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `local_partida` BIGINT NOT NULL,
  `local_chegada` BIGINT NOT NULL,
  `preco` DECIMAL(10,2) NOT NULL,
  `duracao_viagem` DOUBLE,
  CONSTRAINT `fk_preco_partida` FOREIGN KEY (`local_partida`) REFERENCES `cidade` (`id`),
  CONSTRAINT `fk_preco_chegada` FOREIGN KEY (`local_chegada`) REFERENCES `cidade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 11) pagamento
CREATE TABLE `pagamento` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `nome_cartao` VARCHAR(100),
  `numero_cartao` BIGINT,
  `validade_cartao` VARCHAR(7),
  `cvv` INT,
  `chave_pix` VARCHAR(200),
  `forma_pagamento` VARCHAR(20),
  `status_pagamento` VARCHAR(20),
  `valor` DECIMAL(10,2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 12) passagem
CREATE TABLE `passagem` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `id_cliente` BIGINT NOT NULL,
  `id_veiculo_rota` BIGINT NOT NULL,
  `quantidade` INT,
  `data_hora_compra` DATETIME,
  `data_viagem` DATE,
  `id_pagamento` BIGINT,
  CONSTRAINT `fk_passagem_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `fk_passagem_veiculo_rota` FOREIGN KEY (`id_veiculo_rota`) REFERENCES `veiculo_rota` (`id`),
  CONSTRAINT `fk_passagem_pagamento` FOREIGN KEY (`id_pagamento`) REFERENCES `pagamento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 13) ticket
CREATE TABLE `ticket` (
  `id` VARCHAR(50) PRIMARY KEY,
  `status` VARCHAR(20),
  `id_passagem` BIGINT,
  CONSTRAINT `fk_ticket_passagem` FOREIGN KEY (`id_passagem`) REFERENCES `passagem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
