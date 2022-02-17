package africa.semicolon.phoenix.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

    private Integer quantityAddedToCart;

    public Item(Product product, Integer quantityAddedToCart){
        if(quantityAddedToCart <= product.getQuantity()) this.quantityAddedToCart = quantityAddedToCart;
        else this.quantityAddedToCart=0;
        this.product=product;
    }

    public void setQuantity(int quantityAddedToCart){
       if(quantityAddedToCart <= product.getQuantity()) this.quantityAddedToCart = quantityAddedToCart;
   }
}
