CREATE TABLE tb_perfil (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE tb_usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    perfil_id BIGINT NOT NULL,
    filial_id BIGINT NOT NULL,
    FOREIGN KEY (perfil_id) REFERENCES tb_perfil(id),
    FOREIGN KEY (filial_id) REFERENCES tb_filial(id)
);