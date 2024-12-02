package interaction.cx.masdr.sa.backend.exceptionhandler;

import interaction.cx.masdr.sa.backend.exception.TenantNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = TenantNotFound.class)
    public ResponseEntity<Map> tenantNotFoundException(){
       Map<String,String> map= new HashMap<>();
       map.put("msg","tenant not found");
        return new ResponseEntity<>(map,HttpStatus.CONFLICT);
    }
}
