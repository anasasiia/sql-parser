package org.example;

import org.example.elements.Column;
import org.example.elements.Join;
import org.example.elements.Sort;
import org.example.elements.Source;
import org.example.elements.WhereClause;

import java.util.List;

public class QueryBuilder {
    public static StringBuilder stringBuilderSelections(List<Column> selections) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        for (Column selection : selections) {
            if (selection.getTableAndColumnName() != null) {
                String columnName = selection.getTableAndColumnName().keySet()
                        .stream().findFirst().get();
                if (!selection.getTableAndColumnName().get(columnName).equals("")) {
                    sb.append(selection.getTableAndColumnName()
                            .get(columnName)).append(".");
                }
                sb.append(columnName).append(" ");
            }

            sb.append(getNestedQuery(selection));

            if (selection.getAlias() != null) {
                sb.append("AS ").append(selection.getAlias()).append(" ");
            }
        }
        return sb;
    }

    private static StringBuilder getNestedQuery(ColumnSource columnSource) {
        StringBuilder sb = new StringBuilder();
        if (columnSource.getIndexOfNestedQuery() != -1) {
            sb.append("( ").append(Parser.getNestedQueries().get(columnSource.getIndexOfNestedQuery())
                    .toString()).append(") ");
        }
        return sb;
    }

    public static StringBuilder stringBuilderFromSources(List<Source> fromSources) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nFROM ");

        for (Source fromSource : fromSources) {
            if (fromSource.getTableName() != null) {
                sb.append(fromSource.getTableName()).append(" ");
            }
            sb.append(getNestedQuery(fromSource));
            if (fromSource.getAlias() != null) {
                sb.append(fromSource.getAlias()).append(" ");
            }
        }
        return sb;
    }

    public static StringBuilder stringBuilderJoins(List<Join> joins) {
        StringBuilder sb = new StringBuilder();
        for (Join join : joins) {
            sb.append("\n").append(join.getJoinType()).append(" ").append("JOIN ");
            sb.append(join.getTable2()).append(" ");
            if (!join.getJoinType().equals("CROSS")) {
                sb.append("ON ")
                        .append(join.getTable1()).append(".")
                        .append(join.getColumnOfTable2()).append(" = ")
                        .append(join.getTable2()).append(".")
                        .append(join.getColumnOfTable1()).append(" ");
            }
        }
        return sb;
    }

    public static StringBuilder stringBuilderWhereClauses(List<WhereClause> whereClauses) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nWHERE ");
        sb.append(whereClauses.get(0).getObject()).append(" ");
        sb.append(String.join(" ", whereClauses.get(0).getClause())).append(" ");
        sb.append(String.join(" ", whereClauses.get(0).getValue())).append(" ");
        for (int i = 1; i < whereClauses.size(); i++) {
            sb.append("AND ").append(whereClauses.get(i).getObject()).append(" ");
            sb.append(String.join(" ", whereClauses.get(i).getClause())).append(" ");
            sb.append(String.join(" ", whereClauses.get(i).getValue())).append(" ");
        }
        return sb;
    }

    public static StringBuilder stringBuilderOrderBy(List<Sort> sortColumns) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nORDER BY ");
        for (Sort sortColumn : sortColumns) {
            sb.append(sortColumn.getColumn()).append(" ");
            if (sortColumn.getOrder() != null) {
                sb.append(sortColumn.getOrder()).append(" ");
            }
        }
        return sb;
    }
}
