package com.jamen.chen.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FindFragment extends Fragment {

	private String mTitle="default";
	public static String TITLE="tittle";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceStExpandableListViewate) {
		
		if(getArguments()!=null){
			mTitle=getArguments().getString(TITLE);
		}
		
		TextView tv=new TextView(getActivity());
		tv.setText(mTitle);
		tv.setTextSize(30);
		return tv;
		//return super.onCreateView(inflater, container, savedInstanceState);
	}


	
}
