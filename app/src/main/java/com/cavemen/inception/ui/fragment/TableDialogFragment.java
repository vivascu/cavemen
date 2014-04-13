package com.cavemen.inception.ui.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.cavemen.inception.R;
import com.cavemen.inception.model.Person;
import com.cavemen.inception.model.Table;
import com.cavemen.inception.model.TableStatus;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * Created by vivascu on 4/13/2014.
 */
@EFragment
public class TableDialogFragment extends DialogFragment {
    @FragmentArg
    Table mTable;

    @FragmentArg
    Person mPerson;

    @ViewById(R.id.person_name)
    TextView mFullName;
    @ViewById(R.id.person_birthday)
    TextView mBirthday;
    @ViewById(R.id.person_email)
    TextView mEmail;
    @ViewById(R.id.person_gender)
    TextView mGender;
    @ViewById(R.id.person_job_title)
    TextView mJobTitle;
    @ViewById(R.id.person_linemanger)
    TextView mManager;
    @ViewById(R.id.person_phone)
    TextView mPhone;

    @ViewById(R.id.person_image)
    ImageView imageView;

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

    @AfterViews
    public void afterViews(){
        mFullName.setText(mPerson.getFirstName()+ " " + mPerson.getLastName());
        //mPhone.setText(mPerson.getPersonalPhone());
        mJobTitle.setText(mPerson.getJobTitle());
        Picasso.with(getActivity()).load(mPerson.getPhotoUri()).into(imageView);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
