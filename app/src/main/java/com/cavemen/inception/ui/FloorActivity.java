package com.cavemen.inception.ui;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.cavemen.inception.R;
import com.cavemen.inception.model.CavemenDAO;
import com.cavemen.inception.model.Table;
import com.cavemen.inception.model.TableStatus;
import com.cavemen.inception.ui.fragment.TableDialogFragment_;
import com.cavemen.inception.ui.view.TableView;
import com.parse.ParsePush;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

import static com.cavemen.inception.util.LogUtils.LOGD;

/**
 * Created by vivascu on 4/12/2014.
 */
@EActivity(R.layout.floor_fragment_layout)
public class FloorActivity extends BaseActivity implements PhotoViewAttacher.OnMatrixChangedListener, View.OnClickListener {

    @Bean
    CavemenDAO dao;

    @Extra
    String floorId;

    @ViewById(R.id.caveplan)
    ImageView imageView;
    @ViewById(R.id.table_container)
    FrameLayout container;
    PhotoViewAttacher mAttacher;

    List<Table> mTables = new ArrayList<Table>();
    RectF mRect;
    View previouslyActivatedView;

    @AfterViews
    public void afterView() {
        Drawable bitmap = getResources().getDrawable(R.drawable.caveplan);
        imageView.setImageDrawable(bitmap);
        mAttacher = new PhotoViewAttacher(imageView);
        mAttacher.setOnMatrixChangeListener(this);
        getTables();
    }

    @Background
    public void getTables() {
        List<Table> tables = dao.getTablesForFloorId(floorId);
        mTables = tables;
        drawTables(tables);
    }

    @UiThread
    public void drawTables(List<Table> list) {
        if (mRect != null) {
            onMatrixChanged(mRect);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void addDeskView(Table table, float imageWidth, int originX, int originY) {
        float ratio;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float displayRatio = metrics.density;
        TableView view = new TableView(this);
        view.setBackgroundResource(R.drawable.table_bg_selector);
        view.setEmpty(table.getStatus().equals(TableStatus.EMPTY));
        view.setBooked(table.getStatus().equals(TableStatus.BOOKED));
        view.setOccupied(table.getStatus().equals(TableStatus.OCCUPIED));
        ratio = imageWidth / 1267.0f;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        params.width = Math.round(view.intialWeight * ratio);
        params.height = Math.round((view.intialHeight * ratio));
        view.setX(originX + Math.round(table.getX() * ratio));
        view.setY(originY + Math.round(table.getY() * ratio));
        view.setLayoutParams(params);
        view.setTag(table);
        view.setOnClickListener(this);
        container.addView(view);
    }

    @Override
    public void onMatrixChanged(RectF rect) {
        mRect = rect;
        if (container.getChildCount() > mTables.size()) {
            for (int i = 0; i < container.getChildCount(); i++) {
                View view = container.getChildAt(i);
                if (view.getId() != R.id.caveplan && view instanceof TableView) {
                    updateTablePosition(view, rect.width(), Math.round(rect.centerX() - (rect.width() / 2.0f)), Math.round(rect.centerY() - (rect.height() / 2.0f)));
                }
            }
        } else {
            for (Table table : mTables) {
                addDeskView(table, rect.width(), Math.round(rect.centerX() - (rect.width() / 2.0f)), Math.round(rect.centerY() - (rect.height() / 2.0f)));
            }

        }
    }

    private void updateTablePosition(View view, float imageWidth, int originX, int originY) {
        TableView tableView = (TableView) view;
        Table table = (Table) tableView.getTag();
        float ratio = imageWidth / 1267.0f;
        ;
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.width = Math.round(tableView.intialWeight * ratio);
        params.height = Math.round((tableView.intialHeight * ratio));
        view.setX(originX + Math.round(table.getX() * ratio));
        view.setY(originY + Math.round(table.getY() * ratio));
        view.setLayoutParams(params);
        view.setTag(table);
    }

    @Override
    public void onClick(View view) {
        if (view instanceof TableView) {
            Table table = (Table) view.getTag();
            if (!view.isActivated()) {
                if (previouslyActivatedView != null) {
                    previouslyActivatedView.setActivated(false);
                }
                previouslyActivatedView = view;
            }


            showDialog(table);
            view.setActivated(!view.isActivated());

        }
    }

    void showDialog(Table table) {

        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = TableDialogFragment_.builder().mTable(table).build();
        newFragment.show(ft, "dialog");
    }

    public class UpdatesReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            LOGD(UpdatesReceiver.class.getSimpleName(), "got update!");
        }
    }
}
