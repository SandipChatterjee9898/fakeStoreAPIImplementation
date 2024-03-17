package dev.sandip.fakestoreapiimplementation.repositories;

import dev.sandip.fakestoreapiimplementation.models.Category;
import dev.sandip.fakestoreapiimplementation.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product p);
    List<Product> findAll();
    Product findByIdIs(Long id);

}
