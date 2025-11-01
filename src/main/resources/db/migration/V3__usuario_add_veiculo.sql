-- V3__usuario_add_veiculo.sql
-- Adiciona relacionamento de veículo ao usuário para vincular login ao veículo

-- 1) Coluna
ALTER TABLE usuario
  ADD COLUMN id_veiculo BIGINT NULL;

-- 2) FK (com regras claras)
ALTER TABLE usuario
  ADD CONSTRAINT fk_usuario_veiculo
  FOREIGN KEY (id_veiculo)
  REFERENCES veiculo(id)
  ON DELETE SET NULL
  ON UPDATE CASCADE;

-- 3) Índice (útil para consultas; InnoDB pode criar um automaticamente, mas mantemos explícito)
CREATE INDEX idx_usuario_id_veiculo ON usuario(id_veiculo);
