package in.nk.tech.validation.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.nk.tech.validation.exception.UserNotFoundException;

@RestControllerAdvice
public class CustomExceptionAdvice {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgumentException(MethodArgumentNotValidException ex) {
		Map<String, String> invalidArgumentErrors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> invalidArgumentErrors.put(error.getField(), error.getDefaultMessage()));

		return invalidArgumentErrors;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserNotFoundException.class)
	public Map<String, String> handleUserNotFoundException(UserNotFoundException ex) {
		Map<String, String> userNotFound = new HashMap<>();
		userNotFound.put("errorMessage", ex.getMessage());

		return userNotFound;

	}

}
