package com.cavemen.inception.ui.component;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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

    @ViewById
    View occupancyMeter;

    @ViewById
    View freeMeter;


    public FloorListItemComponent(Context context) {
        super(context);
    }


    public void bindItem(String floorNum, int floorOccupancy) {
        this.floorNumber.setText(floorNum);
        this.floorOccupancy.setText(floorOccupancy + "%");

        float occupiedWeight = floorOccupancy / 100.0f;
        float freeWeight = 1 - (floorOccupancy / 100.0f);
        occupancyMeter.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, freeWeight));
        freeMeter.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, occupiedWeight));
    }


}
