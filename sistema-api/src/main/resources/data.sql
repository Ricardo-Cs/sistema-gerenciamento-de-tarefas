SET SESSION FOREIGN_KEY_CHECKS=0;

INSERT INTO `tarefa` VALUES
    (1, 'Descrição da tarefa 1', '2023-11-01', 'MÉDIA', 'PENDENTE', 'Tarefa 1', 1),
    (2, 'Descrição da tarefa 2', '2023-11-11', 'ALTA', 'COMPLETADO', 'Tarefa 2', 2),
    (3, 'Descrição da tarefa 3', '2023-10-11', 'BAIXA', 'FAZENDO', 'Tarefa 3', 2);

INSERT INTO `profissional` VALUES
    (1, 'Administrador', '$2a$10$zArhMpAXoIhDRvqmJSPI2uDJbGxoQft9g2LCa.7fBCOXDuqKVC6cS', 'Admin', 1),
    (2, 'Ana Lívia Dantas', '$2a$10$zArhMpAXoIhDRvqmJSPI2uDJbGxoQft9g2LCa.7fBCOXDuqKVC6cS','Livia-Ana', 2),
    (3, 'Rafael Silva Dourado', '$2a$10$zArhMpAXoIhDRvqmJSPI2uDJbGxoQft9g2LCa.7fBCOXDuqKVC6cS', 'RafaelD', 3);

INSERT INTO `cargo` VALUES
    (1, 'Gerente'),
    (2, 'Desenvolvedor'),
    (3, 'Contador');

INSERT INTO `solicitacao` VALUES
    (1, "2023-11-01", "SOLICITADA", 2, 1, 1);

SET SESSION FOREIGN_KEY_CHECKS=1;
