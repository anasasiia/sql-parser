package org.example.elements;

import org.example.ColumnSource;

public class Source extends ColumnSource {
    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
