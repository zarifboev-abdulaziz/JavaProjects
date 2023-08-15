package uz.pdp.springsecuritydemocrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springsecuritydemocrud.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
