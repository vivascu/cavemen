package com.cavemen.inception.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cavemen.inception.model.Project;
import com.cavemen.inception.ui.component.ProjectListItem;
import com.cavemen.inception.ui.component.ProjectListItem_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ggodonoga on 12/04/2014.
 */
@EBean
public class ProjectsAdapter extends BaseAdapter {

    final List<Project> projectList = new ArrayList<Project>();

    @RootContext
    Context context;

    public void setProjects(List<Project> projects) {
        projectList.clear();
        projectList.addAll(projects);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return projectList.size();
    }

    @Override
    public Project getItem(int position) {
        if (position < projectList.size()) {
            return projectList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProjectListItem projectListItem;
        if (convertView == null) {
            projectListItem = ProjectListItem_.build(context);
        } else {
            projectListItem = (ProjectListItem) convertView;
        }
        if (position < projectList.size()) {
            Project proj = getItem(position);
            projectListItem.bindItem(proj.getTitle(), proj.getLogoUri());
        }
        return projectListItem;
    }
}
