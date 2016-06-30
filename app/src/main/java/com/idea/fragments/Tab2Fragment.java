package com.idea.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idea.adapters.Vp_frag2_adapter;
import com.idea.idea.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 想法页面
 */
public class Tab2Fragment extends Fragment {

	private View view;
	private TabLayout tabLayout;
	private ViewPager viewPager;
	public Tab2Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_tab2, container, false);
		initTabLayout();
		return view;
	}

	/**
	 * 初始化TabLayout，先初始化ViewPager再初始化TabLayout
	 */
	private void initTabLayout() {
		tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_frag2);
		viewPager = (ViewPager) view.findViewById(R.id.vp_frag2);


		String[] titleArray = new String[]{"首页", "生活", "创意", "猎奇", "娱乐", "个人",
				"花卉", "纪念品"};
		List<Fragment> fragmentLists = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			fragmentLists.add(new TestFragment());
//			tabLayout.addTab(tabLayout.newTab().setText(titleArray[i]));
		}


		Vp_frag2_adapter adapter = new Vp_frag2_adapter(getActivity()
				.getSupportFragmentManager(), titleArray, fragmentLists);
		viewPager.setAdapter(adapter);

		tabLayout.setupWithViewPager(viewPager);
	}

}
