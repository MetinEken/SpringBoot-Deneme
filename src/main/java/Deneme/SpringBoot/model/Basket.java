package Deneme.SpringBoot.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productId;
    private Long piece;

    //amazonda urun secilimi butonu var.
    // urun sepette ekli fakat secili degilse diger cihazlardan da secili olup olmadigi gosteriliyor, android haric
    public Basket(Long productId, Long piece){
        this.productId = productId;
        this.piece = piece;
    }


    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;


    public Basket(Long id, Long productId, Long piece) {
        this.id = id;
        this.productId = productId;
        this.piece = piece;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", productId=" + productId +
                ", piece=" + piece +
                ", user=" + user +
                '}';
    }
}
