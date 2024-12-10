package interaction.cx.masdr.sa.backend.controller;

import interaction.cx.masdr.sa.backend.config.TenantContext;
import interaction.cx.masdr.sa.backend.config.TenantRoutingDataSource;
import interaction.cx.masdr.sa.backend.model.Product;
import interaction.cx.masdr.sa.backend.service.ProductService;
import interaction.cx.masdr.sa.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    TenantRoutingDataSource tenantRoutingDataSource;
    @Autowired
    JwtUtil jwtUtil;
    @PostMapping("/createproduct")
    public ResponseEntity<Map> createProduct(@RequestBody Product product,@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");
        String tenantId = jwtUtil.getTenantIdFromToken(token);
        tenantRoutingDataSource.validateTenantDataSource(tenantId);
        TenantContext.setCurrentTenant(tenantId);
        productService.createProduct(product);
        TenantContext.clear();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/getproduct")
    public Product getProduct(@Param("productId") long productId,@RequestParam("tenantId") String tenantId){
        tenantRoutingDataSource.validateTenantDataSource(tenantId);
        TenantContext.setCurrentTenant(tenantId);
       Product product= productService.getProductByProductId(productId);
        TenantContext.clear();
        return product;
    }
}
