package org.example;

import java.util.List;

import org.example.elements.*;


public class Query {
    private List<Column> selections;
    private List<Source> fromSources;
    private List<Join> joins;
    private List<WhereClause> whereClauses;
    private List<String> groupByColumns;
    private List<Sort> sortColumns;
    private Integer limit;
    private Integer offset;

    public Query() {
    }

    public void setSelections(List<Column> selections) {
        this.selections = selections;
    }

    public List<Column> getSelections() {
        return selections;
    }

    public List<Source> getFromSources() {
        return fromSources;
    }

    public List<Join> getJoins() {
        return joins;
    }

    public List<WhereClause> getWhereClauses() {
        return whereClauses;
    }

    public List<String> getGroupByColumns() {
        return groupByColumns;
    }

    public List<Sort> getSortColumns() {
        return sortColumns;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setFromSources(List<Source> fromSources) {
        this.fromSources = fromSources;
    }

    public void setGroupByColumns(List<String> groupByColumns) {
        this.groupByColumns = groupByColumns;
    }

    public void setJoins(List<Join> joins) {
        this.joins = joins;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setSortColumns(List<Sort> sortColumns) {
        this.sortColumns = sortColumns;
    }

    public void setWhereClauses(List<WhereClause> whereClauses) {
        this.whereClauses = whereClauses;
    }


    public String toString() {
        String columns = "columns: ";
        for (int i = 0; i < getSelections().size(); i++) {
            if (getSelections().get(i).getName() != null) {
                columns += getSelections().get(i).getName() + " ";
            }
            if (getSelections().get(i).getTableAndColumnName() != null) {
                if (getSelections().get(i).getTableAndColumnName().containsValue("empty")) {
                    columns += getSelections().get(i).getTableAndColumnName().keySet() + " ";
                } else {
                    columns += getSelections().get(i).getTableAndColumnName().toString() + " ";
                }
            }
        }

        String sources = "from sources: ";
        for (int i = 0; i < getFromSources().size(); i++) {
            if (getFromSources().get(i).getTableName() != null) {
                sources = sources + "name of the table source " + getFromSources().get(i).getTableName() + " ";
            }
            if (getFromSources().get(i).getNestedQuery() != null) {
                sources = sources + "nested query " +
                        getFromSources().get(i).getNestedQuery().getSelections().get(0).getName() + " "
                        + getFromSources().get(i).getNestedQuery().getSelections().get(0).getTableAndColumnName() + " ";
            }
            if (getFromSources().get(i).getAlias() != null) {
                sources = sources + "alias name " + getFromSources().get(i).getAlias() + " ";
            }
        }

        String joins = "how to join: ";
        for (int i = 0; i < getJoins().size(); i++) {
            joins = joins + "joint type " + getJoins().get(i).getJoinType() + " ";
            joins = joins + "what table to join " + getJoins().get(i).getWhatTableToJoin() + " ";
            if (!getJoins().get(i).getJoinType().equals("CROSS")) {
                joins = joins + "table where to join " + getJoins().get(i).getTableWhereToJoin() + " ";
                joins = joins + "column of the table to join " + getJoins().get(i).getColumnOfTableToJoin() + " ";
                joins = joins + "column of the table where to join " + getJoins().get(i).getColumnWhereToJoin() + " ";
            }
        }

        String wheres = "where: ";
        for (int i = 0; i < getWhereClauses().size(); i++) {
            wheres = wheres + "object of where " + getWhereClauses().get(i).getObject() + " ";
            wheres = wheres + "clause " + getWhereClauses().get(i).getClause().toString() + " ";
            wheres = wheres + "values " + getWhereClauses().get(i).getValue().toString() + " ";
        }

        String groups = "group by:";
        for (int i = 0; i < getGroupByColumns().size(); i++) {
            groups = groups + " " + getGroupByColumns().get(i);
        }

        String sortColumns = "order by:";
        for (int i = 0; i < getSortColumns().size(); i++) {
            sortColumns = sortColumns + "column " + getSortColumns().get(i).getColumn() + " ";
            sortColumns = sortColumns + "order " + getSortColumns().get(i).getOrder() + " ";
        }
        return columns + "\n" + sources + "\n" + joins + "\n" + wheres + "\n" + groups + "\n" + sortColumns
                + "\noffset: " + getOffset() + "\nlimit: " + getLimit();
    }
}
