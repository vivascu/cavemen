package com.cavemen.inception.ui.fragment;

import android.app.Fragment;
import android.widget.ListView;

import com.cavemen.inception.R;
import com.cavemen.inception.events.FloorSelectedEvent;
import com.cavemen.inception.model.DU;
import com.cavemen.inception.model.Floor;
import com.cavemen.inception.model.Table;
import com.cavemen.inception.model.TableStatus;
import com.cavemen.inception.ui.adapter.FloorsAdapter;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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

import static com.cavemen.inception.util.LogUtils.LOGE;

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
        loadFloors(du);
    }

    @Background
    void loadFloors(DU du) {
        try {
            ParseObject duObject = ParseQuery.getQuery(DU.TABLE_NAME).whereEqualTo(DU.COLUMN_NAME, du.getName()).getFirst();
            ParseQuery<ParseObject> floorsQuery = ParseQuery.getQuery(Floor.TABLE_NAME);
            floorsQuery.whereEqualTo(Floor.COLUMN_DU, duObject);
            List<Floor> floors = new ArrayList<Floor>();
            List<Integer> percentages = new ArrayList<Integer>();
            for (ParseObject floor : floorsQuery.find()) {
                floors.add(Floor.fromParseObject(floor));
                ParseQuery<ParseObject> tablesQuery = ParseQuery.getQuery(Table.TABLE_NAME);
                tablesQuery.whereEqualTo(Table.COLUMN_FLOOR, floor);
                List<Table> tables = new ArrayList<Table>();
                for (ParseObject table : tablesQuery.find()) {
                    tables.add(Table.fromParseObject(table));
                }
                percentages.add(percentageOfOccupiedSeats(tables));
            }
            populateAdapter(floors, percentages);
        } catch (ParseException e) {
            LOGE(VenueFragment.class.getSimpleName(), e.getLocalizedMessage(), e);
        }
    }

    public int percentageOfOccupiedSeats(List<Table> tables) {
        int total = tables.size();
        int occupied = 0;
        for (Table table : tables) {
            if (!table.getStatus().equals(TableStatus.EMPTY)) {
                occupied++;
            }
        }
        return (int) (((float) occupied / total) * 100);
    }

    @UiThread
    void populateAdapter(List<Floor> floors, List<Integer> percentages) {
        floorsAdapter.setFloors(floors);
        floorsAdapter.setPercentagesOfOccupiedSeats(percentages);
        floorsAdapter.notifyDataSetChanged();
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
