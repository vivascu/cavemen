package com.cavemen.inception.ui;

import android.widget.ImageView;

import com.cavemen.inception.R;
import com.cavemen.inception.ui.view.TableView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by vivascu on 4/12/2014.
 */
@EActivity(R.layout.floor_fragment_layout)
public class FloorActivity extends BaseActivity {
    @ViewById(R.id.Table)
    TableView mTableView;
    @ViewById(R.id.caveplan)
    ImageView imageView;

    @AfterViews
    public void afterView(){

    }

    @Override
    protected void onStart() {
        super.onStart();
        mTableView.setEmpty(true);
        mTableView.setBooked(false);
        mTableView.setOccupied(false);
        mTableView.setX(imageView.getMeasuredWidth() / 2);
        mTableView.setY(imageView.getMeasuredHeight() / 2);
    }
}
