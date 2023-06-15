create sequence tb_request_product_seq start with 1 increment by 50;
create table tb_address (id bigserial not null, bairro varchar(255), cep varchar(255), cidade varchar(255), estado varchar(255), logradouro varchar(255), numero integer, pais varchar(255), supplier_id bigint, primary key (id));
create table tb_center_cost (id bigserial not null, cc_code integer, cc_name varchar(255), primary key (id));
create table tb_departament (id bigserial not null, cod_departamento integer, descricao_departamento varchar(255), primary key (id));
create table tb_employee (id bigserial not null, matricula integer, nome varchar(255), departament_id bigint, primary key (id));
create table tb_group (id bigserial not null, group_code integer, group_name varchar(255), primary key (id));
create table tb_ncm (id bigserial not null, ncm_code varchar(255), ncm_description varchar(255), primary key (id));
create table tb_order_item (quantity numeric(38,2), sub_total numeric(38,2), product_order_id bigint not null, product_id bigint not null, primary key (product_id, product_order_id));
create table tb_product (id bigserial not null, product_code varchar(255), product_description varchar(255), product_price numeric(38,2), product_quantity numeric(38,2), product_registration_date timestamp(6) with time zone, status_produto varchar(255), product_weigth numeric(38,2), center_cost_id bigint, group_id bigint, ncm_id bigint, unity_id bigint, primary key (id));
create table tb_product_supplier (product_id bigint not null, supplier_id bigint not null, primary key (product_id, supplier_id));
create table tb_request_product (id bigint not null, order_date timestamp(6) with time zone, primary key (id));
create table tb_role (id bigserial not null, authority varchar(255), primary key (id));
create table tb_supplier (id bigserial not null, cnpj varchar(255), cod_fornecedor integer, inscricao_estadual varchar(255), razao_social varchar(255), primary key (id));
create table tb_unity (id bigserial not null, unity_code varchar(255), unity_description varchar(255), primary key (id));
create table tb_user (id bigserial not null, user_email varchar(255), user_password varchar(255), primary key (id));
create table tb_user_role (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id));
alter table if exists tb_address add constraint FK7fscyj684gdc78ih513mq6lgt foreign key (supplier_id) references tb_supplier;
alter table if exists tb_employee add constraint FKfuftn90dkauqxvpva7d9bqmhb foreign key (departament_id) references tb_departament;
alter table if exists tb_order_item add constraint FK1kiais5tdjpyipvuj0qckmysu foreign key (product_order_id) references tb_request_product;
alter table if exists tb_order_item add constraint FK4h5xid5qehset7qwe5l9c997x foreign key (product_id) references tb_product;
alter table if exists tb_product add constraint FKnna96x4ubcj7fti81310yeit1 foreign key (center_cost_id) references tb_center_cost;
alter table if exists tb_product add constraint FKq56eyl7v440kcgfna6j9d24jr foreign key (group_id) references tb_group;
alter table if exists tb_product add constraint FKxh9m4ne9q5p5cfx0d0bf9ix4 foreign key (ncm_id) references tb_ncm;
alter table if exists tb_product add constraint FKrcg0qwq5yq9um1x84h8u957m foreign key (unity_id) references tb_unity;
alter table if exists tb_product_supplier add constraint FKsuny6mdqxe9u2mkikv5eudgor foreign key (supplier_id) references tb_supplier;
alter table if exists tb_product_supplier add constraint FKhm9dbkht6uggfk477bmuhlkq4 foreign key (product_id) references tb_product;
alter table if exists tb_user_role add constraint FKea2ootw6b6bb0xt3ptl28bymv foreign key (role_id) references tb_role;
alter table if exists tb_user_role add constraint FK7vn3h53d0tqdimm8cp45gc0kl foreign key (user_id) references tb_user;
INSERT INTO tb_address (logradouro, numero, bairro, cidade, estado, pais, cep) VALUES ('Rua das Flores', 300, 'Centro', 'Uberlândia', 'MG', 'Brasil', '38400-000');
INSERT INTO tb_address (logradouro, numero, bairro, cidade, estado, pais, cep) VALUES ('Avenida Matos', 105, 'Centro', 'Uberlândia', 'MG', 'Brasil', '38400-000');
INSERT INTO tb_address (logradouro, numero, bairro, cidade, estado, pais, cep) VALUES ('Avenida Floriano', 2106, 'Centro', 'Uberlândia', 'MG', 'Brasil', '38400-000');
INSERT INTO tb_address (logradouro, numero, bairro, cidade, estado, pais, cep) VALUES ('Rua das Palmeiras', 800, 'Centro', 'Mogi Mirim', 'SP', 'Brasil', '38400-000');
INSERT INTO tb_address (logradouro, numero, bairro, cidade, estado, pais, cep) VALUES ('Rua das Palmeiras', 900, 'Centro', 'Uberlândia', 'RJ', 'Brasil', '38400-000');
INSERT INTO tb_address (logradouro, numero, bairro, cidade, estado, pais, cep) VALUES ('Rua das Palmeiras', 1000, 'Centro', 'Uberlândia', 'AM', 'Brasil', '38400-000');
INSERT INTO tb_address (logradouro, numero, bairro, cidade, estado, pais, cep) VALUES ('Rua das Palmeiras', 777, 'Centro', 'Uberlândia', 'ES', 'Brasil', '38400-000');
INSERT INTO tb_address (logradouro, numero, bairro, cidade, estado, pais, cep) VALUES ('Rua das Palmeiras', 2, 'Centro', 'Uberlândia', 'RS', 'Brasil', '38400-000');
INSERT INTO tb_group (group_code, group_name) VALUES (1, 'EPI');
INSERT INTO tb_group (group_code, group_name) VALUES (2, 'MECANICA');
INSERT INTO tb_group (group_code, group_name) VALUES (3, 'ELETRICA');
INSERT INTO tb_group (group_code, group_name) VALUES (4, 'ATIVOS');
INSERT INTO tb_supplier(cod_fornecedor, razao_social, cnpj) VALUES (1, 'Fornecedor 1', '123456789');
INSERT INTO tb_supplier(cod_fornecedor, razao_social, cnpj) VALUES (2, 'Fornecedor 2', '123456789');
INSERT INTO tb_supplier(cod_fornecedor, razao_social, cnpj) VALUES (3, 'Fornecedor 3', '123456789');
INSERT INTO tb_product (product_code, product_description, product_registration_date, product_quantity, group_id, product_price) VALUES (1, 'Protetor auricular', NOW(), 5, 1, 10.99);
INSERT INTO tb_product (product_code, product_description, product_registration_date, product_quantity, group_id, product_price) VALUES (2, 'Parafuso sextavado', NOW(), 60, 2, 6.02);
INSERT INTO tb_product (product_code, product_description, product_registration_date, product_quantity, group_id, product_price) VALUES (3, 'CLP Siemens', NOW(), 9, 3, 4.01);
INSERT INTO tb_product (product_code, product_description, product_registration_date, product_quantity, group_id, product_price) VALUES (444, 'Servidor Dell', NOW(), 1, 4, 10000);
INSERT INTO tb_product_supplier (product_id, supplier_id) VALUES (1, 1);
INSERT INTO tb_product_supplier (product_id, supplier_id) VALUES (1, 2);
INSERT INTO tb_product_supplier (product_id, supplier_id) VALUES (2, 1);
INSERT INTO tb_product_supplier (product_id, supplier_id) VALUES (2, 3);
INSERT INTO tb_product_supplier (product_id, supplier_id) VALUES (3, 1);
INSERT INTO tb_product_supplier (product_id, supplier_id) VALUES (3, 2);
INSERT INTO tb_product_supplier (product_id, supplier_id) VALUES (3, 3);