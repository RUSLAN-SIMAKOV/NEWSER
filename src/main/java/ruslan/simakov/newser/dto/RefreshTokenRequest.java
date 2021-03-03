package ruslan.simakov.newser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class RefreshTokenRequest {

    @NotBlank
    private String refreshToken;
    private String username;
}
