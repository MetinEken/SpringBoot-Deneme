package Deneme.SpringBoot.response;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuyingResponse {

    private Long buyingId;
    private boolean isSuccess;
    private String message;
}
