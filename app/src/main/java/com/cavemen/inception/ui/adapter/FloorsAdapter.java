package com.cavemen.inception.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cavemen.inception.model.Floor;
import com.cavemen.inception.ui.component.FloorListItemComponent;
import com.cavemen.inception.ui.component.FloorListItemComponent_;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;
import java.util.List;

@EBean
public class FloorsAdapter extends BaseAdapter {

    private ParseQuery<ParseObject> floorsQuery;

    final List<Floor> floors = new ArrayList<Floor>();

    @RootContext
    Context context;

    @AfterInject
    void initAdapter() {
        floorsQuery = ParseQuery.getQuery(Floor.TABLE_NAME);
        reloadFloors();
    }

    public void loadFloorsForVenue(int floor) {

    }

    @Background
    public void reloadFloors() {
        floors.clear();
        try {
            for (ParseObject floor : floorsQuery.find()) {
                floors.add(Floor.fromParseObject(floor));
            }
            notifyDataChanged();
        } catch (ParseException e) {
            Log.e(FloorsAdapter.class.getSimpleName(), e.getLocalizedMessage(), e);
        }
    }

    @UiThread
    public void notifyDataChanged() {
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
            authorsItemView.bindItem(String.valueOf(floors.get(position).getNumber()), position * 10);
        }
        return authorsItemView;
    }
}
