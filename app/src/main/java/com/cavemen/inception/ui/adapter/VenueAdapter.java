package com.cavemen.inception.ui.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.cavemen.inception.ui.fragment.VenueFragment_;

public class VenueAdapter extends FragmentStatePagerAdapter {

    public VenueAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return VenueFragment_.builder()
                .buildingId(position)
                .build();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Building name: " + (position + 1);
    }
}
