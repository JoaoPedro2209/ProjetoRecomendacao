SELECT * FROM db_pessoas.tb_usuariomusica;

ALTER TABLE tb_usuariomusica ADD COLUMN usuarioId INT;
ALTER TABLE tb_usuariomusica ADD COLUMN musicaId INT;

ALTER TABLE tb_usuariomusica ADD CONSTRAINT FK_usuario
FOREIGN KEY (usuarioId) REFERENCES tb_usuarios(id);