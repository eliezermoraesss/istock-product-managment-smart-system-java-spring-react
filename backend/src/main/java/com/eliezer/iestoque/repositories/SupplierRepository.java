package com.eliezer.iestoque.repositories;

import com.eliezer.iestoque.entities.Supplier;
import com.eliezer.iestoque.projections.SupplierByProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_supplier.id, tb_supplier.cod_fornecedor, tb_product.product_code,
            tb_product.product_description
            FROM tb_supplier
            INNER JOIN tb_product ON tb_supplier.id = tb_product.supplier_id
            WHERE tb_product.product_description LIKE LOWER(CONCAT('%',:nameProduct,'%'))    
            """)
    List<SupplierByProductDTO> searchSupplierByProduct(String nameProduct);

}
