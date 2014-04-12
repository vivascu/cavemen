package com.cavemen.inception.model;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents an employee.
 * <p/>
 * Created by eandreevici on 4/12/2014.
 */
public class Person {

    public static final String TABLE_NAME = "Person";

    public static final String COLUMN_FNAME = "fName";
    public static final String COLUMN_LNAME = "lName";
    public static final String COLUMN_JOB_TITLE = "jobTitle";
    public static final String COLUMN_PHOTO_URI = "photoUri";
    public static final String COLUMN_PROJECTS = "projects";
    public static final String COLUMN_TABLE_TOKEN = "tableToken";
    public static final String COLUMN_TABLE = "table";

    private String firstName;
    private String lastName;
    private String jobTitle;
    private String photoUri;
    private String tableToken;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getTableToken() {
        return tableToken;
    }

    public void setTableToken(String tableToken) {
        this.tableToken = tableToken;
    }

    public ParseObject toParseObject() {
        ParseObject parseObject = new ParseObject(TABLE_NAME);
        parseObject.put(COLUMN_FNAME, firstName);
        parseObject.put(COLUMN_LNAME, lastName);
        parseObject.put(COLUMN_JOB_TITLE, jobTitle);
        parseObject.put(COLUMN_PHOTO_URI, photoUri);
        parseObject.put(COLUMN_TABLE_TOKEN, tableToken);
        return parseObject;
    }

    public static Person fromParseObject(ParseObject parseObject) throws ParseException {
        Person person = new Person();
        person.firstName = parseObject.getString(COLUMN_FNAME);
        person.lastName = parseObject.getString(COLUMN_LNAME);
        person.jobTitle = parseObject.getString(COLUMN_JOB_TITLE);
        person.photoUri = parseObject.getString(COLUMN_PHOTO_URI);
        person.tableToken = parseObject.getString(COLUMN_TABLE_TOKEN);
        return person;
    }
}
