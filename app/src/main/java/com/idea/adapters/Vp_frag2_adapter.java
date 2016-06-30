package com.idea.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Chan on 2016/6/30.
 *
 * 想法界面的外层ViewPager适配器,与TabLayout配合使用
 */
public class Vp_frag2_adapter extends FragmentPagerAdapter {

	private String[] titleArray;
	private List<Fragment> fragmentList;

	public Vp_frag2_adapter (FragmentManager fm, String[] titleArray,
	                         List<Fragment> fragmentList) {
		super(fm);
		this.titleArray = titleArray;
		this.fragmentList = fragmentList;
	}

	@Override
	public Fragment getItem(int position) {
		return fragmentList.get (position);
	}

	@Override
	public int getCount() {
		return fragmentList.size ();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titleArray[position % titleArray.length];
	}
}
