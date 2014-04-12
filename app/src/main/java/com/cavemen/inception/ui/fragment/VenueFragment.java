package com.cavemen.inception.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.cavemen.inception.R;
import com.cavemen.inception.events.FloorSelectedEvent;
import com.cavemen.inception.model.CavemenDAO;
import com.cavemen.inception.model.DU;
import com.cavemen.inception.model.Floor;
import com.cavemen.inception.ui.adapter.FloorsAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

@EFragment(R.layout.fragment_listview_with_empty)
public class VenueFragment extends Fragment {

    @FragmentArg
    String buildingName;

    @FragmentArg
    int buildingId;

    @Bean
    FloorsAdapter floorsAdapter;

    @Bean
    CavemenDAO dao;

    long currentFloorIndex;

    @ViewById
    ListView list;

    @ViewById
    ProgressBar listProgress;


    @AfterViews
    public void bindAdapter() {
        listProgress.setVisibility(View.VISIBLE);
        list.setAdapter(floorsAdapter);
    }


    public void bindUnit(DU du) {
        //TODO reload adapter with stuff
        //currentFloorIndex = itemPosition;
        loadFloors(du);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Background
    void loadFloors(DU du) {
        List<Floor> floors = dao.getFloorsForDU(du);
        populateFloors(floors);
        List<Integer> percentages = new ArrayList<Integer>();
        for (Floor floor : floors) {
            percentages.add(dao.calculateOccupiedTablesPercentage(floor));
        }
        populatePercentages(percentages);
    }

    @UiThread
    void populateFloors(List<Floor> floors) {
        floorsAdapter.setFloors(floors);
        listProgress.setVisibility(View.GONE);
    }

    @UiThread
    void populatePercentages(List<Integer> percentages) {
        floorsAdapter.setPercentagesOfOccupiedSeats(percentages);
    }

    public String getCurrentFloor() {
        Floor floor = floorsAdapter.getItem((int) currentFloorIndex);
        if (floor != null) {
            return String.valueOf(floor.getNumber());
        }
        return "";
    }


    @ItemClick
    public void listItemClicked(int position) {
        // Notify the parent activity of selected item
        currentFloorIndex = position;
        EventBus.getDefault().post(new FloorSelectedEvent(floorsAdapter.getItem(position)));
        // Set the item as checked to be highlighted
        floorsAdapter.notifyDataSetChanged();
    }

}
