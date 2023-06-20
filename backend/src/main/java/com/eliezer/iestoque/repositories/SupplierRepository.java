package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Supplier;
import com.eliezer.iestoque.projections.SupplierProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_product.product_description AS productDescription, tb_supplier.id, tb_supplier.razao_social AS razaoSocial
            FROM tb_product
            INNER JOIN tb_product_supplier ON tb_product.id = tb_product_supplier.product_id
            INNER JOIN tb_supplier ON tb_supplier.id = tb_product_supplier.supplier_id
            WHERE tb_product.product_description LIKE CONCAT('%',:productName,'%')
            """)
    List<SupplierProductProjection> findSupplierByProduct(String productName);
}
