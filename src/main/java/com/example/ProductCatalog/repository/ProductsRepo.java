package com.example.ProductCatalog.repository;

import com.example.ProductCatalog.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsRepo extends CrudRepository<Product,Long> {

    @Query(value = "select max(a.id) from products a",nativeQuery = true)
    Long findMaxId();

    List<Product> findProductByName(@Param("name") String name);
}
