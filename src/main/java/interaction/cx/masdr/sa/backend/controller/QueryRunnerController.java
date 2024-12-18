package interaction.cx.masdr.sa.backend.controller;

import interaction.cx.masdr.sa.backend.config.TenantContext;
import interaction.cx.masdr.sa.backend.mapper.QueryRequest;
import interaction.cx.masdr.sa.backend.model.CurrentState;
import interaction.cx.masdr.sa.backend.service.CurrentStateService;
import interaction.cx.masdr.sa.backend.service.QueryRunnerService;
import interaction.cx.masdr.sa.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class QueryRunnerController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    CurrentStateService currentStateService;
    private final QueryRunnerService queryRunnerService;

    public QueryRunnerController(QueryRunnerService queryRunnerService) {
        this.queryRunnerService = queryRunnerService;
    }

    @PostMapping("/query-runner/run")
    @CrossOrigin
    public ResponseEntity<?> runQuery(@RequestBody QueryRequest queryRequest,@RequestHeader("Authorization") String authorizationHeader,@RequestParam(required = false) String paramTenantId) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            Map<String,String> claimPropertiesMap= jwtUtil.getClaimPropertiesFromToken(token, Arrays.asList("tenantId","userId"));
            if(claimPropertiesMap.get("tenantId")==null){
                if(paramTenantId==null){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("error", "Tenant Id not available in Token and parameter"));
            }
            }
            String tenantId = claimPropertiesMap.get("tenantId");
            String userId = claimPropertiesMap.get("userId");
           if(tenantId==null){
               tenantId=paramTenantId;
           }
            TenantContext.setCurrentTenant(tenantId);
          //  log.info("Executing query for tenant: {} | Query: {}", tenant, queryRequest.getQuery());

            // Execute the query and handle results
            Object result = queryRunnerService.executeQuery(queryRequest.getQuery());
            currentStateService.saveCurrentState(new CurrentState(tenantId,queryRequest.toString()));
            TenantContext.clear();
            return ResponseEntity.ok(Map.of("tenant", tenantId, "result", result));
        } catch (Exception e) {
          //  log.error("Error executing query for tenant: {}", TenantContext.getCurrentTenant(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    @GetMapping("/getcurrentstate")
    public ResponseEntity<?> getCurrentState(@RequestHeader("Authorization") String authorizationHeader, @RequestParam(value = "graphId",required = false) String graphId,@RequestParam(required = false) String paramTenantId){
        String token = authorizationHeader.replace("Bearer ", "");

        Map<String,String> claimPropertiesMap= jwtUtil.getClaimPropertiesFromToken(token, Arrays.asList("tenantId","userId"));
        if(claimPropertiesMap.get("tenantId")!=null){
        TenantContext.setCurrentTenant(claimPropertiesMap.get("tenantId"));}
        else{if(paramTenantId!=null){
            TenantContext.setCurrentTenant(paramTenantId);}
            else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "tenantId required"));
        }
        }
      Object currentState=  currentStateService.getConfiguration(graphId);
      TenantContext.clear();
        return ResponseEntity.ok(Map.of("data",currentState));
    }
}
