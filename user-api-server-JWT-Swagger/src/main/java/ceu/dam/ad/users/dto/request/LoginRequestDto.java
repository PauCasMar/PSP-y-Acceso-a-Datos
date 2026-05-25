package ceu.dam.ad.users.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class LoginRequestDto {
	

	
	@NotEmpty(message = "El username es obligatorio")
	@Schema(description = "Username o email")
    private String username;
    
	@NotEmpty(message = "El password es obligatorio")
	@Schema(description = "Password en claro sin cifrar")
	private String password;
}