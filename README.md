![github actions](https://github.com/anasasiia/sql-parser/actions/workflows/github-actions.yml/badge.svg) <a href="https://codeclimate.com/github/anasasiia/sql-parser/maintainability"><img src="https://api.codeclimate.com/v1/badges/fbe15717c36d2ca141dd/maintainability" /></a>
# sql-parser

SQL-parser presents a random SELECT-statement as such class:
```
public class Query {
    private List<Column> selections;
    private List<Source> fromSources;
    private List<Join> joins;
    private List<WhereClause> whereClauses;
    private List<String> groupByColumns;
    private List<Sort> sortColumns;
    private Integer limit = 0;
    private Integer offset = 0;
}
```

### Which keywords are supported
- SELECT
- FROM
- WHERE
- JOIN
- GROUP BY
- ORDER BY
- LIMIT
- OFFSET

Also nested statements, aliases and inner, left, right, full, cross join.

### Example of SELECT-statement
```
SELECT author.name, book.id, book.cost 
FROM (SELECT * FROM classic.authors)
LEFT JOIN book ON (author.id = book.author_id)
WHERE book.cost > 500 AND book.id BETWEEN 10 AND 50
GROUP BY author.name 
ORDER BY book.id ASC
OFFSET 10
LIMIT 10
```
### Some syntax rules
1. Write your SELECT-statement into input.txt file. In ouput.txt file you will see the result of parsing.
2. Write every main keyword from a new line.
3. Nested statements need to be written in one line for a better parsing. 
4. Use keyword AS only for naming columns, not tables.

### Requirements
- JDK 17
- Gradle 7

### Clone repository
```
git clone git@github.com:anasasiia/sql-parser.git
```
### Build application
```
make build
```
### Run checkstyle
```
make lint
```
### Run
```
make run
```
