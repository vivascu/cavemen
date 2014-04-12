package com.cavemen.inception.model;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a floor in a DU's office.
 * <p/>
 * Created by eandreevici on 4/12/2014.
 */
public class Floor {

    public static final String TABLE_NAME = "Floor";

    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TABLES = "tables";

    private int number;
    private String name;
    private List<Table> tables;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Table> getTables() {
        return Collections.unmodifiableList(tables);
    }

    public void setTables(List<Table> tables) {
        this.tables = new ArrayList<Table>(tables);
    }

    public int percentageOfOccupiedSeats() {
        int total = tables.size();
        int occupied = 0;
        for (Table table : tables) {
            if (!table.getStatus().equals(TableStatus.EMPTY)) {
                occupied++;
            }
        }
        return (int) (((float) occupied / total) * 100);
    }

    public ParseObject toParseObject() {
        ParseObject parseObject = new ParseObject(TABLE_NAME);
        parseObject.put(COLUMN_NUMBER, number);
        parseObject.put(COLUMN_NAME, name);
        parseObject.put(COLUMN_TABLES, tables);
        return parseObject;
    }

    public static Floor fromParseObject(ParseObject parseObject) throws ParseException {
        Floor floor = new Floor();
        floor.number = parseObject.getInt(COLUMN_NUMBER);
        floor.name = parseObject.getString(COLUMN_NAME);
        floor.tables = new ArrayList<Table>();
        List<ParseObject> tableObjects = parseObject.getList(COLUMN_TABLES);
        if (tableObjects != null) {
            for (ParseObject table : tableObjects) {
                floor.tables.add(Table.fromParseObject(table.fetchIfNeeded()));
            }
        }
        return floor;
    }
}
