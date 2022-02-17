package africa.semicolon.phoenix.data.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(length = 500)
    private String address;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @OneToOne(cascade = CascadeType.PERSIST)
    @Getter
    private final Cart myCart;

    public AppUser(){
        this.myCart = new Cart();
        this.myCart.setTotalPrice(0.0);
    }
}
