package org.example.elements;

import java.util.Map;

public class Column {
    private String name;
    private Map<String, String> tableAndColumnName;

    public Map<String, String> getTableAndColumnName() {
        return tableAndColumnName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTableAndColumnName(Map<String, String> tableAndColumnName) {
        this.tableAndColumnName = tableAndColumnName;
    }
}
