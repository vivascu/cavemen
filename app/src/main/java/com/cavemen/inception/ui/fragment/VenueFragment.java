package com.cavemen.inception.ui.fragment;

import android.app.Fragment;
import android.widget.ListView;

import com.cavemen.inception.R;
import com.cavemen.inception.events.FloorSelectedEvent;
import com.cavemen.inception.model.DU;
import com.cavemen.inception.ui.adapter.FloorsAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

@EFragment(R.layout.fragment_listview_with_empty)
public class VenueFragment extends Fragment {

    @FragmentArg
    String buildingName;

    @FragmentArg
    int buildingId;

    @Bean
    FloorsAdapter floorsAdapter;

    long currentFloorIndex;

    @ViewById
    ListView list;


    @AfterViews
    public void bindAdapter() {
        list.setAdapter(floorsAdapter);
    }


    public void bindUnit(DU du) {
        //TODO reload adapter with stuff
//        currentFloorIndex = itemPosition;
        floorsAdapter.setFloors(du.getFloors());
    }

    public String getCurrentFloor() {
        return String.valueOf(floorsAdapter.getItem((int) currentFloorIndex).getNumber());
    }


    @ItemClick
    public void listItemClicked(int position) {
        // Notify the parent activity of selected item
        currentFloorIndex = position;
        EventBus.getDefault().post(new FloorSelectedEvent(currentFloorIndex));
        // Set the item as checked to be highlighted
        floorsAdapter.notifyDataSetChanged();
    }

}
