package com.cavemen.inception.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cavemen.inception.R;
import com.cavemen.inception.events.FloorSelectedEvent;
import com.cavemen.inception.model.DU;
import com.cavemen.inception.model.Floor;
import com.cavemen.inception.model.Project;
import com.cavemen.inception.model.Table;
import com.cavemen.inception.ui.FloorActivity_;
import com.cavemen.inception.ui.adapter.ProjectsAdapter;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

import static com.cavemen.inception.util.LogUtils.LOGE;

@EFragment(R.layout.floordesc_fragment_layout)
@OptionsMenu(R.menu.floor_desc_menu)
public class FloorDescriptionFragment extends Fragment {

    @ViewById
    TextView occupiedTablesField;

    @ViewById
    TextView freeTablesField;

    @ViewById
    TextView bookedTablesField;

    @ViewById
    ListView projectList;

    Floor currentFloor;

    @Bean
    ProjectsAdapter projectAdapter;

    @AfterViews
    public void initAdapter() {
        projectList.setAdapter(projectAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(FloorSelectedEvent event) {
        currentFloor = event.getFloor();

        loadProjects(currentFloor);

        /*getResources().getString(R.string.occupied_tbls, selectedFloor.percentageOfOccupiedSeats());
        getResources().getString(R.string.free_tbls, selectedFloor.percentageOfOccupiedSeats());
        getResources().getString(R.string.booked_tbls, selectedFloor.percentageOfOccupiedSeats());
        occupiedTablesField.setText(occupiedSeats);
        freeTablesField.setText(freeSeats);
        bookedTablesField.setText(bookedSeats);*/

    }

    @Background
    void loadProjects(Floor floor) {
        /*try {

            ParseObject projectObject = ParseQuery.getQuery(DU.TABLE_NAME).whereEqualTo(DU.COLUMN_NAME, floor.getName()).getFirst();
            ParseQuery<ParseObject> projectQuery = ParseQuery.getQuery(Project.TABLE_NAME);

            List<Project> projects = new ArrayList<Project>();
            for (ParseObject project : projectQuery.find()) {
                projects.add(Project.fromParseObject(project));
                ParseQuery<ParseObject> tablesQuery = ParseQuery.getQuery(Table.TABLE_NAME);
                tablesQuery.whereEqualTo(Table.COLUMN_FLOOR, floor);
                List<Table> tables = new ArrayList<Table>();
                for (ParseObject table : tablesQuery.find()) {
                    tables.add(Table.fromParseObject(table));
                }

            }
            populateAdapter(projects);
        } catch (ParseException e) {
            LOGE(VenueFragment.class.getSimpleName(), e.getLocalizedMessage(), e);
        }*/
    }

    @UiThread
    void populateAdapter(List<Project> projects) {
        projectAdapter.setProjects(projects);
        projectAdapter.notifyDataSetChanged();
    }

    @OptionsItem(R.id.action_map)
    public void viewFloorMap() {
//        FloorActivity_.intent(getActivity()).floorId(currentFloor.getFloorId()).start();
    }

}
