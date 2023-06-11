SET SESSION FOREIGN_KEY_CHECKS=0;

INSERT INTO `tarefa` VALUES
    (1, 'Testando o título', '2023-11-01','MÉDIA', 'PENDENTE', 'Teste de título', 1);

INSERT INTO `profissional` VALUES
    (1, 'admin@gmail.com', 'Ricardo Costa da Silva', '$2a$10$zArhMpAXoIhDRvqmJSPI2uDJbGxoQft9g2LCa.7fBCOXDuqKVC6cS', 1),
    (2, 'empregado@gmail.com', 'Rafael Silva Dourado', '$2a$10$zArhMpAXoIhDRvqmJSPI2uDJbGxoQft9g2LCa.7fBCOXDuqKVC6cS', 3);

INSERT INTO `cargo` VALUES
    (1, 'Gerente'),
    (2, 'Desenvolvedor'),
    (3, 'Contador');

INSERT INTO `solicitacao` VALUES
    (1, "2023-11-01", "SOLICITADA", 2, 1, 1);

SET SESSION FOREIGN_KEY_CHECKS=1;
