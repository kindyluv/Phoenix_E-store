package africa.semicolon.phoenix.service.cart;

import africa.semicolon.phoenix.data.dto.CartRequestDto;
import africa.semicolon.phoenix.data.dto.CartResponseDto;
import africa.semicolon.phoenix.data.dto.CartUpdateDto;
import africa.semicolon.phoenix.data.dto.QuantityOperation;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;
import java.util.function.Predicate;

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

    CartUpdateDto updateDto;

    @BeforeEach
    void setUp() {
        updateDto = CartUpdateDto.builder()
                .itemId(122L)
                .quantityOp(QuantityOperation.INCREASE)
                .userId(5005L).build();
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

        assertThat(cartResponseDto.getTotalPrice()).isNotEqualTo(0.0);
        assertThat(cartResponseDto.getTotalPrice()).isEqualTo(500);
    }

    @Test
    @DisplayName("Cart Price Updated test")
    void updateCartPriceTest() throws UserNotFoundException, BusinessLogicException, ProductNotFoundException {
        CartRequestDto cartRequestDto = new CartRequestDto();
        cartRequestDto.setProductId(13L);
        cartRequestDto.setUserId(5011L);
        cartRequestDto.setQuantity(2);

        CartResponseDto cartResponseDto = cartService.addItemToCart(cartRequestDto);
        assertThat(cartResponseDto.getCartItems()).isNotNull();
        assertThat(cartResponseDto.getCartItems().size()).isEqualTo(1);

        assertThat(cartResponseDto.getTotalPrice()).isNotEqualTo(0.0);
        assertThat(cartResponseDto.getTotalPrice()).isEqualTo(1000);
    }

    @Test
    void viewCart() {

    }

    @Test
    @DisplayName("Update cart item quantity")
    void updateCartItemTest() throws UserNotFoundException, BusinessLogicException {
        AppUser appUser = appUserRepository.findById(updateDto.getUserId()).orElse(null);

        assert appUser != null;
        Cart userCart = appUser.getMyCart();
//        assertThat(userCart.getItemList().size()).isEqualTo(2);
        Item item = userCart.getItemList().get(0);
        log.info("Item --> {}", item);
        assertThat(item).isNotNull();
        assertThat(item.getQuantityAddedToCart()).isEqualTo(2);
        log.info("Cart update obj --> {}", userCart);
//        get item
//        for (int i = 0; i < userCart.getItemList().size(); i++) {
//            item = userCart.getItemList().get(i);
//            if (item.getId() == updateDto.getUserId()) break;
//        }

//        Predicate<Item> itemPredicate = i -> i.getId().equals(updateDto.getItemId());
//        Optional<Item> optionalItem = userCart.getItemList().stream().filter(itemPredicate).findFirst();


        CartResponseDto responseDto = cartService.updateCartItem(updateDto);
        assertThat(responseDto.getCartItems()).isNotNull();
        assertThat(responseDto.getCartItems().get(0).getQuantityAddedToCart()).isEqualTo(2);

//        log.info("Cart update obj -> {}", updateDto);
//
//
//        for (int i = 0; i < responseDto.getCartItems().size(); i++) {
//            item = responseDto.getCartItems().get(i);
//            if (item.getId() == updateDto.getUserId()) break;
//        }
//
//        assertThat(item).isNotNull();
//        assertThat(item.getQuantityAddedToCart()).isEqualTo(4);
    }
}