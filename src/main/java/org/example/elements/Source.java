package org.example.elements;

public class Source {
    private String tableName;
    private String alias;
    private int indexOfNestedQuery = -1;

    public String getTableName() {
        return tableName;
    }
    public String getAlias() {
        return alias;
    }
    public int getIndexOfNestedQuery() {
        return indexOfNestedQuery;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public void setIndexOfNestedQuery(int indexOfNestedQuery) {
        this.indexOfNestedQuery = indexOfNestedQuery;
    }

}
