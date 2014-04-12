package com.cavemen.inception.ui.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.cavemen.inception.R;
import com.cavemen.inception.model.Table;
import com.cavemen.inception.model.TableStatus;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

/**
 * Created by vivascu on 4/13/2014.
 */
@EFragment
public class TableDialogFragment extends DialogFragment {
    @FragmentArg
    Table mTable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutID =  R.layout.empty_dialog_layot;;
        if(mTable.getStatus().equals(TableStatus.EMPTY)){
            layoutID = R.layout.empty_dialog_layot;
        }
        if(mTable.getStatus().equals(TableStatus.BOOKED)){
            layoutID = R.layout.booked_dialog_layout;
        }
        if(mTable.getStatus().equals(TableStatus.OCCUPIED)){
            layoutID = R.layout.occupied_dialog_layout;
        }
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View v = inflater.inflate(layoutID, container, false);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
