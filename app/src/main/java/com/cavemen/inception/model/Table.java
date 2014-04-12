package com.cavemen.inception.model;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a table in the office.
 * <p/>
 * Created by eandreevici on 4/12/2014.
 */
public class Table {

    private static final String TABLE_NAME = "Table";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_TOKEN = "token";
    private static final String COLUMN_EMPLOYEES = "employees";
    private static final String COLUMN_X = "x";
    private static final String COLUMN_Y = "y";

    private TableStatus status;
    private String token;
    private List<Person> employees;
    private int x;
    private int y;

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Person> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Person> employees) {
        this.employees = employees;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ParseObject toParseObject() {
        ParseObject parseObject = new ParseObject(TABLE_NAME);
        parseObject.put(COLUMN_STATUS, status.ordinal());
        parseObject.put(COLUMN_TOKEN, token);
        parseObject.put(COLUMN_EMPLOYEES, employees);
        parseObject.put(COLUMN_X, x);
        parseObject.put(COLUMN_Y, y);
        return parseObject;
    }

    public static Table fromParseObject(ParseObject parseObject) throws ParseException {
        Table table = new Table();
        table.status = TableStatus.values()[parseObject.getInt(COLUMN_STATUS)];
        table.token = parseObject.getString(COLUMN_TOKEN);
        table.employees = new ArrayList<Person>();
        List<ParseObject> employees = parseObject.getList(COLUMN_EMPLOYEES);
        if (employees != null) {
            for (ParseObject person : employees) {
                table.employees.add(Person.fromParseObject(person.fetchIfNeeded()));
            }
        }
        table.x = parseObject.getInt(COLUMN_X);
        table.y = parseObject.getInt(COLUMN_Y);
        return table;
    }
}
