package com.chromamon.analysis.handler;

import com.chromamon.analysis.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TransformerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTransformerNotFoundException(TransformerNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Transformer Not Found");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(TransformerAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleTransformerAlreadyExistsException(TransformerAlreadyExistsException ex){
        Map<String, String> response = new HashMap<>();
        response.put("error", "Transformer Already Exists");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(TransformerDataInsertionException.class)
    public ResponseEntity<Map<String, String>> handleTransformerDataInsertionException(TransformerDataInsertionException ex){
        Map<String, String> response = new HashMap<>();
        response.put("error", "Adding Transformer Data");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(TransformerPhaseConfigurationException.class)
    public ResponseEntity<Map<String, String>> handleTransformerPhaseConfigurationException(TransformerPhaseConfigurationException ex){
        Map<String, String> response = new HashMap<>();
        response.put("error", "Processing Transformer Configuration");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(TransformerDateNotLogicalException.class)
    public ResponseEntity<Map<String, String>> handleTransformerDateNotLogicalException(TransformerDateNotLogicalException ex){
        Map<String, String> response = new HashMap<>();
        response.put("error", "Requested Date Is Not Logical");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(TransformerIsolationRefrigerationException.class)
    public ResponseEntity<Map<String, String>> handleTransformerIsolationRefrigerationException(TransformerIsolationRefrigerationException ex){
        Map<String, String> response = new HashMap<>();
        response.put("error", "Oil Isolation Must Have Oil Refrigeration");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex){
        Map<String, String> response = new HashMap<>();
        response.put("error", "Argument Not Supported");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(TransformerIdsNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleTransformerIdsNotFoundException(TransformerIdsNotFoundException ex){
        Map<String, Object> response = new HashMap<>();
        response.put("warning", "Transformer Data not found in Database");
        response.put("ids", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
