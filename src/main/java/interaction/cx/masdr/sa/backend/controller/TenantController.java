package interaction.cx.masdr.sa.backend.controller;

import interaction.cx.masdr.sa.backend.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TenantController {
    @Autowired
    TenantService tenantService;
    @GetMapping("/tenant/tenantlist")
    @CrossOrigin("*")
    public ResponseEntity<Map> getTenantlist(){
        return ResponseEntity.ok(Map.of("data",tenantService.getTenantList()));
    }
    @PutMapping("/hello")
    @CrossOrigin
    public String get(){
        return "hello.......";
    }
}
