package com.cavemen.inception.ui.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.cavemen.inception.R;

public class BUnitsAdapter extends ArrayAdapter<String> {

    private String[] units;

    public BUnitsAdapter(Context context) {
        super(context, android.R.layout.simple_spinner_dropdown_item);
        units = getContext().getResources().getStringArray(R.array.dus);
    }


    @Override
    public String getItem(int position) {
        if (position < units.length) {
            return units[position];
        }
        return "";
    }

    @Override
    public int getCount() {
        return 3;
    }

}
