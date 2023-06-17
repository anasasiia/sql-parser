package org.example.elements;

import java.util.List;

public class WhereClause {
    private String object;
    private List<String> clause;
    private List<String> value;

    public String getObject() {
        return object;
    }

    public List<String> getClause() {
        return clause;
    }

    public List<String> getValue() {
        return value;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setClause(List<String> clause) {
        this.clause = clause;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
