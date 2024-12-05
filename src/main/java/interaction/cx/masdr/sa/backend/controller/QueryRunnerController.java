package interaction.cx.masdr.sa.backend.controller;

import interaction.cx.masdr.sa.backend.config.TenantContext;
import interaction.cx.masdr.sa.backend.mapper.QueryRequest;
import interaction.cx.masdr.sa.backend.service.QueryRunnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/query-runner")
public class QueryRunnerController {

    private final QueryRunnerService queryRunnerService;

    public QueryRunnerController(QueryRunnerService queryRunnerService) {
        this.queryRunnerService = queryRunnerService;
    }

    @PostMapping("/run")
    public ResponseEntity<?> runQuery(@RequestBody QueryRequest queryRequest) {
        try {

            TenantContext.setCurrentTenant("tenant9");

          //  log.info("Executing query for tenant: {} | Query: {}", tenant, queryRequest.getQuery());

            // Execute the query and handle results
            Object result = queryRunnerService.executeQuery(queryRequest.getQuery());
            return ResponseEntity.ok(Map.of("tenant", "tenant9", "result", result));
        } catch (Exception e) {
          //  log.error("Error executing query for tenant: {}", TenantContext.getCurrentTenant(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
