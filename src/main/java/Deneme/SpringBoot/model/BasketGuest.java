package Deneme.SpringBoot.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "basket_guest")
@Data
@NoArgsConstructor
public class BasketGuest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productId;
    private Long piece;
    private String userJwt;




    public BasketGuest(Long productId, Long piece) {
        this.productId = productId;
        this.piece =piece;
    }
}
