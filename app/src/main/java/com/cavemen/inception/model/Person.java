package com.cavemen.inception.model;

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

    private static final String TABLE_NAME = "Person";
    private static final String COLUMN_FNAME = "fName";
    private static final String COLUMN_LNAME = "lName";
    private static final String COLUMN_JOB_TITLE = "jobTitle";
    private static final String COLUMN_PHOTO_URI = "photoUri";
    private static final String COLUMN_PROJECTS = "projects";
    private static final String COLUMN_TABLE_TOKEN = "tableToken";

    private String firstName;
    private String lastName;
    private String jobTitle;
    private String photoUri;
    private List<Project> projects;
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

    public List<Project> getProjects() {
        return Collections.unmodifiableList(projects);
    }

    public void setProjects(List<Project> projects) {
        this.projects = new ArrayList<Project>(projects);
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
        parseObject.put(COLUMN_PROJECTS, projects);
        parseObject.put(COLUMN_TABLE_TOKEN, tableToken);
        return parseObject;
    }

    public static Person fromParseObject(ParseObject parseObject) {
        Person person = new Person();
        person.firstName = parseObject.getString(COLUMN_FNAME);
        person.lastName = parseObject.getString(COLUMN_LNAME);
        person.jobTitle = parseObject.getString(COLUMN_JOB_TITLE);
        person.photoUri = parseObject.getString(COLUMN_PHOTO_URI);
        person.projects = new ArrayList<Project>();
        for (ParseObject project : (List<ParseObject>) parseObject.get(COLUMN_PROJECTS)) {
            person.projects.add(Project.fromParseObject(project));
        }
        person.tableToken = parseObject.getString(COLUMN_TABLE_TOKEN);
        return person;
    }
}
