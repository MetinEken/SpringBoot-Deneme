package Deneme.SpringBoot.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SoldProductDAO {
    private Long id;
    private Date date;
    private Long productId;
    private double price;
    private Long piece;
    private double amount;


}
