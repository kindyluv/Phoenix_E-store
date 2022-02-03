package africa.semicolon.phoenix.data.repository;

import africa.semicolon.phoenix.data.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
