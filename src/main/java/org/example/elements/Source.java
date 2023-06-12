package org.example.elements;

import org.example.Query;

public class Source {
    private String tableName;
    private Query nestedQuery;
    private String alias;

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setNestedQuery(Query nestedQuery) {
        this.nestedQuery = nestedQuery;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public Query getNestedQuery() {
        return nestedQuery;
    }

    public String getAlias() {
        return alias;
    }
}
