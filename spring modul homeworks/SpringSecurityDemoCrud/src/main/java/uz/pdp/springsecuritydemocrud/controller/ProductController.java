package uz.pdp.springsecuritydemocrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecuritydemocrud.entity.Product;
import uz.pdp.springsecuritydemocrud.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @PreAuthorize(value = "hasAuthority('VIEW_PRODUCTS')")
    @GetMapping()
    public HttpEntity<?> getProducts(){
        List<Product> laptops = productRepository.findAll();
        return ResponseEntity.status(200).body(laptops);
    }


    @PreAuthorize(value = "hasAuthority('VIEW_PRODUCTS')")
    @GetMapping("/{productId}")
    public HttpEntity<?> getProduct(@PathVariable Integer productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()){
            ResponseEntity.status(404).body("Product not found");
        }

        return ResponseEntity.status(200).body(optionalProduct.get());
    }

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping()
    public HttpEntity<?> getStudents(@RequestBody Product product){
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(202).body(savedProduct);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping("/{productId}")
    public HttpEntity<?> deleteProduct(@PathVariable Integer productId){
        if (!productRepository.findById(productId).isPresent()) return ResponseEntity.status(404).body("Product Not Found");

        try {
            productRepository.deleteById(productId);
        }catch (Exception e){
            return ResponseEntity.status(404).body("Error in deleting Product.");
        }

        return ResponseEntity.status(200).body("Product Successfully Deleted");
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping("/{productId}")
    public HttpEntity<?> editLaptop(@PathVariable Integer productId, @RequestBody Product product){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.status(404).body("Product not found");
        }

        Product editingProduct = optionalProduct.get();
        editingProduct.setName(product.getName());
        editingProduct.setDescription(product.getDescription());
        editingProduct.setPrice(product.getPrice());
        Product editedProduct = productRepository.save(editingProduct);

        return ResponseEntity.status(202).body(editedProduct);
    }

}
