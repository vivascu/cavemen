package com.cavemen.inception.ui.fragment;

import android.app.Fragment;

import com.cavemen.inception.R;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

@EFragment(R.layout.venue_fragment_layout)
public class VenueFragment extends Fragment {

    @FragmentArg
    String buildingName;

    @FragmentArg
    int buildingId;

}
