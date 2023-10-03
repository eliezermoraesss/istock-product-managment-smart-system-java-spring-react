INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Eliezer', 'Brown', 'eliezer@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Nádia', 'Green', 'nadia@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Snoop', 'Green', 'snoop@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Luisa', 'Green', 'luisa@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Nicolas', 'Green', 'nicolas@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (4, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (5, 1);

-- Inserções para a tabela tb_departamento
INSERT INTO tb_departamento (codigo, descricao) VALUES (1, 'Departamento 1');
INSERT INTO tb_departamento (codigo, descricao) VALUES (2, 'Departamento 2');
INSERT INTO tb_departamento (codigo, descricao) VALUES (3, 'Departamento 3');
INSERT INTO tb_departamento (codigo, descricao) VALUES (4, 'Departamento 4');
INSERT INTO tb_departamento (codigo, descricao) VALUES (5, 'Departamento 5');

-- Inserções para a tabela tb_funcionario
INSERT INTO tb_funcionario (matricula, nome, departamento_id, user_id) VALUES (101, 'Funcionário 1', 1, 1);
INSERT INTO tb_funcionario (matricula, nome, departamento_id, user_id) VALUES (102, 'Funcionário 2', 2, 2);
INSERT INTO tb_funcionario (matricula, nome, departamento_id, user_id) VALUES (103, 'Funcionário 3', 3, 3);
INSERT INTO tb_funcionario (matricula, nome, departamento_id, user_id) VALUES (104, 'Funcionário 4', 4, 4);
INSERT INTO tb_funcionario (matricula, nome, departamento_id, user_id) VALUES (105, 'Funcionário 5', 5, 5);

INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('1', '99910000', 'ENCOMENDAS POSTAIS');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('2', '99920000', 'AMOSTRAS');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('3', '99970000', 'MERCADORIAS DOADAS');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('4', '99997101', 'PEDRAS EM BRUTO DO CAPITULO 71 DA NCM');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('5', '99997102', 'PEDRAS LAPIDADAS/TRABALHADAS DO CAPITULO 71 DA NCM');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('6', '99997103', 'JOALHERIA DE OURO DO CAPITULO 71 DA NCM');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('7', '99997104', 'OUTROS ARTIGOS DO CAPITULO 71 DA NCM');

INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Flores', 300, 'Centro', 'Uberlândia', 'MG', '38400-000');
INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Avenida Matos', 105, 'Centro', 'Uberlândia', 'MG', '38400-000');
INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Avenida Floriano', 2106, 'Centro', 'Uberlândia', 'MG', '38400-000');
INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 800, 'Centro', 'Mogi Mirim', 'SP', '38400-000');
INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 900, 'Centro', 'Uberlândia', 'RJ', '38400-000');
INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 1000, 'Centro', 'Uberlândia', 'AM', '38400-000');
INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 777, 'Centro', 'Uberlândia', 'ES', '38400-000');
INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 268, 'Centro', 'Uberlândia', 'RS', '38400-000');
INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 322, 'Centro', 'Uberlândia', 'RS', '38400-000');
INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 21, 'Centro', 'Uberlândia', 'RS', '38400-000');

-- Inserções para a tabela tb_grupo (grupos de produtos para uma loja de roupas)
INSERT INTO tb_grupo (codigo, descricao) VALUES (1, 'Camisetas');
INSERT INTO tb_grupo (codigo, descricao) VALUES (2, 'Calças');
INSERT INTO tb_grupo (codigo, descricao) VALUES (3, 'Vestidos');
INSERT INTO tb_grupo (codigo, descricao) VALUES (4, 'Sapatos');
INSERT INTO tb_grupo (codigo, descricao) VALUES (5, 'Acessórios');
INSERT INTO tb_grupo (codigo, descricao) VALUES (6, 'Roupas Íntimas');
INSERT INTO tb_grupo (codigo, descricao) VALUES (7, 'Casacos');
INSERT INTO tb_grupo (codigo, descricao) VALUES (8, 'Bijuterias');
INSERT INTO tb_grupo (codigo, descricao) VALUES (9, 'Bolsas');
INSERT INTO tb_grupo (codigo, descricao) VALUES (10, 'Sungas');

