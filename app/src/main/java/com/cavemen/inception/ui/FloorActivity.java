package com.cavemen.inception.ui;

import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.cavemen.inception.R;
import com.cavemen.inception.model.Table;
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
    @ViewById(R.id.table_container)
    FrameLayout container;


    @AfterViews
    public void afterView() {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        addDeskView(null, 1022, 136);
        addDeskView(null, 1022, 216);
        addDeskView(null, 1022, 296);
        addDeskView(null, 975, 136);
        addDeskView(null, 975, 216);
        addDeskView(null, 975, 296);
    }

    public void addDeskView(Table table, int x, int y) {
        float ratio;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float displayRatio = metrics.density;
        TableView view = new TableView(this);
        view.setBackgroundResource(R.drawable.table_bg_selector);
        view.setEmpty(true);
        view.setBooked(false);
        view.setOccupied(false);
        ratio = imageView.getMeasuredWidth() / 1267.0f;
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mTableView.getLayoutParams();
        params.width = Math.round(mTableView.intialWeight * ratio * displayRatio);
        params.height = Math.round((mTableView.intialHeight * ratio) * displayRatio);
        //params.setX(Math.round(975*1.5),137,0,0);
        view.setX(Math.round(x * ratio));
        view.setY(Math.round(y * ratio));
        view.setLayoutParams(params);
        view.setTag(table);
        container.addView(view);
    }
}
