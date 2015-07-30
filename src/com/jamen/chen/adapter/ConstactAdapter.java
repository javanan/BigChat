package com.jamen.chen.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Toast;

public class ConstactAdapter extends BaseExpandableListAdapter {

	// 字符串
	private List<String> strList;
	private Context context;
	private LayoutInflater layoutInflater;
	
	public ConstactAdapter(Context context, List<String> strList) {
		super();
		this.context = context;
		this.layoutInflater = LayoutInflater.from(context);
		this.strList = strList;
		if (strList == null) {
			strList = new ArrayList<String>();
		}
		long time = System.currentTimeMillis();
		
		//test
		Toast.makeText(context,
				String.valueOf(System.currentTimeMillis() - time), 1).show();
	}
	
	
	
	@Override
	public int getGroupCount() {
		return 0;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		return null;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		return null;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
