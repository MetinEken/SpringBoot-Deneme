package Deneme.SpringBoot.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasketListReq<T> {
    @Valid
    private List<T> basketList;
// generic class lar icin guzel bir orenek, durabilir

}