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
            stringBuilder.append(QueryBuilder.stringBuilderSelections(getSelections()));
        }

        if (getFromSources() != null) {
            stringBuilder.append(QueryBuilder.stringBuilderFromSources(getFromSources()));
        }

        if (getJoins() != null) {
            stringBuilder.append(QueryBuilder.stringBuilderJoins(getJoins()));
        }

        if (getWhereClauses() != null) {
            stringBuilder.append(QueryBuilder.stringBuilderWhereClauses(getWhereClauses()));
        }

        if (getGroupByColumns() != null) {
            stringBuilder.append("\nGROUP BY ");
            stringBuilder.append(String.join(" ", getGroupByColumns())).append(" ");
        }

        if (getSortColumns() != null) {
            stringBuilder.append(QueryBuilder.stringBuilderOrderBy(getSortColumns()));
        }
        if (getLimit() != 0) {
            stringBuilder.append("\nLIMIT ").append(getLimit());
        }
        if (getOffset() != 0) {
            stringBuilder.append("\nOFFSET ").append(getOffset());
        }
        return stringBuilder.toString();
    }
}
