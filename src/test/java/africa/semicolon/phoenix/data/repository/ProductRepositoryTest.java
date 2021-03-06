package africa.semicolon.phoenix.data.repository;

import africa.semicolon.phoenix.data.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Sql(scripts={"/db/insert.sql"})
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }


    @Test
    @DisplayName("Save a new product to the database")
    void saveProductToDatabaseTest(){
        Product product = new Product();
        product.setName("Bamboo Chair");
        product.setDescription("World class bamboo");
        product.setPrice(5540);
        product.setQuantity(9);

        assertThat(product.getId()).isNull();
        productRepository.save(product);

        log.info("Product Saved :: {}", product);

        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("Bamboo Chair");
        assertThat(product.getPrice()).isEqualTo(5540);
        assertThat(product.getQuantity()).isEqualTo(9);
        assertThat(product.getDateCreated()).isNotNull();
    }

    @Test
    @DisplayName("Find an existing product from database")
    void findExistingProductFromDatabaseTest(){
        Product product = productRepository.findById(12L).orElse(null);

        assert product != null;
        productRepository.save(product);

        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(12);
        assertThat(product.getName()).isEqualTo("Luxury Map");
        assertThat(product.getPrice()).isEqualTo(2340);
        assertThat(product.getQuantity()).isEqualTo(3);

        log.info("Product retrieved :: {}", product);
    }

    @Test
    @DisplayName("Find all product in the database")
    void findAllProductsTest(){
        List<Product> productList = productRepository.findAll();
        assertThat(productList).isNotNull();
        assertThat(productList.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Find product by name")
    void findProductByName(){
        Product product = productRepository.findByName("Luxury Map").orElse(null);
        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(12);
        assertThat(product.getName()).isEqualTo("Luxury Map");
        assertThat(product.getPrice()).isEqualTo(2340);
        assertThat(product.getQuantity()).isEqualTo(3);
    }

    @Test
    @DisplayName("Update a product attributes test")
    void updateProductAttributesTest(){
        Product productId = productRepository.findByName("MacBook Air").orElse(null);
        assertThat(productId).isNotNull();
        assertThat(productId.getName()).isEqualTo("MacBook Air");
        assertThat(productId.getPrice()).isEqualTo(18340);
        productId.setName("MacBook Air 13");
        productId.setPrice(23420);

        productRepository.save(productId);
        assertThat(productId.getName()).isEqualTo("MacBook Air 13");
        assertThat(productId.getPrice()).isEqualTo(23420);
    }
}