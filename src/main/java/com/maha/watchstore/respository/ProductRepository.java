package com.maha.watchstore.respository;

import com.maha.watchstore.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT u FROM Product u JOIN FETCH u.discount WHERE u.id = :productId ")
    Optional<Product> findById(@Param("productId") Long id);
}
