package Deneme.SpringBoot.model;


import Deneme.SpringBoot.model.Sales.Sales;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
public class SoldProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date date;
    private Long productId;
    private String name;
    private Long piece;
    private double price;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @JoinColumn(name = "sales_id")
    @ManyToOne
    Sales sales;


}
