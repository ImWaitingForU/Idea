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

	private List<String> titleList;
	private List<Fragment> fragmentList;

	public Vp_frag2_adapter(FragmentManager fm, List<String> titleList,
			List<Fragment> fragmentList) {
		super(fm);
		this.titleList = titleList;
		this.fragmentList = fragmentList;
	}

	@Override
	public Fragment getItem(int position) {
		return fragmentList.get(position);
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titleList.get(position % titleList.size());
	}
}
