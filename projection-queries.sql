--iSTOCK

--CONSULTANDO OS FORNECEDORES FILTRANDO PELA DESCRICAO DO PRODUTO
SELECT tb_product.id, tb_product.product_description, tb_supplier.id, tb_supplier.razao_social
FROM tb_product
INNER JOIN tb_product_supplier ON tb_product.id = tb_product_supplier.product_id
INNER JOIN tb_supplier ON tb_supplier.id = tb_product_supplier.supplier_id
WHERE tb_product.product_description LIKE '%erver 1%'


--CONSULTANDO OS PRODUTOS FILTRANDO PELA DESCRICAO DO FORNECEDOR
SELECT tb_product.id, tb_product.product_description, tb_supplier.id, tb_supplier.razao_social
FROM tb_product
INNER JOIN tb_product_supplier ON tb_product.id = tb_product_supplier.product_id
INNER JOIN tb_supplier ON tb_supplier.id = tb_product_supplier.supplier_id
WHERE tb_supplier.razao_social LIKE '%cedor 1%'