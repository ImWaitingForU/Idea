package com.idea.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.idea.adapters.Vp_frag2_adapter;
import com.idea.idea.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 想法页面
 */
public class Tab2Fragment extends Fragment implements View.OnClickListener {

	private TabLayout tabLayout;
	private ViewPager viewPager;

	private List<String> titleList;
	private List<Fragment> fragmentList;
	private Vp_frag2_adapter adapter;

	public Tab2Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tab2, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initTabLayout(view);

		ImageButton iv_add = (ImageButton) view.findViewById(R.id.ib_add_frag2);
		iv_add.setOnClickListener(this);
	}

	/**
	 * 初始化TabLayout，先初始化ViewPager再初始化TabLayout
	 */
	private void initTabLayout(View view) {
		tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_frag2);
		viewPager = (ViewPager) view.findViewById(R.id.vp_frag2);

		titleList = new ArrayList<>();
		titleList.add("首页");
		fragmentList = new ArrayList<>();
		fragmentList.add(new TestFragment());
		// for (int i = 0; i < 8; i++) {
		// fragmentList.add(new TestFragment());
		// }

		adapter = new Vp_frag2_adapter(getActivity()
				.getSupportFragmentManager(), titleList, fragmentList);
		viewPager.setAdapter(adapter);

		tabLayout.setupWithViewPager(viewPager);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ib_add_frag2 :
				// TODO 添加新的Tab
				addNewTab("新标签");
				break;
			default :
				break;
		}
	}

	/**
	 * 点击按钮动态添加标签和Fragment
	 */
	private void addNewTab(String title) {
		fragmentList.add(new TestFragment());
		titleList.add(title);
		adapter.notifyDataSetChanged();
		viewPager.setCurrentItem (viewPager.getChildCount ());
	}
}
