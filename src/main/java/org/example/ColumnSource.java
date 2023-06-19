package org.example;

public class ColumnSource {
    private String alias;
    private int indexOfNestedQuery = -1;

    public String getAlias() {
        return alias;
    }

    public int getIndexOfNestedQuery() {
        return indexOfNestedQuery;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setIndexOfNestedQuery(int indexOfNestedQuery) {
        this.indexOfNestedQuery = indexOfNestedQuery;
    }
}
