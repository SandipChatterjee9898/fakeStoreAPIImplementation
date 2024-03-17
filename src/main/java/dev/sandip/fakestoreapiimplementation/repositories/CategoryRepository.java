package dev.sandip.fakestoreapiimplementation.repositories;

import dev.sandip.fakestoreapiimplementation.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByTitle(String title);
    Category save(Category c);
    List<Category> findAll();

}
