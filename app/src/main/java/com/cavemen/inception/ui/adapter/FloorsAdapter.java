package com.cavemen.inception.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cavemen.inception.model.Floor;
import com.cavemen.inception.ui.component.FloorListItemComponent;
import com.cavemen.inception.ui.component.FloorListItemComponent_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

@EBean
public class FloorsAdapter extends BaseAdapter {

    List<Floor> floors = new ArrayList<Floor>();
    List<Integer> percentagesOfOccupiedSeats = new ArrayList<Integer>();

    @RootContext
    Context context;

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
        notifyDataSetChanged();
    }

    public void setPercentagesOfOccupiedSeats(List<Integer> percentagesOfOccupiedSeats) {
        this.percentagesOfOccupiedSeats = percentagesOfOccupiedSeats;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return floors.size();
    }

    @Override
    public Floor getItem(int position) {
        if (position >= floors.size()) {
            return null;
        }
        return floors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FloorListItemComponent authorsItemView;
        if (convertView == null) {
            authorsItemView = FloorListItemComponent_.build(context);
        } else {
            authorsItemView = (FloorListItemComponent) convertView;
        }
        if (position < floors.size()) {
            Floor floor = getItem(position);
            int occupiedSeats = -1;
            if (position < percentagesOfOccupiedSeats.size()) {
                occupiedSeats = percentagesOfOccupiedSeats.get(position);
            }
            authorsItemView.bindItem(floor, occupiedSeats);
        }
        return authorsItemView;
    }
}