-- Inserções para a tabela tb_fornecedor (com cnpj e inscricao_estadual únicos)
INSERT INTO tb_fornecedor (codigo, razao_social, cnpj, inscricao_estadual, endereco_id) VALUES (1, 'Fornecedor 1', '123456789', 'IE123456', 1);
INSERT INTO tb_fornecedor (codigo, razao_social, cnpj, inscricao_estadual, endereco_id) VALUES (2, 'Fornecedor 2', '987654321', 'IE987654', 2);
INSERT INTO tb_fornecedor (codigo, razao_social, cnpj, inscricao_estadual, endereco_id) VALUES (3, 'Fornecedor 3', '111222333', 'IE111222', 3);
INSERT INTO tb_fornecedor (codigo, razao_social, cnpj, inscricao_estadual, endereco_id) VALUES (4, 'Fornecedor 4', '444555666', 'IE444555', 4);
INSERT INTO tb_fornecedor (codigo, razao_social, cnpj, inscricao_estadual, endereco_id) VALUES (5, 'Fornecedor 5', '777888999', 'IE777888', 5);
INSERT INTO tb_fornecedor (codigo, razao_social, cnpj, inscricao_estadual, endereco_id) VALUES (6, 'Fornecedor 6', '222333444', 'IE222333', 6);
INSERT INTO tb_fornecedor (codigo, razao_social, cnpj, inscricao_estadual, endereco_id) VALUES (7, 'Fornecedor 7', '666777888', 'IE666777', 7);
INSERT INTO tb_fornecedor (codigo, razao_social, cnpj, inscricao_estadual, endereco_id) VALUES (8, 'Fornecedor 8', '999888777', 'IE999888', 8);
INSERT INTO tb_fornecedor (codigo, razao_social, cnpj, inscricao_estadual, endereco_id) VALUES (9, 'Fornecedor 9', '555444333', 'IE555444', 9);
INSERT INTO tb_fornecedor (codigo, razao_social, cnpj, inscricao_estadual, endereco_id) VALUES (10, 'Fornecedor 10', '333222111', 'IE333222', 10);

