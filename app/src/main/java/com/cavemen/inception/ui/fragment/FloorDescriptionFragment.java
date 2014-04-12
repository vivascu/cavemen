package com.cavemen.inception.ui.fragment;

import android.app.Fragment;
import android.widget.Button;

import com.cavemen.inception.R;
import com.cavemen.inception.events.FloorSelectedEvent;
import com.cavemen.inception.ui.FloorActivity_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.floordesc_fragment_layout)
public class FloorDescriptionFragment extends Fragment {

    @ViewById(R.id.venue_button)
    Button mButton;

    public void onEvent(FloorSelectedEvent event) {

    }

    @Click(R.id.venue_button)
    public void viewFloorMap() {
        FloorActivity_.intent(getActivity()).start();
    }

}
