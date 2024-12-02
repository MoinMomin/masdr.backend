package interaction.cx.masdr.sa.backend.repository;

import interaction.cx.masdr.sa.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    // Find a product by its name
    Optional<Product> findByName(String name);
    //public Product save(Product product);

   // public Product save(Product product);
    void deleteByName(String name);

    // Check if a product with a specific name exists
    boolean existsByName(String name);
}