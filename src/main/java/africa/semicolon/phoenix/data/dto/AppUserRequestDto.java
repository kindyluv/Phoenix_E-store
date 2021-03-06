package africa.semicolon.phoenix.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppUserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
}
