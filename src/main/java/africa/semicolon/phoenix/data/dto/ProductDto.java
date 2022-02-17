package africa.semicolon.phoenix.data.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.util.Date;

@Data
public class ProductDto {

    private String name;
    private String description;
    private double price;
    private int quantity;
    private MultipartFile image;
}
