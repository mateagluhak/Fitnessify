package fer.infsus.fitnessify.validation.handler;

import fer.infsus.fitnessify.validation.exception.InvalidExerciseException;
import fer.infsus.fitnessify.validation.exception.InvalidMuscleGroupException;
import fer.infsus.fitnessify.validation.exception.InvalidPriorityException;
import fer.infsus.fitnessify.validation.exception.InvalidRepetitionsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidExerciseException.class)
    public ResponseEntity<Map<String, List<String>>> handleInvalidExerciseError(InvalidExerciseException ex) {
        return new ResponseEntity<>(getErrorsMap(List.of(ex.getErrorMessage())), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidMuscleGroupException.class)
    public ResponseEntity<Map<String, List<String>>> handleInvalidMuscleGroupError(InvalidMuscleGroupException ex) {
        return new ResponseEntity<>(getErrorsMap(List.of(ex.getErrorMessage())), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPriorityException.class)
    public ResponseEntity<Map<String, List<String>>> handleInvalidPriorityError(InvalidPriorityException ex) {
        return new ResponseEntity<>(getErrorsMap(List.of(ex.getErrorMessage())), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRepetitionsException.class)
    public ResponseEntity<Map<String, List<String>>> handleInvalidRepetitionsError(InvalidRepetitionsException ex) {
        return new ResponseEntity<>(getErrorsMap(List.of(ex.getErrorMessage())), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
