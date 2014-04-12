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

    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DU = "du";
    public static final String COLUMN_ID = "objectId";

    private int number;
    private String name;
    private String floorId;

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

    public ParseObject toParseObject() {
        ParseObject parseObject = new ParseObject(TABLE_NAME);
        parseObject.put(COLUMN_NUMBER, number);
        parseObject.put(COLUMN_NAME, name);
        return parseObject;
    }

    public static Floor fromParseObject(ParseObject parseObject) throws ParseException {
        Floor floor = new Floor();
        floor.number = parseObject.getInt(COLUMN_NUMBER);
        floor.name = parseObject.getString(COLUMN_NAME);
        floor.floorId = parseObject.getObjectId();
        return floor;
    }

    public String getFloorId() {
        return floorId;
    }
}
