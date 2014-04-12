package com.cavemen.inception.ui.component;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cavemen.inception.R;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by ggodonoga on 12/04/2014.
 */

@EViewGroup(R.layout.project_item_row)
public class ProjectListItem extends LinearLayout {

    @ViewById
    ImageView clientLogoImageView;

    @ViewById
    TextView projectNameField;

    public ProjectListItem(Context context) {
        super(context);
    }

    public void bindItem(String title, String logoUri) {
        projectNameField.setText(title);
        Picasso.with(this.getContext()).load(logoUri).into(clientLogoImageView);
    }
}
