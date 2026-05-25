package ceu.dam.ad.users.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserUnauthorizedException.class)
	public ResponseEntity<String> handle(UserUnauthorizedException e){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body("Login no autorizado. Causa: " + e.getMessage());
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handle(UserNotFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Usuario no encontrado. " + e.getMessage());
	}
	
	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<String> handle(DuplicateUserException e){
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body("Usuario duplicado. " + e.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handle(MethodArgumentNotValidException e){
		return ResponseEntity.badRequest()
				.body(e.getFieldErrors().stream().map(t -> t.getDefaultMessage()).collect(Collectors.joining("\n")));
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<String> handle(UserException e){
		log.error("Error accediendo a BBDD. Consultar traza ", e);
		return ResponseEntity.internalServerError()
				.body("Error al acceder a base de datos. " + e.getMessage());
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> handle(BadCredentialsException e) {
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body("Usuario o contraseña incorrectos");
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
	    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
	        .body(Map.of("error", "Método HTTP no permitido: " + ex.getMethod()));
	}
	
	//Token expirado
	@ExceptionHandler(io.jsonwebtoken.ExpiredJwtException.class)
	public ResponseEntity<String> handle(io.jsonwebtoken.ExpiredJwtException e) {
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body("Token expirado. Por favor, inicie sesión nuevamente.");
	}

	//Error con el token
	@ExceptionHandler(io.jsonwebtoken.JwtException.class)
	public ResponseEntity<String> handle(io.jsonwebtoken.JwtException e) {
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body("Token inválido: " + e.getMessage());
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handle(Exception e){
		log.error("Error inesperado. Consultar traza ", e);
		return ResponseEntity.internalServerError()
				.body("Error inesperado en el servidor. Consulte el log del servidor si tiene acceso");
	}
	
	
}
