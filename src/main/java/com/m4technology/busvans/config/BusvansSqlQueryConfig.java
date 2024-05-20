package com.m4technology.busvans.config;

import java.util.Map;
import java.util.Objects;

public class BusvansSqlQueryConfig {

    private Map<String, String> queries;

    public BusvansSqlQueryConfig(Map<String, String> queries) {
        this.queries = queries;
    }

    public String getQuery(String queryName) {
        if (!this.queries.containsKey(queryName)) {
            throw new IllegalArgumentException(String.format("Não existe query de nome '%s' configurada no sistema", queryName));
        } else {
            return (String)this.queries.get(queryName);
        }
    }

    public boolean isEmpty() {
        return Objects.isNull(this.queries) || this.queries.isEmpty();
    }
}
