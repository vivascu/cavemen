package com.cavemen.inception.ui.fragment;

import android.app.Fragment;

import com.cavemen.inception.R;
import com.cavemen.inception.events.FloorSelectedEvent;

import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.floordesc_fragment_layout)
public class FloorDescriptionFragment extends Fragment {


    public void onEvent(FloorSelectedEvent event) {

    }

}
