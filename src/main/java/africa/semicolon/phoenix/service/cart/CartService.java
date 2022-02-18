package africa.semicolon.phoenix.service.cart;

import africa.semicolon.phoenix.data.dto.CartRequestDto;
import africa.semicolon.phoenix.data.dto.CartResponseDto;
import africa.semicolon.phoenix.data.dto.CartUpdateDto;
import africa.semicolon.phoenix.data.models.Cart;
import africa.semicolon.phoenix.web.Exceptions.BusinessLogicException;
import africa.semicolon.phoenix.web.Exceptions.ProductNotFoundException;
import africa.semicolon.phoenix.web.Exceptions.UserNotFoundException;

public interface CartService {
    CartResponseDto addItemToCart(CartRequestDto cart) throws UserNotFoundException, ProductNotFoundException, BusinessLogicException;
    Cart viewCart();
    CartResponseDto updateCartItem(CartUpdateDto updateDto) throws UserNotFoundException, BusinessLogicException;
}
