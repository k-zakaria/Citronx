package com.capps.citronix.web.errors;


import com.capps.citronix.web.errors.farm.FarmExisteException;
import com.capps.citronix.web.errors.farm.FarmNotFoundException;
import com.capps.citronix.web.errors.field.FieldNotFoundException;
import com.capps.citronix.web.errors.field.MaxFieldAreaExceededException;
import com.capps.citronix.web.errors.field.MaxFieldsExceededException;
import com.capps.citronix.web.errors.harvest.HarvestAlreadyExistsException;
import com.capps.citronix.web.errors.harvest.HarvestNotFoundException;
import com.capps.citronix.web.errors.harvestdetails.HarvestDetailsNotFoundException;
import com.capps.citronix.web.errors.sale.SaleNotFoundException;
import com.capps.citronix.web.errors.tree.InvalidPlantingDateException;
import com.capps.citronix.web.errors.tree.MaxTreeDensityExceededException;
import com.capps.citronix.web.errors.tree.TreeNotFoundException;
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

    @ExceptionHandler(MaxFieldAreaExceededException.class)
    public ResponseEntity<String> handleMaxFieldAreaExceeded(MaxFieldAreaExceededException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MaxFieldsExceededException.class)
    public ResponseEntity<String> handleMaxFieldsExceeded(MaxFieldsExceededException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HarvestNotFoundException.class)
    public ResponseEntity<String> handleHarvestNotFound(HarvestNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HarvestAlreadyExistsException.class)
    public ResponseEntity<String> handleHarvestAlreadyExists(HarvestAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(HarvestDetailsNotFoundException.class)
    public ResponseEntity<String> handleHarvestDetailsNotFound(HarvestDetailsNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SaleNotFoundException.class)
    public ResponseEntity<String> handleSaleNotFound(SaleNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TreeNotFoundException.class)
    public ResponseEntity<String> handleTreeNotFound(TreeNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MaxTreeDensityExceededException.class)
    public ResponseEntity<String> handleMaxTreeDensityExceeded(MaxTreeDensityExceededException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPlantingDateException.class)
    public ResponseEntity<String> handleInvalidPlantingDate(InvalidPlantingDateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
