package interaction.cx.masdr.sa.backend.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QueryRunnerService {

    private final JdbcTemplate jdbcTemplate;

    public QueryRunnerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> executeQuery(String query) {
        // Validate the query before execution (e.g., allow only SELECT queries)
        if (!query.trim().toLowerCase().startsWith("select")) {
            throw new IllegalArgumentException("Only SELECT queries are allowed.");
        }

        // Execute the query and return the results
        return jdbcTemplate.queryForList(query);
    }
}
/*
package interaction.cx.masdr.sa.backend.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QueryRunnerService {

    private final JdbcTemplate jdbcTemplate;

    public QueryRunnerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Object executeQuery(String query) {
        String trimmedQuery = query.trim().toLowerCase();

        if (trimmedQuery.startsWith("select")) {
            // SELECT queries
            return jdbcTemplate.queryForList(query);
        } else if (trimmedQuery.startsWith("insert") ||
                trimmedQuery.startsWith("update") ||
                trimmedQuery.startsWith("delete")) {
            // INSERT, UPDATE, DELETE queries
            int rowsAffected = jdbcTemplate.update(query);
            return Map.of("rowsAffected", rowsAffected);
        } else if (trimmedQuery.startsWith("create") ||
                trimmedQuery.startsWith("alter") ||
                trimmedQuery.startsWith("drop")) {
            // DDL queries (e.g., CREATE, ALTER, DROP)
            jdbcTemplate.execute(query);
            return Map.of("message", "DDL query executed successfully.");
        } else {
            throw new IllegalArgumentException("Unsupported query type.");
        }
    }
}

*/