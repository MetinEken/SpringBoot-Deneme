package Deneme.SpringBoot.dao;

import Deneme.SpringBoot.model.Address;
import Deneme.SpringBoot.model.Sales.PayMethod;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalesDAO {
    private Long id;
    private Date date;
    private double amount;
    private PayMethod payId;
    private Address addressId;

}
