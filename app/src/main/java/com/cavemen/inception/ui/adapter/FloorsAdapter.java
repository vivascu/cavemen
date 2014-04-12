package com.cavemen.inception.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cavemen.inception.ui.component.FloorListItemComponent;
import com.cavemen.inception.ui.component.FloorListItemComponent_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

@EBean
public class FloorsAdapter extends BaseAdapter {

    final List<String> floors = new ArrayList<String>();

    @RootContext
    Context context;

    @AfterInject
    void initAdapter() {
        reloadFloors();
    }

    public void loadFloorsForVenue(int floor) {

    }

    @Background
    public void reloadFloors() {
        floors.clear();
        floors.add("Floor 1");
        floors.add("Floor 2");
    }

    @Override
    public int getCount() {
        if (floors != null) {
            return floors.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (position >= floors.size()) {
            return "";
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
            authorsItemView.bindItem(floors.get(position), (position + 5) * 10);
        }
        return authorsItemView;
    }
}
