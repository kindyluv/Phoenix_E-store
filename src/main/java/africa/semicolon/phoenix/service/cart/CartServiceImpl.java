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
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public CartResponseDto addItemToCart(CartRequestDto cartRequestDto) throws UserNotFoundException, ProductNotFoundException, BusinessLogicException {
        //check if user exist
        Optional<AppUser> query = appUserRepository.findById(cartRequestDto.getUserId());

        if(query.isEmpty()) throw new UserNotFoundException("User with ID "+ cartRequestDto.getUserId()+" not found");

        AppUser existingUser = query.get();
        //get user cart
        Cart myCart = existingUser.getMyCart();
        //check if product exist
        Product product = productRepository.findById(cartRequestDto.getProductId()).orElse(null);
        if(product == null) throw new ProductNotFoundException("Product with ID "+ cartRequestDto.getProductId()+" not found");

        if(!quantityIsValid(product, cartRequestDto.getQuantity())) throw new BusinessLogicException("Quantity too large");
        //add product to cart
        Item cartItem = new Item(product, cartRequestDto.getQuantity());
        myCart.addItem(cartItem);
        myCart.setTotalPrice(myCart.getTotalPrice() + calculateItemPrice(cartItem));
        //save cart
        cartRepository.save(myCart);
        return buildCartResponse(myCart);
    }

    private CartResponseDto buildCartResponse(Cart cart) {
        return CartResponseDto.builder()
                .cartItems(cart.getItemList())
                .totalPrice(cart.getTotalPrice())
                .build();
    }

    private Double calculateItemPrice(Item itemPrice){
        return itemPrice.getProduct().getPrice() * itemPrice.getQuantityAddedToCart();
    }

    private boolean quantityIsValid(Product product, int quantity){
        return product.getQuantity() >= quantity;
    }

    @Override
    public Cart viewCart() {
        return null;
    }

    @Override
    public CartResponseDto updateCartItem(CartUpdateDto updateDto) throws UserNotFoundException, BusinessLogicException {
//        Get a cart by userId/get user by id
          AppUser appUser = appUserRepository.findById(updateDto.getUserId()).orElse(null);
          if (appUser == null) throw new UserNotFoundException("User with ID "+updateDto.getUserId()+" not found");

//        get user cart
          Cart myCart = appUser.getMyCart();

//        Find an item within cart with itemId / find item in cart
          Item item = findCartItem(updateDto.getItemId(), myCart).orElse(null);
          if(item == null) throw new BusinessLogicException("Item not in cart");

//        perform operation
          if(updateDto.getQuantityOp() == QuantityOperation.INCREASE) {
              item.setQuantityAddedToCart(item.getQuantityAddedToCart() + 1);
              myCart.setTotalPrice(myCart.getTotalPrice() + item.getProduct().getPrice());
          }
          else if(updateDto.getQuantityOp() == QuantityOperation.DECREASE) item.setQuantityAddedToCart(item.getQuantityAddedToCart()-1);
          myCart.setTotalPrice(myCart.getTotalPrice() - item.getProduct().getPrice());

        cartRepository.save(myCart);
        return buildCartResponse(myCart);
    }

    private Optional<Item> findCartItem(Long itemId, Cart cart){
        Predicate<Item> itemPredicate = i -> i.getId().equals(itemId);
       return cart.getItemList().stream().filter(itemPredicate).findFirst();
    }

//    private Double updateItemPrice(QuantityOperation operation, Double currentTotal, Item item){
//        if(operation == QuantityOperation.INCREASE) return currentTotal + calculateItemPrice(item);
//        else()
//    }
}
