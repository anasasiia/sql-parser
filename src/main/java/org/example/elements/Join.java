package org.example.elements;

public class Join {
    private String joinType;
    private String table1;
    private String table2;
    private String columnOfTable1;
    private String columnOfTable2;

    public String getColumnOfTable2() {
        return columnOfTable2;
    }

    public String getColumnOfTable1() {
        return columnOfTable1;
    }

    public String getJoinType() {
        return joinType;
    }

    public String getTable2() {
        return table2;
    }

    public String getTable1() {
        return table1;
    }

    public void setColumnOfTable2(String columnOfTable2) {
        this.columnOfTable2 = columnOfTable2;
    }

    public void setColumnOfTable1(String columnOfTable1) {
        this.columnOfTable1 = columnOfTable1;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public void setTable2(String table2) {
        this.table2 = table2;
    }

    public void setTable1(String table1) {
        this.table1 = table1;
    }
}
