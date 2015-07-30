package com.jamen.chen.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jamen.chen.bigchat.R;

public class ContactFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceStExpandableListViewate) {

		/*
		 * if(getArguments()!=null){ mTitle=getArguments().getString(TITLE); }
		 * 
		 * TextView tv=new TextView(getActivity()); tv.setText(mTitle);
		 * tv.setTextSize(30); return tv;
		 */
		View view = inflater.inflate(R.layout.contratc, null); // View
		return view;
	}

}
