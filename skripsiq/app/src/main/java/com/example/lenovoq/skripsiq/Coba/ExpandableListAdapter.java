package com.example.lenovoq.skripsiq.Coba;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import com.example.lenovoq.skripsiq.R;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity context;
//    private Map<String, List<String>> listpertemuan;
    private List listpertemuan;
    private List<String> listjadwal;

    public ExpandableListAdapter(Activity context, List<String> groupListjdwl,
                                  List<String> childListjdwl) {
        this.context = context;
        this.listpertemuan = childListjdwl;
        this.listjadwal = groupListjdwl;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return listpertemuan.get(Integer.parseInt(listjadwal.get(groupPosition)));
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String laptop = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.article_list_child_item_layout, null);
        }

        TextView item = (TextView) convertView.findViewById(R.id.articleContentTextView);

        item.setText(laptop);
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return (int) listpertemuan.get(Integer.parseInt(listjadwal.get(groupPosition)));
    }

    public Object getGroup(int groupPosition) {
        return listjadwal.get(groupPosition);
    }

    public int getGroupCount() {
        return listjadwal.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String jadwal = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.article_list_item_layout,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.articleHeaderTextView);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(jadwal);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}