package africa.semicolon.phoenix.service.cart;

import africa.semicolon.phoenix.data.dto.CartRequestDto;
import africa.semicolon.phoenix.data.dto.CartResponseDto;
import africa.semicolon.phoenix.data.models.AppUser;
import africa.semicolon.phoenix.data.models.Cart;
import africa.semicolon.phoenix.data.models.Item;
import africa.semicolon.phoenix.data.models.Product;
import africa.semicolon.phoenix.data.repository.AppUserRepository;
import africa.semicolon.phoenix.data.repository.CartRepository;
import africa.semicolon.phoenix.data.repository.ProductRepository;
import africa.semicolon.phoenix.web.Exceptions.BusinessLogicException;
import africa.semicolon.phoenix.web.Exceptions.ProductNotFoundException;
import africa.semicolon.phoenix.web.Exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Sql(scripts="/db/insert.sql")
class CartServiceImplTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartService cartService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void addItemToCart() throws UserNotFoundException, BusinessLogicException, ProductNotFoundException {
        CartRequestDto cartRequestDto = new CartRequestDto();
        cartRequestDto.setProductId(13L);
        cartRequestDto.setUserId(5011L);
        cartRequestDto.setQuantity(1);

        CartResponseDto cartResponseDto = cartService.addItemToCart(cartRequestDto);
        assertThat(cartResponseDto.getCartItems()).isNotNull();
        assertThat(cartResponseDto.getCartItems().size()).isEqualTo(1);
    }

    @Test
    void viewCart() {
    }
}