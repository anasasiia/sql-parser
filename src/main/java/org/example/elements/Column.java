package org.example.elements;

import java.util.Map;

public class Column extends ColumnSource {
    private Map<String, String> tableAndColumnName;

    public Map<String, String> getTableAndColumnName() {
        return tableAndColumnName;
    }

    public void setTableAndColumnName(Map<String, String> tableAndColumnName) {
        this.tableAndColumnName = tableAndColumnName;
    }
}
