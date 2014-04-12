package com.cavemen.inception.model;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a project.
 * <p/>
 * Created by eandreevici on 4/12/2014.
 */
public class Project {

    private static final String TABLE_NAME = "Project";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_RESPONSIBLE = "responsibleId";
    private static final String COLUMN_TEAM = "team";
    private static final String COLUMN_TABLES = "tables";

    private String title;
    private List<Person> team;
    private String responsibleId;
    private List<Table> tables;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Person> getTeam() {
        return Collections.unmodifiableList(team);
    }

    public void setTeam(List<Person> team) {
        this.team = new ArrayList<Person>(team);
    }

    public String getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(String responsibleId) {
        this.responsibleId = responsibleId;
    }

    public List<Table> getTables() {
        return Collections.unmodifiableList(tables);
    }

    public void setTables(List<Table> tables) {
        this.tables = new ArrayList<Table>(tables);
    }

    public ParseObject toParseObject() {
        ParseObject parseObject = new ParseObject(TABLE_NAME);
        parseObject.put(COLUMN_TITLE, title);
        parseObject.put(COLUMN_RESPONSIBLE, responsibleId);
        parseObject.put(COLUMN_TEAM, team);
        parseObject.put(COLUMN_TABLES, tables);
        return parseObject;
    }

    public static Project fromParseObject(ParseObject parseObject) throws ParseException {
        Project project = new Project();
        project.title = parseObject.getString(COLUMN_TITLE);
        project.responsibleId = parseObject.getString(COLUMN_RESPONSIBLE);
        project.team = new ArrayList<Person>();
        for (ParseObject person : (List<ParseObject>) parseObject.get(COLUMN_TEAM)) {
            project.team.add(Person.fromParseObject(person.fetchIfNeeded()));
        }
        project.tables = new ArrayList<Table>();
        for (ParseObject table : (List<ParseObject>) parseObject.get(COLUMN_TABLES)) {
            project.tables.add(Table.fromParseObject(table.fetchIfNeeded()));
        }
        return project;
    }
}
