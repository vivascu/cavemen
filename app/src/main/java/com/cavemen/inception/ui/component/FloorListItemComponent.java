package com.cavemen.inception.ui.component;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cavemen.inception.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.floor_item_row)
public class FloorListItemComponent extends FrameLayout {

    @ViewById
    TextView floorNumber;

    @ViewById
    TextView floorOccupancy;


    public FloorListItemComponent(Context context) {
        super(context);
    }


    public void bindItem(String floorNum, int floorOccupancy) {
        this.floorNumber.setText(floorNum);
        this.floorOccupancy.setText(floorOccupancy + "%");
    }
}
