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

    private String title;
    private String responsibleId;

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

    public ParseObject toParseObject() {
        ParseObject parseObject = new ParseObject(TABLE_NAME);
        parseObject.put(COLUMN_TITLE, title);
        parseObject.put(COLUMN_RESPONSIBLE, responsibleId);
        return parseObject;
    }

    public static Project fromParseObject(ParseObject parseObject) throws ParseException {
        Project project = new Project();
        project.title = parseObject.getString(COLUMN_TITLE);
        project.responsibleId = parseObject.getString(COLUMN_RESPONSIBLE);
        return project;
    }
}
