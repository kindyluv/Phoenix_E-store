package africa.semicolon.phoenix.data.dto;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.util.Date;

public class ProductDto {

    private String name;
    private String description;
    private double price;
    private int quantity;
    private String imageUrl;
}
