package com.cavemen.inception.ui.fragment;

import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cavemen.inception.R;
import com.cavemen.inception.events.FloorSelectedEvent;
import com.cavemen.inception.model.CavemenDAO;
import com.cavemen.inception.model.Floor;
import com.cavemen.inception.model.Project;
import com.cavemen.inception.ui.FloorActivity_;
import com.cavemen.inception.ui.adapter.ProjectsAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import de.greenrobot.event.EventBus;

@EFragment(R.layout.floordesc_fragment_layout)
@OptionsMenu(R.menu.floor_desc_menu)
public class FloorDescriptionFragment extends Fragment {

    @ViewById
    ImageView cavemanLogo;

    @ViewById
    LinearLayout contentContainer;

    @ViewById
    TextView floorNameField;

    @ViewById
    TextView occupiedTablesField;

    @ViewById
    TextView freeTablesField;

    @ViewById
    TextView bookedTablesField;

    @ViewById
    ListView projectList;

    @ViewById
    ViewStub empty;

    Floor currentFloor;

    @Bean
    CavemenDAO dao;

    @Bean
    ProjectsAdapter projectAdapter;

    @AfterViews
    public void initAdapter() {
        projectList.setAdapter(projectAdapter);
        empty.setLayoutResource(R.layout.empty_projects);
        projectList.setEmptyView(empty);
        cavemanLogo.setBackgroundResource(R.drawable.caveman_progress);
        ((AnimationDrawable)cavemanLogo.getBackground()).start();
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
        cavemanLogo.setVisibility(View.VISIBLE);
        ((AnimationDrawable)cavemanLogo.getBackground()).start();
        contentContainer.setVisibility(View.GONE);
        currentFloor = event.getFloor();
        floorNameField.setText("Floor " + currentFloor.getNumber() + " (" + currentFloor.getName() + ")");
        loadProjectsAndStats(currentFloor);
    }

    @Background
    void loadProjectsAndStats(Floor floor) {
        List<Project> projects = dao.getProjectsForFloor(floor);
        int[] stats = dao.calculateTableStats(floor);
        populateAdapter(projects, stats);
    }

    @UiThread
    void populateAdapter(List<Project> projects, int[] stats) {
        cavemanLogo.setVisibility(View.GONE);
        contentContainer.setVisibility(View.VISIBLE);
        projectAdapter.setProjects(projects);
        projectAdapter.notifyDataSetChanged();
        occupiedTablesField.setText(stats[2]);
        freeTablesField.setText(stats[0]);
        bookedTablesField.setText(stats[1]);
    }

    @OptionsItem(R.id.action_map)
    public void viewFloorMap() {
        FloorActivity_.intent(getActivity()).floorId(currentFloor.getFloorId()).start();
    }

}
