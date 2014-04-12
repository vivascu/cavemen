package com.cavemen.inception.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.cavemen.inception.R;
import com.cavemen.inception.ui.FloorActivity;
import com.cavemen.inception.ui.FloorActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.venue_fragment_layout)
public class VenueFragment extends Fragment {

    @FragmentArg
    String buildingName;

    @FragmentArg
    int buildingId;

    @ViewById(R.id.venue_button)
    Button mButton;

    @AfterViews
    public void afterView(){
    }

   @Click(R.id.venue_button)
    public void onClick() {
       FloorActivity_.intent(getActivity()).start();
    }
}
