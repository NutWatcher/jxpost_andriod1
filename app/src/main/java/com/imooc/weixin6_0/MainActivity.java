package com.imooc.weixin6_0;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Toast;

import com.activity.AccountSearch;
import com.activity.Activity_AccountInfo;

public class MainActivity extends FragmentActivity implements OnClickListener,
		OnPageChangeListener,fragment_accountList.OnFragmentInteractionListener
{


	private ViewPager mViewPager;
	private List<Fragment> mTabs = new ArrayList<Fragment>();
	private String[] mTitles = new String[]
	{ "Account", "Third Fragment !"};
	private FragmentPagerAdapter mAdapter;

	private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<ChangeColorIconWithText>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setOverflowButtonAlways();
		getActionBar().setDisplayShowHomeEnabled(false);

		initView();
		initDatas();
		mViewPager.setAdapter(mAdapter);
		initEvent();

	}

	/**
	 * 初始化所有事件
	 */
	private void initEvent()
	{
		mViewPager.setOnPageChangeListener(this);
	}

	private void initDatas()
	{
		for (String title : mTitles)
		{
			if ( title == "Account"){
				fragment_accountList myfragment = new fragment_accountList();
				Bundle bundle = new Bundle();
				bundle.putString(title, title);
				myfragment.setArguments(bundle);
				mTabs.add(myfragment);
			}
			else{
				TabFragment tabFragment = new TabFragment();
				Bundle bundle = new Bundle();
				bundle.putString(TabFragment.TITLE, title);
				tabFragment.setArguments(bundle);
				mTabs.add(tabFragment);
			}
		}

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{

			@Override
			public int getCount()
			{
				return mTabs.size();
			}

			@Override
			public Fragment getItem(int position)
			{
				return mTabs.get(position);
			}
		};
	}

	private void initView()
	{
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

		ChangeColorIconWithText two = (ChangeColorIconWithText) findViewById(R.id.id_indicator_two);
		mTabIndicators.add(two);
		ChangeColorIconWithText four = (ChangeColorIconWithText) findViewById(R.id.id_indicator_four);
		mTabIndicators.add(four);

		two.setOnClickListener(this);
		four.setOnClickListener(this);

		two.setIconAlpha(1.0f);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_search:
				goActivity_Search();
				break;
			case Menu.FIRST + 6:
				Toast.makeText(this, "发送菜单被点击了", Toast.LENGTH_LONG).show();
				break;
		}
		return false;
	}
	private void setOverflowButtonAlways()
	{
		try
		{
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKey = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			menuKey.setAccessible(true);
			menuKey.setBoolean(config, false);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 设置menu显示icon
	 */
	@Override
	public boolean onMenuOpened(int featureId, Menu menu)
	{

		if (featureId == Window.FEATURE_ACTION_BAR && menu != null)
		{
			if (menu.getClass().getSimpleName().equals("MenuBuilder"))
			{
				try
				{
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}

		return super.onMenuOpened(featureId, menu);
	}

	@Override
	public void onClick(View v)
	{
		clickTab(v);
	}

	/**
	 * 点击Tab按钮
	 * 
	 * @param v
	 */
	private void clickTab(View v)
	{
		resetOtherTabs();

		switch (v.getId())
		{
		case R.id.id_indicator_two:
			mTabIndicators.get(0).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(0, false);
			break;
		case R.id.id_indicator_four:
			mTabIndicators.get(1).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(1, false);
			break;
		}
	}

	/**
	 * 重置其他的TabIndicator的颜色
	 */
	private void resetOtherTabs()
	{
		for (int i = 0; i < mTabIndicators.size(); i++)
		{
			mTabIndicators.get(i).setIconAlpha(0);
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels)
	{
		// Log.e("TAG", "position = " + position + " ,positionOffset =  "
		// + positionOffset);
		if (positionOffset > 0)
		{
			ChangeColorIconWithText left = mTabIndicators.get(position);
			ChangeColorIconWithText right = mTabIndicators.get(position + 1);
			left.setIconAlpha(1 - positionOffset);
			right.setIconAlpha(positionOffset);
		}

	}

	@Override
	public void onPageSelected(int position)
	{

	}

	@Override
	public void onPageScrollStateChanged(int state)
	{

	}

	@Override
	public void onFragmentInteraction(String uri) {
		Log.i("log", uri);
		Intent intent = new Intent(MainActivity.this, Activity_AccountInfo.class);
		startActivityForResult(intent, 1 );
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	}
	private void goActivity_Search(){
		Intent intent = new Intent(MainActivity.this, AccountSearch.class);
		startActivityForResult(intent, 1 );
	}
}
