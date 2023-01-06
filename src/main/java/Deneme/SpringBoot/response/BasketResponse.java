package Deneme.SpringBoot.response;

import Deneme.SpringBoot.dao.BasketDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketResponse<T> {
    @Valid
    private List<T> basketList;

}
