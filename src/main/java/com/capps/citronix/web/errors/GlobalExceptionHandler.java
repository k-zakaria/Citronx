package com.capps.citronix.web.errors;


import com.capps.citronix.web.errors.farm.FarmExisteException;
import com.capps.citronix.web.errors.farm.FarmNotFoundException;
import com.capps.citronix.web.errors.field.FieldNotFoundException;
import com.capps.citronix.web.errors.harvest.HarvestNotFoundException;
import com.capps.citronix.web.errors.harvestdetails.HarvestDetailsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FarmNotFoundException.class)
    public ResponseEntity<String> FarmNotFound(FarmNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FarmExisteException.class)
    public ResponseEntity<String> FarmExiste(FarmExisteException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FieldNotFoundException.class)
    public ResponseEntity<String> handleFieldNotFound(FieldNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HarvestNotFoundException.class)
    public ResponseEntity<String> handleHarvestNotFound(HarvestNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HarvestDetailsNotFoundException.class)
    public ResponseEntity<String> handleHarvestDetailsNotFound(HarvestDetailsNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }




}
