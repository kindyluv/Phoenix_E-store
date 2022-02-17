package africa.semicolon.phoenix.data.dto;

import lombok.Data;

@Data
public class CartRequestDto {
    private Long userId;
    private Long productId;
    private Integer quantity;
}
