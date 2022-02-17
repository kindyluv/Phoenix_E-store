package africa.semicolon.phoenix.data.repository;

import africa.semicolon.phoenix.data.models.Cart;
import africa.semicolon.phoenix.data.models.Item;
import africa.semicolon.phoenix.data.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts="/db/insert.sql")
class CartRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Add item to cart")
    void addProductToCart(){
        Product product = productRepository.findByName("MacBook Air").orElse(null);
        assertThat(product).isNotNull();

        Item item = new Item(product,2);

        assertThat(item.getProduct()).isNotNull();
        Cart cart = new Cart();
        cart.addItem(item);

        cartRepository.save(cart);
        assertThat(cart.getId()).isNotNull();
        assertThat(cart.getItemList().get(0).getProduct()).isNotNull();
        log.info("cart object saved --> {}", cart);

    }

    @Test
//    @Transactional
    @DisplayName("View all items in a cart")
    void viewItemsInCartTest(){
        //get a cart by id
        Cart savedCart = cartRepository.findById(345L).orElse(null);
        assertThat(savedCart).isNotNull();
        assertThat(savedCart.getItemList().size()).isEqualTo(3);
        //print items
        log.info("cart Retrieved from DB :: {}", savedCart);
    }
}