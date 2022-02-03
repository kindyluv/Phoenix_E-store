package africa.semicolon.phoenix.data.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    private double price;
    private int quantity;

    @CreationTimestamp
    private Date dateCreated;
    private String imageUrl;
}
