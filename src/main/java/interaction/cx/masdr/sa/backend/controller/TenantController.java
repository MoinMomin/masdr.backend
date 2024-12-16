package interaction.cx.masdr.sa.backend.controller;

import interaction.cx.masdr.sa.backend.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/tenant")
public class TenantController {
    @Autowired
    TenantService tenantService;
    @GetMapping("/tenantlist")
    public ResponseEntity<Map> getTenantlist(){
        return ResponseEntity.ok(Map.of("data",tenantService.getTenantList()));
    }
}
