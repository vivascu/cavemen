package com.cavemen.inception;

import android.app.Application;
import android.os.AsyncTask;

import com.cavemen.inception.model.Person;
import com.cavemen.inception.model.Project;
import com.cavemen.inception.ui.FloorActivity;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.PushService;

import static com.cavemen.inception.util.LogUtils.LOGE;

/**
 * Created by eandreevici on 4/12/2014.
 */
public class CavemenApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "X7woT6Y1lWzo9LWvVGTLalqEyHueHiz10XP5iwaa", "2nDd05km9XcNmHZShR7GZNOkpgOsOFs7CVwk7LGe");
        PushService.setDefaultPushCallback(this, FloorActivity.class);
//        new Mapper().execute();
    }

    class Mapper extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                ParseQuery<ParseObject> personsQuery = new ParseQuery<ParseObject>(Person.TABLE_NAME);
                ParseQuery<ParseObject> projectsQuery = new ParseQuery<ParseObject>(Project.TABLE_NAME);
                ParseObject leonid = personsQuery.get("GcNZDwkyFH");
                ParseObject alex = personsQuery.get("I99ijezt6p");
                ParseObject gleb = personsQuery.get("IGOnyZua2f");
                ParseObject victor = personsQuery.get("uVnUqmbJB4");
                ParseObject egor = personsQuery.get("cW8X9AFAjR");
                ParseObject p1 = projectsQuery.get("YoYeBQW03T");
                ParseObject p2 = projectsQuery.get("FFbeVN0st5");
                ParseObject p3 = projectsQuery.get("LyZtXCodmM");
                ParseObject p4 = projectsQuery.get("Gl9QVRuQW7");
                ParseObject p5 = projectsQuery.get("yvH2nyu3Ql");

                leonid.getRelation(Person.COLUMN_PROJECTS).add(p1);
                leonid.getRelation(Person.COLUMN_PROJECTS).add(p2);
                alex.getRelation(Person.COLUMN_PROJECTS).add(p3);
                alex.getRelation(Person.COLUMN_PROJECTS).add(p4);
                gleb.getRelation(Person.COLUMN_PROJECTS).add(p5);
                victor.getRelation(Person.COLUMN_PROJECTS).add(p2);
                victor.getRelation(Person.COLUMN_PROJECTS).add(p5);
                egor.getRelation(Person.COLUMN_PROJECTS).add(p1);
                egor.getRelation(Person.COLUMN_PROJECTS).add(p4);

                p1.getRelation(Project.COLUMN_TEAM).add(leonid);
                p1.getRelation(Project.COLUMN_TEAM).add(egor);
                p2.getRelation(Project.COLUMN_TEAM).add(leonid);
                p2.getRelation(Project.COLUMN_TEAM).add(victor);
                p3.getRelation(Project.COLUMN_TEAM).add(alex);
                p4.getRelation(Project.COLUMN_TEAM).add(alex);
                p4.getRelation(Project.COLUMN_TEAM).add(egor);
                p5.getRelation(Project.COLUMN_TEAM).add(victor);
                p5.getRelation(Project.COLUMN_TEAM).add(gleb);

                leonid.saveInBackground();
                alex.saveInBackground();
                gleb.saveInBackground();
                victor.saveInBackground();
                egor.saveInBackground();

                p1.saveInBackground();
                p2.saveInBackground();
                p3.saveInBackground();
                p4.saveInBackground();
                p5.saveInBackground();
            } catch (ParseException e) {
                LOGE(Mapper.class.getSimpleName(), e.getLocalizedMessage(), e);
            }
            return null;
        }
    }
}
