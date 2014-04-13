package com.cavemen.inception.model;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.io.Serializable;

/**
 * Represents an employee.
 * <p/>
 * Created by eandreevici on 4/12/2014.
 */
public class Person implements Serializable{

    public static final String TABLE_NAME = "Person";

    public static final String COLUMN_FNAME = "fName";
    public static final String COLUMN_LNAME = "lName";
    public static final String COLUMN_JOB_TITLE = "jobTitle";
    public static final String COLUMN_PHOTO_URI = "photoUri";
    public static final String COLUMN_PROJECTS = "projects";
    public static final String COLUMN_TABLE_TOKEN = "tableToken";
    public static final String COLUMN_TABLE = "table";
    public static final String COLUMN_LOGIN = "login";
    private static final long serialVersionUID = -4241661417062206800L;
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_BIRTHDAY = "birthday";
    public static final String COLUMN_PHONE = "personalPhone";
    public static final String COLUMN_LINE_MANAGER = "lineManager";

    private String id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String photoUri;
    private String tableToken;
    private String login;
    private String email;
    private String gender;
    private String birthday;
    private String phone;
    private String lineManager;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLineManager() {
        return lineManager;
    }

    public void setLineManager(String lineManager) {
        this.lineManager = lineManager;
    }

    public ParseObject toParseObject() {
        ParseObject parseObject = new ParseObject(TABLE_NAME);
        parseObject.setObjectId(id);
        parseObject.put(COLUMN_FNAME, firstName);
        parseObject.put(COLUMN_LNAME, lastName);
        parseObject.put(COLUMN_JOB_TITLE, jobTitle);
        parseObject.put(COLUMN_PHOTO_URI, photoUri);
        parseObject.put(COLUMN_TABLE_TOKEN, tableToken);
        parseObject.put(COLUMN_LOGIN, login);
        parseObject.put(COLUMN_EMAIL, email);
        parseObject.put(COLUMN_GENDER, gender);
        parseObject.put(COLUMN_BIRTHDAY, birthday);
        parseObject.put(COLUMN_PHONE, phone);
        parseObject.put(COLUMN_LINE_MANAGER, lineManager);
        return parseObject;
    }

    public static Person fromParseObject(ParseObject parseObject) throws ParseException {
        Person person = new Person();
        person.id = parseObject.getObjectId();
        person.firstName = parseObject.getString(COLUMN_FNAME);
        person.lastName = parseObject.getString(COLUMN_LNAME);
        person.jobTitle = parseObject.getString(COLUMN_JOB_TITLE);
        person.photoUri = parseObject.getString(COLUMN_PHOTO_URI);
        person.tableToken = parseObject.getString(COLUMN_TABLE_TOKEN);
        person.login = parseObject.getString(COLUMN_LOGIN);
        person.email = parseObject.getString(COLUMN_EMAIL);
        person.gender = parseObject.getString(COLUMN_GENDER);
        person.birthday = parseObject.getString(COLUMN_BIRTHDAY);
        person.phone = parseObject.getString(COLUMN_PHONE);
        person.lineManager = parseObject.getString(COLUMN_LINE_MANAGER);
        return person;
    }
}
