package org.example.elements;

import java.util.Map;

public class Column {
    private String alias;
    private Map<String, String> tableAndColumnName;

    private int indexOfNestedQuery = -1;

    public String getAlias() {
        return alias;
    }

    public Map<String, String> getTableAndColumnName() {
        return tableAndColumnName;
    }


    public int getIndexOfNestedQuery() {
        return indexOfNestedQuery;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setTableAndColumnName(Map<String, String> tableAndColumnName) {
        this.tableAndColumnName = tableAndColumnName;
    }

    public void setIndexOfNestedQuery(int indexOfNestedQuery) {
        this.indexOfNestedQuery = indexOfNestedQuery;
    }
}
