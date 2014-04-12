package com.cavemen.inception.model;

import com.parse.ParseException;
import com.parse.ParseObject;

/**
 * Represents a project.
 * <p/>
 * Created by eandreevici on 4/12/2014.
 */
public class Project {

    public static final String TABLE_NAME = "Project";

    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_RESPONSIBLE = "responsibleId";
    public static final String COLUMN_TEAM = "team";
    public static final String COLUMN_TABLES = "tables";
    public static final String COLUMN_TOKEN = "token";
    public static final String COLUMN_LOGO_URI = "logoUri";

    private String id;
    private String title;
    private String responsibleId;
    private String token;
    private String logoUri;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(String responsibleId) {
        this.responsibleId = responsibleId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLogoUri() {
        return logoUri;
    }

    public void setLogoUri(String logoUri) {
        this.logoUri = logoUri;
    }

    public ParseObject toParseObject() {
        ParseObject parseObject = new ParseObject(TABLE_NAME);
        parseObject.setObjectId(id);
        parseObject.put(COLUMN_TITLE, title);
        parseObject.put(COLUMN_RESPONSIBLE, responsibleId);
        parseObject.put(COLUMN_TOKEN, token);
        parseObject.put(COLUMN_LOGO_URI, logoUri);
        return parseObject;
    }

    public static Project fromParseObject(ParseObject parseObject) throws ParseException {
        Project project = new Project();
        project.id = parseObject.getObjectId();
        project.title = parseObject.getString(COLUMN_TITLE);
        project.responsibleId = parseObject.getString(COLUMN_RESPONSIBLE);
        project.token = parseObject.getString(COLUMN_TOKEN);
        project.logoUri = parseObject.getString(COLUMN_LOGO_URI);
        return project;
    }
}
