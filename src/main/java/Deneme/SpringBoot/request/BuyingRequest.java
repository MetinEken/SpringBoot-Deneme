package Deneme.SpringBoot.request;

import Deneme.SpringBoot.model.Address;
import Deneme.SpringBoot.model.Basket;
import Deneme.SpringBoot.model.Sales.Sales;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Data
@NoArgsConstructor
public class BuyingRequest {


    Address address;
    Sales sales;
    List<Basket> basket;
}
