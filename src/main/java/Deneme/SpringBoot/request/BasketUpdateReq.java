package Deneme.SpringBoot.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketUpdateReq {
    private Long id;
    private Long productId;
    private Long piece;
}
