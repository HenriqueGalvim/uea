INSERT INTO usuarios (username, password) VALUES ('admin@uea.edu.br', '$2a$10$Ebmi/uPZlhTEB7e39gsPTOfADOsL0IdEcEQllZyogM/WI/WKUMYdW');
INSERT INTO usuarios (username, password) VALUES ('gerente@uea.edu.br', '$2a$10$Ebmi/uPZlhTEB7e39gsPTOfADOsL0IdEcEQllZyogM/WI/WKUMYdW');
INSERT INTO usuarios (username, password) VALUES ('secretaria@uea.edu.br', '$2a$10$Ebmi/uPZlhTEB7e39gsPTOfADOsL0IdEcEQllZyogM/WI/WKUMYdW');

 INSERT INTO usuario_roles (usuario_id, role) VALUES (1, 'ROLE_ADMIN');
 INSERT INTO usuario_roles (usuario_id, role) VALUES (2, 'ROLE_ADMIN');
 INSERT INTO usuario_roles (usuario_id, role) VALUES (3, 'ROLE_ADMIN');                                
/*
INSERT INTO usuario_roles (usuario_id, role) VALUES (1, 'ROLE_GERENTE');
 INSERT INTO usuario_roles (usuario_id, role) VALUES (1, 'ROLE_ADMIN');
INSERT INTO usuario_roles (usuario_id, role) VALUES (1, 'ROLE_SECRETARIO'); 
*/


-- CATEGORIAS
INSERT INTO categoria (id, nome) VALUES (1, 'Evento Acadêmico');
INSERT INTO categoria (id, nome) VALUES (2, 'Extensão');
INSERT INTO categoria (id, nome) VALUES (3, 'Projeto Social');
INSERT INTO categoria (id, nome) VALUES (4, 'Visita Técnica');

-- CURSOS
INSERT INTO curso (id, nome, descricao, gerente_id) VALUES (100, 'Licenciatura em Computação', 'Curso de formação de professores com foco em computação.', 1);
INSERT INTO curso (id, nome, descricao, gerente_id) VALUES (101, 'Engenharia Florestal', 'Curso voltado ao manejo e preservação de recursos florestais.', 2);

-- ATIVIDADES (com categoria_id agora)
INSERT INTO atividade (id, titulo, descricao, publico_alvo, categoria_id, data, publicada, curso_id) 
VALUES (200, 'Semana de Programação', 'Evento com palestras sobre desenvolvimento de software.', 'Estudantes do curso', 1, '2025-05-10', true, 100);

INSERT INTO atividade (id, titulo, descricao, publico_alvo, categoria_id, data, publicada, curso_id) 
VALUES (201, 'Oficina de Robótica', 'Atividade prática com montagem de robôs educativos.', 'Alunos do ensino médio', 2, '2025-06-05', false, 100);

INSERT INTO atividade (id, titulo, descricao, publico_alvo, categoria_id, data, publicada, curso_id) 
VALUES (202, 'Mutirão de Reflorestamento', 'Plantio de mudas em área degradada da universidade.', 'Comunidade local', 3, '2025-04-22', true, 101);

INSERT INTO atividade (id, titulo, descricao, publico_alvo, categoria_id, data, publicada, curso_id) 
VALUES (203, 'Visita Técnica ao INPA', 'Visita guiada para conhecer projetos florestais.', 'Turma de Engenharia Florestal', 4, '2025-05-18', true, 101);

-- FOTOS
INSERT INTO foto (id, url, legenda, atividade_id) 
VALUES (300, 'https://exemplo.com/fotos/semana_programacao.jpg', 'Palestra sobre Java na Semana de Programação.', 200);

INSERT INTO foto (id, url, legenda, atividade_id) 
VALUES (301, 'https://exemplo.com/fotos/oficina_robotica.jpg', 'Alunos montando robôs.', 201);

INSERT INTO foto (id, url, legenda, atividade_id) 
VALUES (302, 'https://exemplo.com/fotos/reflorestamento.jpg', 'Voluntários durante o plantio de mudas.', 202);

INSERT INTO foto (id, url, legenda, atividade_id) 
VALUES (303, 'https://exemplo.com/fotos/visita_inpa.jpg', 'Grupo visitando laboratório no INPA.', 203);
