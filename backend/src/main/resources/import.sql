INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Eliezer', 'Brown', 'eliezer@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Nádia', 'Green', 'nadia@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('1', '99910000', 'ENCOMENDAS POSTAIS');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('2', '99920000', 'AMOSTRAS');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('3', '99970000', 'MERCADORIAS DOADAS');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('4', '99997101', 'PEDRAS EM BRUTO DO CAPITULO 71 DA NCM');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('5', '99997102', 'PEDRAS LAPIDADAS/TRABALHADAS DO CAPITULO 71 DA NCM');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('6', '99997103', 'JOALHERIA DE OURO DO CAPITULO 71 DA NCM');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('7', '99997104', 'OUTROS ARTIGOS DO CAPITULO 71 DA NCM');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('8', '99980101', 'CONSUMO DE BORDO - COMBUSTIVEIS E LUBRIFP/EMBARCAC');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('9', '99980102', 'CONSUMO DE BORDO - COMBUSTIVEIS E LUBRIFP/AERONAVE');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('10', '99980201', 'CONSUMO DE BORDO - QQOUTRA MERCADORIA P/EMBARCACOE');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('11', '99980202', 'CONSUMO DE BORDO - QQOUTRA MERCADORIA P/AERONAVES');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('12', '99999900', 'QQOUTRA MERCADORIA SEM COBERTURA CAMBIAL');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('13', '01011100', 'CAVALOS REPRODUTORES,DE RACA PURA');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('14', '01011900', 'OUTROS CAVALOS,VIVOS');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('15', '01012000', 'ASININOS E MUARES VIVOS');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('16', '01021090', 'OUTROS BOVINOS REPRODUTORES DE RACA PURA');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('17', '01021010', 'BOVINOS REPRODUTORES DE RACA PURA,PRENHE OU CRIA A');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('18', '01029011', 'OUTROS BOVINOS PARA REPRODUCAO,PRENHE OU COM CRIA');
INSERT INTO tb_ncm (posicao, codigo, descricao) VALUES ('19', '01029019', 'OUTROS BOVINOS PARA REPRODUCAO');

INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Flores', 300, 'Centro', 'Uberlândia', 'MG', '38400-000');
INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Avenida Matos', 105, 'Centro', 'Uberlândia', 'MG', '38400-000');
INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Avenida Floriano', 2106, 'Centro', 'Uberlândia', 'MG', '38400-000');
INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 800, 'Centro', 'Mogi Mirim', 'SP', '38400-000');
INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 900, 'Centro', 'Uberlândia', 'RJ', '38400-000');
INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 1000, 'Centro', 'Uberlândia', 'AM', '38400-000');
INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 777, 'Centro', 'Uberlândia', 'ES', '38400-000');
INSERT INTO tb_endereco (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 2, 'Centro', 'Uberlândia', 'RS', '38400-000');

INSERT INTO tb_grupo (codigo, descricao) VALUES (1, 'EPI');
INSERT INTO tb_grupo (codigo, descricao) VALUES (2, 'MECANICA');
INSERT INTO tb_grupo (codigo, descricao) VALUES (3, 'ELETRICA');
INSERT INTO tb_grupo (codigo, descricao) VALUES (4, 'ATIVOS');

INSERT INTO tb_fornecedor(codigo, razao_social, cnpj, endereco_id) VALUES (1, 'Fornecedor 1', '123456789', 1);
INSERT INTO tb_fornecedor(codigo, razao_social, cnpj, endereco_id) VALUES (2, 'Fornecedor 2', '12345', 2);
INSERT INTO tb_fornecedor(codigo, razao_social, cnpj, endereco_id) VALUES (3, 'Fornecedor 3', '12', 3);

INSERT INTO tb_produto (codigo, descricao, data_de_cadastro, quantidade, grupo_id, preco) VALUES (1, 'Protetor auricular', NOW(), 5, 1, 10.99);
INSERT INTO tb_produto (codigo, descricao, data_de_cadastro, quantidade, grupo_id, preco) VALUES (2, 'Parafuso sextavado', NOW(), 60, 2, 6.02);
INSERT INTO tb_produto (codigo, descricao, data_de_cadastro, quantidade, grupo_id, preco) VALUES (3, 'CLP Siemens', NOW(), 9, 3, 4.01);
INSERT INTO tb_produto (codigo, descricao, data_de_cadastro, quantidade, grupo_id, preco) VALUES (4, 'Servidor Dell', NOW(), 1, 4, 10000);

INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (1, 1);
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (1, 2);
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (2, 1);
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (2, 3);
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (3, 1);
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (3, 2);
INSERT INTO tb_produto_fornecedor (produto_id, fornecedor_id) VALUES (3, 3);
