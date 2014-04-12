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

    public static final String TABLE_NAME = "Table";

    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_TOKEN = "token";
    public static final String COLUMN_X = "x";
    public static final String COLUMN_Y = "y";
    public static final String COLUMN_FLOOR = "floor";

    private String id;
    private TableStatus status;
    private String token;
    private int x;
    private int y;

    public String getId() {
        return id;
    }

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
        parseObject.setObjectId(id);
        parseObject.put(COLUMN_STATUS, status.ordinal());
        parseObject.put(COLUMN_TOKEN, token);
        parseObject.put(COLUMN_X, x);
        parseObject.put(COLUMN_Y, y);
        return parseObject;
    }

    public static Table fromParseObject(ParseObject parseObject) throws ParseException {
        Table table = new Table();
        table.id = parseObject.getObjectId();
        table.status = TableStatus.values()[parseObject.getInt(COLUMN_STATUS)];
        table.token = parseObject.getString(COLUMN_TOKEN);
        table.x = parseObject.getInt(COLUMN_X);
        table.y = parseObject.getInt(COLUMN_Y);
        return table;
    }
}
