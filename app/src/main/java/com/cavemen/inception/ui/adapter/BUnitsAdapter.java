package com.cavemen.inception.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cavemen.inception.model.DU;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

@EBean
public class BUnitsAdapter extends BaseAdapter {

    private List<DU> dus = new ArrayList<DU>();

    @RootContext
    Context context;

    public void setDus(List<DU> dus) {
        this.dus = dus;
        notifyDataSetChanged();
    }

    @Override
    public DU getItem(int position) {
        if (position < dus.size()) {
            return dus.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }
        ((TextView) convertView).setText(getItem(position).getName());
        return convertView;
    }

    @Override
    public int getCount() {
        return dus.size();
    }

}
