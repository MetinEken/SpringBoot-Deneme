package Deneme.SpringBoot.model.Sales;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date date;
    private double amount;
    private String money;
    private String payMethod;
    private Long userId;
    private Long addressId;

//    @JoinColumn(name = "payMethod")
//    @OneToOne(fetch = FetchType.EAGER)
//    private PayMethod pay;



//    @JoinColumn(name = "address_id")
//    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Address address;


//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="user_id")
//    private User user;
//
//private List<Transaction> products;

}
