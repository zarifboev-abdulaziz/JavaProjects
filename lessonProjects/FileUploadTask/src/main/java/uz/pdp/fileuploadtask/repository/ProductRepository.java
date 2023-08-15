package uz.pdp.fileuploadtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.fileuploadtask.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
