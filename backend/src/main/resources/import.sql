INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Eliezer', 'Brown', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Nádia', 'Green', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_address (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Flores', 300, 'Centro', 'Uberlândia', 'MG', '38400-000');
INSERT INTO tb_address (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Avenida Matos', 105, 'Centro', 'Uberlândia', 'MG', '38400-000');
INSERT INTO tb_address (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Avenida Floriano', 2106, 'Centro', 'Uberlândia', 'MG', '38400-000');
INSERT INTO tb_address (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 800, 'Centro', 'Mogi Mirim', 'SP', '38400-000');
INSERT INTO tb_address (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 900, 'Centro', 'Uberlândia', 'RJ', '38400-000');
INSERT INTO tb_address (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 1000, 'Centro', 'Uberlândia', 'AM', '38400-000');
INSERT INTO tb_address (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 777, 'Centro', 'Uberlândia', 'ES', '38400-000');
INSERT INTO tb_address (logradouro, numero, bairro, localidade, uf, cep) VALUES ('Rua das Palmeiras', 2, 'Centro', 'Uberlândia', 'RS', '38400-000');

INSERT INTO tb_group (group_code, group_name) VALUES (1, 'EPI');
INSERT INTO tb_group (group_code, group_name) VALUES (2, 'MECANICA');
INSERT INTO tb_group (group_code, group_name) VALUES (3, 'ELETRICA');
INSERT INTO tb_group (group_code, group_name) VALUES (4, 'ATIVOS');

INSERT INTO tb_supplier(cod_fornecedor, razao_social, cnpj, address_id) VALUES (1, 'Fornecedor 1', '123456789', 1);
INSERT INTO tb_supplier(cod_fornecedor, razao_social, cnpj, address_id) VALUES (2, 'Fornecedor 2', '12345', 2);
INSERT INTO tb_supplier(cod_fornecedor, razao_social, cnpj, address_id) VALUES (3, 'Fornecedor 3', '12', 3);

INSERT INTO tb_product (product_code, product_description, product_registration_date, product_quantity, group_id, product_price) VALUES (1, 'Protetor auricular', NOW(), 5, 1, 10.99);
INSERT INTO tb_product (product_code, product_description, product_registration_date, product_quantity, group_id, product_price) VALUES (2, 'Parafuso sextavado', NOW(), 60, 2, 6.02);
INSERT INTO tb_product (product_code, product_description, product_registration_date, product_quantity, group_id, product_price) VALUES (3, 'CLP Siemens', NOW(), 9, 3, 4.01);
INSERT INTO tb_product (product_code, product_description, product_registration_date, product_quantity, group_id, product_price) VALUES (4, 'Servidor Dell', NOW(), 1, 4, 10000);

INSERT INTO tb_product_supplier (product_id, supplier_id) VALUES (1, 1);
INSERT INTO tb_product_supplier (product_id, supplier_id) VALUES (1, 2);
INSERT INTO tb_product_supplier (product_id, supplier_id) VALUES (2, 1);
INSERT INTO tb_product_supplier (product_id, supplier_id) VALUES (2, 3);
INSERT INTO tb_product_supplier (product_id, supplier_id) VALUES (3, 1);
INSERT INTO tb_product_supplier (product_id, supplier_id) VALUES (3, 2);
INSERT INTO tb_product_supplier (product_id, supplier_id) VALUES (3, 3);


