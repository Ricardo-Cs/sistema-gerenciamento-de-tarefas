SET SESSION FOREIGN_KEY_CHECKS=0;

INSERT INTO `task` VALUES
    (1, 'Testando o título', '2023-11-01', 'MÉDIA', 'PENDENTE', 'Teste de título');

INSERT INTO `profissional` VALUES
    (1, 'admin@gmail.com', 'Ricardo Costa da Silva', '$2a$10$zArhMpAXoIhDRvqmJSPI2uDJbGxoQft9g2LCa.7fBCOXDuqKVC6cS', 1);

INSERT INTO `cargo` VALUES
    (1, 'Cargo X'),
    (2, 'Cargo Y'),
    (3, 'Cargo Z');

SET SESSION FOREIGN_KEY_CHECKS=1;
