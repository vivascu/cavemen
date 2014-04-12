package com.cavemen.inception.model;

import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a Delivery Unit.
 * <p/>
 * Created by eandreevici on 4/12/2014.
 */
public class DU {

    public static final String TABLE_NAME = "DU";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LOCATION = "location";

    private String name;
    private double latitude;
    private double longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ParseObject toParseObject() {
        ParseObject parseObject = new ParseObject(TABLE_NAME);
        parseObject.put(COLUMN_NAME, name);
        parseObject.put(COLUMN_LOCATION, new ParseGeoPoint(latitude, longitude));
        return parseObject;
    }

    public static DU fromParseObject(ParseObject parseObject) throws ParseException {
        DU du = new DU();
        du.name = parseObject.getString(COLUMN_NAME);
        ParseGeoPoint geoPoint = parseObject.getParseGeoPoint(COLUMN_LOCATION);
        du.latitude = geoPoint.getLatitude();
        du.longitude = geoPoint.getLongitude();
        return du;
    }
}
