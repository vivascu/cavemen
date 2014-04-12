package com.cavemen.inception.ui;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.cavemen.inception.R;
import com.cavemen.inception.events.FloorSelectedEvent;
import com.cavemen.inception.model.DU;
import com.cavemen.inception.ui.adapter.BUnitsAdapter;
import com.cavemen.inception.ui.fragment.FloorDescriptionFragment;
import com.cavemen.inception.ui.fragment.VenueFragment;
import com.cavemen.inception.util.UIUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends BaseActivity {

    @Bean
    BUnitsAdapter adapter;

    @FragmentById(R.id.fragment_venue)
    VenueFragment mVenueFragment;

    @FragmentById(R.id.fragment_floorDesc)
    FloorDescriptionFragment mFloorDescFragment;

    @ViewById(R.id.fragment_container)
    SlidingPaneLayout slidingPane;

    @StringRes(R.string.app_name)
    String mAppName;

    @AfterViews
    public void afterViews() {
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        ActionBar.OnNavigationListener navigationListener = new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                mVenueFragment.bindUnit(adapter.getItem(itemPosition));
                return false;
            }
        };
        actionBar.setListNavigationCallbacks(adapter, navigationListener);
        loadDUs();

        mFloorDescFragment.setHasOptionsMenu(false);
        slidingPane.openPane();
        slidingPane.setParallaxDistance(100);
    }

    @Background
    void loadDUs() {
        try {
            List<DU> result = new ArrayList<DU>();
            ParseQuery<ParseObject> duQuery = ParseQuery.getQuery(DU.TABLE_NAME);
            for (ParseObject du : duQuery.find()) {
                result.add(DU.fromParseObject(du));
            }
            populateAdapter(result);
        } catch (ParseException e) {
            Log.e(BUnitsAdapter.class.getSimpleName(), e.getLocalizedMessage(), e);
        }
    }

    @UiThread
    void populateAdapter(List<DU> dus) {
        adapter.setDus(dus);
        mVenueFragment.bindUnit(dus.get(0));
    }

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (isFinishing()) {
            return;
        }
        UIUtils.enableDisableActivitiesByFormFactor(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        slidingPane.setPanelSlideListener(slidingPaneListener);
        getFragmentManager().addOnBackStackChangedListener(backStackListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        slidingPane.setPanelSlideListener(null);
        getFragmentManager().removeOnBackStackChangedListener(backStackListener);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(FloorSelectedEvent event) {
        slidingPane.closePane();
    }

    /**
     * This back stack listener is used to simulate standard fragment backstack behavior
     * for back button when panes are slid back and forth.
     */
    final FragmentManager.OnBackStackChangedListener backStackListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            if (slidingPane.isSlideable() &&
                    !slidingPane.isOpen() &&
                    getFragmentManager().getBackStackEntryCount() == 0) {
                slidingPane.openPane();
            }
        }
    };


    final SlidingPaneLayout.SimplePanelSlideListener slidingPaneListener = new SlidingPaneLayout.SimplePanelSlideListener() {

        public void onPanelOpened(View view) {
            if (slidingPane.isSlideable()) {
                updateActionBarWithHomeBackNavigation();
                getFragmentManager().popBackStack();
            }
        }

        public void onPanelClosed(View view) {
            //This is called only on phones and 7 inch tablets in portrait
            updateActionBarWithoutLandingNavigation();
            getFragmentManager().beginTransaction().addToBackStack(null).commit();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
         * The action bar up action should open the slider if it is currently
         * closed, as the left pane contains content one level up in the
         * navigation hierarchy.
         */
        if (item.getItemId() == android.R.id.home && !slidingPane.isOpen()) {
            slidingPane.openPane();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void updateActionBarWithoutLandingNavigation() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        mFloorDescFragment.setHasOptionsMenu(true);
        String floorNumber = mVenueFragment.getCurrentFloor();
        actionBar.setTitle(floorNumber.equals("") ? mAppName : floorNumber);
    }

    private void updateActionBarWithHomeBackNavigation() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        mFloorDescFragment.setHasOptionsMenu(false);
        actionBar.setTitle(mAppName);
    }


    @OptionsItem(R.id.action_settings)
    void menuSettingsSelected() {
        //Start settings activity
    }

}
