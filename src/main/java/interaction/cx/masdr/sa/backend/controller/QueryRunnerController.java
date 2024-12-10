package interaction.cx.masdr.sa.backend.controller;

import interaction.cx.masdr.sa.backend.config.TenantContext;
import interaction.cx.masdr.sa.backend.mapper.QueryRequest;
import interaction.cx.masdr.sa.backend.model.CurrentState;
import interaction.cx.masdr.sa.backend.service.CurrentStateService;
import interaction.cx.masdr.sa.backend.service.QueryRunnerService;
import interaction.cx.masdr.sa.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/query-runner")
public class QueryRunnerController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    CurrentStateService currentStateService;
    private final QueryRunnerService queryRunnerService;

    public QueryRunnerController(QueryRunnerService queryRunnerService) {
        this.queryRunnerService = queryRunnerService;
    }

    @PostMapping("/run")
    @CrossOrigin
    public ResponseEntity<?> runQuery(@RequestBody QueryRequest queryRequest,@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");

            String tenantId = jwtUtil.getTenantIdFromToken(token);
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
    public ResponseEntity<?> getCurrentState(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");

        String tenantId = jwtUtil.getTenantIdFromToken(token);
        TenantContext.setCurrentTenant(tenantId);
      CurrentState currentState=  currentStateService.findByTenantId(tenantId);
      TenantContext.clear();
        return ResponseEntity.ok(Map.of("data",currentState));
    }
}
