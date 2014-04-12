package com.cavemen.inception.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.cavemen.inception.R;

/**
 * Created by vivascu on 4/12/2014.
 */
public class TableView extends ImageButton {
    public static final int intialHeight = 75 ;
    public static final int intialWeight = 45;

    boolean isEmpty;
    boolean isBooked;
    boolean isOccupied;


    private static final int[] EMPTY_STATE_SET = {
            R.attr.state_empty
    };

    private static final int[] BOOKED_STATE_SET = {
            R.attr.state_booked
    };

    private static final int[] OCCUPIED_STATE_SET = {
            R.attr.state_occupied
    };

    public TableView(Context context) {
        super(context);
    }

    public TableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TableView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 3);
        if (isEmpty()) {
            mergeDrawableStates(drawableState, EMPTY_STATE_SET);
        }
        if (isBooked()) {
            mergeDrawableStates(drawableState, BOOKED_STATE_SET);
        }
        if (isOccupied()) {
            mergeDrawableStates(drawableState, OCCUPIED_STATE_SET);
        }
        return drawableState;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
}
