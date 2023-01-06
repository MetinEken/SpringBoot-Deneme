package Deneme.SpringBoot.dao;


import Deneme.SpringBoot.model.BasketGuest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasketGuestDAO {

   List<BasketGuest> basket;
}
