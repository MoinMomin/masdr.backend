package interaction.cx.masdr.sa.backend.mapper;
public class QueryRequest {
    private String query;

    // Getters and setters
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "QueryRequest{" +
                "query='" + query + '\'' +
                '}';
    }
}
