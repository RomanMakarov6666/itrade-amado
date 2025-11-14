package com.sale.iTrade.repositories;

import com.sale.iTrade.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);

    List<Product> findByTitleContainingIgnoreCase(String title);

    List<Product> findByDateOfCreatedAfter(LocalDateTime localDateTime);
}
