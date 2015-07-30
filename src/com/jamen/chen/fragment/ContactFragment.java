package com.jamen.chen.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.jamen.chen.adapter.ConstactAdapter;
import com.jamen.chen.bigchat.R;
import com.jamen.chen.bigchat.view.AssortView;

public class ContactFragment extends Fragment {
	private ConstactAdapter adapter;
	private ExpandableListView elistview;
	private List<String> names;
	private AssortView assortView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData();
	}

	private void initData() {
		names = new ArrayList<String>();
		names.add("lxz");
		names.add("A酱");
		names.add("芙兰");
		names.add("鱼鱼");
		names.add("妹妹");
		names.add("你好");
		names.add("林小姐");
		names.add("联盟");
		names.add("L");
		names.add("xdsfsdggsdsf");
		names.add("星星");
		names.add("靴刀誓死");
		names.add("Java");
		names.add("倒塌");
		names.add("黑人");
		names.add("a妹");
		names.add("aYa");

		adapter = new ConstactAdapter(getActivity(), names);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceStExpandableListViewate) {
		View view = inflater.inflate(R.layout.contratc, null); // View
		elistview = (ExpandableListView) view.findViewById(R.id.elist);
		assortView = (AssortView) view.findViewById(R.id.assort);
		elistview.setAdapter(adapter);

		// 展开所有
		for (int i = 0, length = adapter.getGroupCount(); i < length; i++) {
			elistview.expandGroup(i);
		}
		return view;
	}

}