-- Inserções de produtos na tabela tb_produto
INSERT INTO tb_produto (preco, quantidade, quantidade_minima_estoque, data_de_cadastro, grupo_id, codigo, codigo_ncm, descricao, status, unidade_medida) VALUES (15.99, 20.00, 5.00, NOW(), 1, 1, '61091000', 'Camiseta branca', 'DISPONIVEL', 'UN');
INSERT INTO tb_produto (preco, quantidade, quantidade_minima_estoque, data_de_cadastro, grupo_id, codigo, codigo_ncm, descricao, status, unidade_medida) VALUES (39.99, 15.00, 3.00, NOW(), 2, 2, '62034200', 'Calça jeans', 'DISPONIVEL', 'UN');
INSERT INTO tb_produto (preco, quantidade, quantidade_minima_estoque, data_de_cadastro, grupo_id, codigo, codigo_ncm, descricao, status, unidade_medida) VALUES (49.99, 10.00, 2.00, NOW(), 3, 3, '62044300', 'Vestido floral', 'DISPONIVEL', 'UN');
INSERT INTO tb_produto (preco, quantidade, quantidade_minima_estoque, data_de_cadastro, grupo_id, codigo, codigo_ncm, descricao, status, unidade_medida) VALUES (29.99, 12.00, 4.00, NOW(), 4, 4, '64029900', 'Sapato social', 'DISPONIVEL', 'UN');
INSERT INTO tb_produto (preco, quantidade, quantidade_minima_estoque, data_de_cadastro, grupo_id, codigo, codigo_ncm, descricao, status, unidade_medida) VALUES (9.99, 50.00, 10.00, NOW(), 5, 5, '71171900', 'Bijuterias', 'DISPONIVEL', 'UN');
INSERT INTO tb_produto (preco, quantidade, quantidade_minima_estoque, data_de_cadastro, grupo_id, codigo, codigo_ncm, descricao, status, unidade_medida) VALUES (12.99, 30.00, 8.00, NOW(), 6, 6, '61082200', 'Roupa íntima', 'DISPONIVEL', 'UN');
INSERT INTO tb_produto (preco, quantidade, quantidade_minima_estoque, data_de_cadastro, grupo_id, codigo, codigo_ncm, descricao, status, unidade_medida) VALUES (89.99, 8.00, 2.00, NOW(), 7, 7, '62019200', 'Casaco de inverno', 'DISPONIVEL', 'UN');
INSERT INTO tb_produto (preco, quantidade, quantidade_minima_estoque, data_de_cadastro, grupo_id, codigo, codigo_ncm, descricao, status, unidade_medida) VALUES (59.99, 25.00, 6.00, NOW(), 8, 8, '42023100', 'Bolsa de couro', 'DISPONIVEL', 'UN');
INSERT INTO tb_produto (preco, quantidade, quantidade_minima_estoque, data_de_cadastro, grupo_id, codigo, codigo_ncm, descricao, status, unidade_medida) VALUES (19.99, 18.00, 4.00, NOW(), 9, 9, '61123100', 'Sunga de praia', 'DISPONIVEL', 'UN');
INSERT INTO tb_produto (preco, quantidade, quantidade_minima_estoque, data_de_cadastro, grupo_id, codigo, codigo_ncm, descricao, status, unidade_medida) VALUES (9.99, 40.00, 7.00, NOW(), 10, 10, '65050090', 'Boné esportivo', 'DISPONIVEL', 'UN');

-- Relacionamento entre produtos e fornecedores na tabela tb_produto_fornecedor (10 produtos com 10 fornecedores)
-- Produto 1 com fornecedores 1 e 2
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (1, 1);
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (1, 2);

-- Produto 2 com fornecedores 3 e 4
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (2, 3);
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (2, 4);

-- Produto 3 com fornecedores 5 e 6
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (3, 5);
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (3, 6);

-- Produto 4 com fornecedores 7 e 8
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (4, 7);
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (4, 8);

-- Produto 5 com fornecedores 9 e 10
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (5, 9);
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (5, 10);

-- Produto 6 com fornecedores 1 e 2
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (6, 1);
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (6, 2);

-- Produto 7 com fornecedores 3 e 4
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (7, 3);
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (7, 4);

-- Produto 8 com fornecedores 5 e 6
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (8, 5);
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (8, 6);

-- Produto 9 com fornecedores 7 e 8
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (9, 7);
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (9, 8);

-- Produto 10 com fornecedores 9 e 10
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (10, 9);
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (10, 10);

-- Inserções de dados de exemplo na tabela tb_unidade
INSERT INTO tb_unidade (codigo, descricao) VALUES ('PC', 'Peça');
INSERT INTO tb_unidade (codigo, descricao) VALUES ('PCT', 'Pacote');
INSERT INTO tb_unidade (codigo, descricao) VALUES ('L', 'Litro');
INSERT INTO tb_unidade (codigo, descricao) VALUES ('M2', 'Metro Quadrado');
INSERT INTO tb_unidade (codigo, descricao) VALUES ('M', 'Metro');
INSERT INTO tb_unidade (codigo, descricao) VALUES ('KG', 'Quilograma');
INSERT INTO tb_unidade (codigo, descricao) VALUES ('UN', 'Unidade');
INSERT INTO tb_unidade (codigo, descricao) VALUES ('CX', 'Caixa');
INSERT INTO tb_unidade (codigo, descricao) VALUES ('ROLO', 'Rolo');
INSERT INTO tb_unidade (codigo, descricao) VALUES ('G', 'Grama');
