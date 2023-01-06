package Deneme.SpringBoot.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
public class ProductReq {
    private String name;
    private String type;
    private String fe1;
    private String fe2;
    private String fe3;
}
