package com.cavemen.inception.ui.fragment;

import android.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cavemen.inception.R;
import com.cavemen.inception.events.FloorSelectedEvent;
import com.cavemen.inception.model.Floor;
import com.cavemen.inception.model.Project;
import com.cavemen.inception.ui.FloorActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

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

    ArrayAdapter<String> projectAdapter;

    @AfterViews
    public void afterViews() {
        projectAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1);
    }

    public void onEvent(FloorSelectedEvent event) {
        Floor selectedFloor = event.getFloor();
        List<String> projectNames = new ArrayList<String>();
        projectNames.add("BUPA");
        projectNames.add("VISA TMS");
        projectNames.add("Some proj");
        projectAdapter.addAll(projectNames);
    }

    @OptionsItem(R.id.action_map)
    public void viewFloorMap() {
        FloorActivity_.intent(getActivity()).start();
    }

}
