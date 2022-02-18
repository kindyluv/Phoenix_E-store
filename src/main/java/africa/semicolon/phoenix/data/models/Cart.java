package africa.semicolon.phoenix.data.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    private List<Item> itemList;

    private Double totalPrice;

    public void addItem(Item item){
        if(itemList == null) itemList = new ArrayList<>();
        itemList.add(item);
    }
}
