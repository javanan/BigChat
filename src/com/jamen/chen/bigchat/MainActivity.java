package com.jamen.chen.bigchat;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;



import android.view.View.OnClickListener;
import android.view.Window;

import com.jamen.chen.bigchat.view.BottomView;
import com.jamen.chen.fragment.ChatFragment;
import com.jamen.chen.fragment.ContactFragment;
import com.jamen.chen.fragment.FindFragment;
import com.jamen.chen.fragment.MeFragment;

public class MainActivity extends FragmentActivity implements OnClickListener, OnPageChangeListener {

	private ViewPager viewPager;
	private List<Fragment> viewpage = new ArrayList<Fragment>();
	//private String[] titles = new String[] { "1", "2", "3", "4" };
	private FragmentPagerAdapter adapter;
	private List<BottomView> bottomviewList = new ArrayList<BottomView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayShowHomeEnabled(false);
		initview();
		initDate();
		viewPager.setAdapter(adapter);
		initEvent();
	}

	private void initEvent() {
		viewPager.setOnPageChangeListener(this);
	}

	private void initDate() {
		
			ChatFragment Chatfragment = new ChatFragment();
			Bundle bundle1 = new Bundle();
			bundle1.putString(Chatfragment.TITLE, "聊天");
			Chatfragment.setArguments(bundle1);
			viewpage.add(Chatfragment);
		
			ContactFragment Contractfragment = new ContactFragment();
			Bundle bundle2 = new Bundle();
			bundle2.putString(Contractfragment.TITLE, "通讯录");
			Contractfragment.setArguments(bundle2);
			viewpage.add(Contractfragment);
			
			FindFragment findfragment = new FindFragment();
			Bundle bundle3 = new Bundle();
			bundle3.putString(findfragment.TITLE, "发现");
			findfragment.setArguments(bundle3);
			viewpage.add(findfragment);
			
			MeFragment Mefragment = new MeFragment();
			Bundle bundle4 = new Bundle();
			bundle4.putString(Mefragment.TITLE, "我");
			Mefragment.setArguments(bundle4);
			viewpage.add(Mefragment);
			
			
			
			
			
		adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				return viewpage.size();
			}

			@Override
			public Fragment getItem(int position) {
				return viewpage.get(position);
			}
		};
	}

	private void initview() {
		viewPager = (ViewPager) findViewById(R.id.viewpage);
		BottomView bottomviewOne = (BottomView) findViewById(R.id.bottomview_one);
		bottomviewList.add(bottomviewOne);

		BottomView bottomviewTwo = (BottomView) findViewById(R.id.bottomview_two);
		bottomviewList.add(bottomviewTwo);

		BottomView bottomviewThree = (BottomView) findViewById(R.id.bottomview_three);
		bottomviewList.add(bottomviewThree);

		BottomView bottomviewFour = (BottomView) findViewById(R.id.bottomview_four);
		bottomviewList.add(bottomviewFour);

		bottomviewOne.setOnClickListener(this);
		bottomviewTwo.setOnClickListener(this);
		bottomviewThree.setOnClickListener(this);
		bottomviewFour.setOnClickListener(this);

		bottomviewOne.setIconAlpha(1.0f);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/***
	 * 反射 修改actionbar icon不显示的问题
	 * 
	 */
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {

		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}

	@Override
	public void onClick(View v) {
		viewListClick(v);
	}

	private void viewListClick(View v) {
		resetOtherTabs();
		switch (v.getId()) {
		case R.id.bottomview_one:
			bottomviewList.get(0).setIconAlpha(1.0f);
			viewPager.setCurrentItem(0, false);
			break;
		case R.id.bottomview_two:
			bottomviewList.get(1).setIconAlpha(1.0f);
			viewPager.setCurrentItem(1, false);
			break;
		case R.id.bottomview_three:
			bottomviewList.get(2).setIconAlpha(1.0f);
			viewPager.setCurrentItem(2, false);
			break;
		case R.id.bottomview_four:
			bottomviewList.get(3).setIconAlpha(1.0f);
			viewPager.setCurrentItem(3, false);
			break;
		}
	}

	/**
	 * 重置其他的TabIndicator的颜色
	 */
	private void resetOtherTabs() {
		for (int i = 0; i < bottomviewList.size(); i++) {
			bottomviewList.get(i).setIconAlpha(0);
		}
	}

	//setOnPageChangeListener(this)  overMethod
	@Override
	public void onPageScrollStateChanged(int arg0) {

		
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		if (positionOffset > 0)
		{
			BottomView left = bottomviewList.get(position);
			BottomView right = bottomviewList.get(position + 1);
			left.setIconAlpha(1 - positionOffset);
			right.setIconAlpha(positionOffset);
		}
		
	}

	@Override
	public void onPageSelected(int arg0) {
		
	}
}
