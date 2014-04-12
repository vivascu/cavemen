package com.cavemen.inception.model;

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

    private static final String TABLE_NAME = "DU";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_FLOORS = "floors";

    private String name;
    private double latitude;
    private double longitude;
    private List<Floor> floors;

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

    public List<Floor> getFloors() {
        return Collections.unmodifiableList(floors);
    }

    public void setFloors(List<Floor> floors) {
        this.floors = new ArrayList<Floor>(floors);
    }

    public ParseObject toParseObject() {
        ParseObject parseObject = new ParseObject(TABLE_NAME);
        parseObject.put(COLUMN_NAME, name);
        parseObject.put(COLUMN_LOCATION, new ParseGeoPoint(latitude, longitude));
        parseObject.put(COLUMN_FLOORS, floors);
        return parseObject;
    }

    public static DU fromParseObject(ParseObject parseObject) {
        DU du = new DU();
        du.name = parseObject.getString(COLUMN_NAME);
        ParseGeoPoint geoPoint = parseObject.getParseGeoPoint(COLUMN_LOCATION);
        du.latitude = geoPoint.getLatitude();
        du.longitude = geoPoint.getLongitude();
        du.floors = new ArrayList<Floor>();
        for (ParseObject floor : (List<ParseObject>) parseObject.get(COLUMN_FLOORS)) {
            du.floors.add(Floor.fromParseObject(floor));
        }
        return du;
    }
}
