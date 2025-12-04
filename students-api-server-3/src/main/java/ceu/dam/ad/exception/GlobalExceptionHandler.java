package ceu.dam.ad.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ceu.dam.ad.service.StudentDuplicateException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<String> handle(StudentDuplicateException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("student already exists.Cause: " + e.getMessage());
	}

	@ExceptionHandler
	public ResponseEntity<String> handle(MethodArgumentNotValidException e) {
		return ResponseEntity.badRequest()
				.body(e.getFieldErrors().stream().map(t -> t.getDefaultMessage()).collect(Collectors.joining(", ")));
	}

	@ExceptionHandler
	public ResponseEntity<String> handle(Exception e) {
		log.error("Unexpected error", e);

		return ResponseEntity.internalServerError().body("Unexpected server error");
	}
}
