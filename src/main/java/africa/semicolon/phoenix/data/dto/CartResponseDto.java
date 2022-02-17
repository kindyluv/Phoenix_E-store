package africa.semicolon.phoenix.data.dto;

import africa.semicolon.phoenix.data.models.Item;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CartResponseDto {

    private List<Item> cartItems;

    private double totalPrice;

}
