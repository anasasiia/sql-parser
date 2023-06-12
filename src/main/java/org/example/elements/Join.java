package org.example.elements;

public class Join {
    private String joinType;
    private String whatTableToJoin;
    private String tableWhereToJoin;
    private String columnWhereToJoin;
    private String columnOfTableToJoin;

    public String getColumnOfTableToJoin() {
        return columnOfTableToJoin;
    }

    public String getColumnWhereToJoin() {
        return columnWhereToJoin;
    }

    public String getJoinType() {
        return joinType;
    }

    public String getTableWhereToJoin() {
        return tableWhereToJoin;
    }

    public String getWhatTableToJoin() {
        return whatTableToJoin;
    }

    public void setColumnOfTableToJoin(String columnOfTableToJoin) {
        this.columnOfTableToJoin = columnOfTableToJoin;
    }

    public void setColumnWhereToJoin(String columnWhereToJoin) {
        this.columnWhereToJoin = columnWhereToJoin;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public void setTableWhereToJoin(String tableWhereToJoin) {
        this.tableWhereToJoin = tableWhereToJoin;
    }

    public void setWhatTableToJoin(String whatTableToJoin) {
        this.whatTableToJoin = whatTableToJoin;
    }
}
