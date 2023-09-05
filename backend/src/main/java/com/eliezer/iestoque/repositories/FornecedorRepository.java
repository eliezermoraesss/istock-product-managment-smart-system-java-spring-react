package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Fornecedor;
import com.eliezer.iestoque.projections.FornecedorProdutoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_produto.descricao, tb_fornecedor.id, tb_fornecedor.razao_social AS razaoSocial
            FROM tb_produto
            INNER JOIN tb_produto_fornecedor ON tb_produto.id = tb_produto_fornecedor.produto_id
            INNER JOIN tb_fornecedor ON tb_fornecedor.id = tb_produto_fornecedor.fornecedor_id
            WHERE tb_produto.descricao LIKE CONCAT('%',:nomeProduto,'%')
            """)
    List<FornecedorProdutoProjection> findFornecedorByProduto(String nomeProduto);
}
