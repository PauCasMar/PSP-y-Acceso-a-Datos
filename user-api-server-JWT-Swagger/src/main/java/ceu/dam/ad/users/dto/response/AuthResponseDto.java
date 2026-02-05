package ceu.dam.ad.users.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
    private String token;
    private String type = "Bearer";
    private Long expiresIn;
    
    public AuthResponseDto(String token) {
        this.token = token;
    }
}