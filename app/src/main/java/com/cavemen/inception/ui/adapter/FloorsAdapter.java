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

    @RootContext
    Context context;

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (floors != null) {
            return floors.size();
        }
        return 0;
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
            authorsItemView.bindItem(String.valueOf(floor.getNumber()), floor.percentageOfOccupiedSeats());
        }
        return authorsItemView;
    }
}
