package org.example;

import java.util.List;

import org.example.elements.Column;
import org.example.elements.Join;
import org.example.elements.Sort;
import org.example.elements.Source;
import org.example.elements.WhereClause;


public class Query {
    private List<Column> selections;
    private List<Source> fromSources;
    private List<Join> joins;
    private List<WhereClause> whereClauses;
    private List<String> groupByColumns;
    private List<Sort> sortColumns;
    private Integer limit = 0;
    private Integer offset = 0;

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
        StringBuilder stringBuilder = new StringBuilder();
        if (getSelections() != null) {
            stringBuilder.append("SELECT ");
            for (int i = 0; i < getSelections().size(); i++) {
                if (getSelections().get(i).getTableAndColumnName() != null) {
                    String columnName = getSelections().get(i).getTableAndColumnName().keySet()
                            .stream().findFirst().get();
                    stringBuilder.append(columnName).append(" ");

                    if (!getSelections().get(i).getTableAndColumnName().get(columnName).equals("")) {
                        stringBuilder.append(".").append(getSelections().get(i).getTableAndColumnName()
                                                                                        .get(columnName)).append(" ");
                    }
                }
                if (getSelections().get(i).getIndexOfNestedQuery() != -1) {
                    stringBuilder.append("( ").append(Parser.getNestedQueries().get(getSelections().get(i)
                                    .getIndexOfNestedQuery()).toString()).append(") ");
                }
                if (getSelections().get(i).getAlias() != null) {
                    stringBuilder.append("AS ").append(getSelections().get(i).getAlias()).append(" ");
                }
            }
        }

        if (getFromSources() != null) {
            stringBuilder.append("\nFROM ");
            for (int i = 0; i < getFromSources().size(); i++) {
                if (getFromSources().get(i).getTableName() != null) {
                    stringBuilder.append(getFromSources().get(i).getTableName()).append(" ");
                }
                if (getFromSources().get(i).getIndexOfNestedQuery() != -1) {
                    stringBuilder.append("(").append(Parser.getNestedQueries().get(getFromSources().get(i)
                            .getIndexOfNestedQuery()).toString()).append(") ");
                }
                if (getFromSources().get(i).getAlias() != null) {
                    stringBuilder.append(getFromSources().get(i).getAlias()).append(" ");
                }
            }
        }

        if (getJoins() != null) {
            for (int i = 0; i < getJoins().size(); i++) {
                stringBuilder.append("\n").append(getJoins().get(i).getJoinType()).append(" ").append("JOIN ");
                stringBuilder.append(getJoins().get(i).getTable2()).append(" ");
                if (!getJoins().get(i).getJoinType().equals("CROSS")) {
                    stringBuilder.append("ON ")
                            .append(getJoins().get(i).getTable1()).append(".")
                            .append(getJoins().get(i).getColumnOfTable2()).append(" = ")
                            .append(getJoins().get(i).getTable2()).append(".")
                            .append(getJoins().get(i).getColumnOfTable1()).append(" ");
                }
            }
        }

        if (getWhereClauses() != null) {
            stringBuilder.append("\nWHERE ");
            stringBuilder.append(getWhereClauses().get(0).getObject()).append(" ");
            stringBuilder.append(String.join(" ", getWhereClauses().get(0).getClause())).append(" ");
            stringBuilder.append(String.join(" ", getWhereClauses().get(0).getValue())).append(" ");
            for (int i = 1; i < getWhereClauses().size(); i++) {
                stringBuilder.append("AND ").append(getWhereClauses().get(i).getObject()).append(" ");
                stringBuilder.append(String.join(" ", getWhereClauses().get(i).getClause())).append(" ");
                stringBuilder.append(String.join(" ", getWhereClauses().get(i).getValue())).append(" ");
            }
        }

        if (getGroupByColumns() != null) {
            stringBuilder.append("\nGROUP BY ");
            stringBuilder.append(String.join(" ", getGroupByColumns())).append(" ");
        }

        if (getSortColumns() != null) {
            stringBuilder.append("\nORDER BY ");
            for (int i = 0; i < getSortColumns().size(); i++) {
                stringBuilder.append(getSortColumns().get(i).getColumn()).append(" ");
                if (getSortColumns().get(i).getOrder() != null) {
                    stringBuilder.append(getSortColumns().get(i).getOrder()).append(" ");
                }
            }
        }
        if (getLimit() != 0) {
            stringBuilder.append("\nLIMIT ").append(getLimit());
        }
        if (getOffset() != 0) {
            stringBuilder.append("\nOFFSET").append(getOffset());
        }
        return stringBuilder.toString();
    }
}
