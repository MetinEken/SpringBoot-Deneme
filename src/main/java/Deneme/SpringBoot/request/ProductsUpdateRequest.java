package Deneme.SpringBoot.request;

import lombok.*;


@Getter
@Setter

public class ProductsUpdateRequest {
    private Long id;
    private String name;
    private String type;
    private String fe1;
    private String fe2;
    private String fe3;
}
