package com.idea.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;
import com.idea.fragments.Tab1Fragment;
import com.idea.fragments.Tab2Fragment;
import com.idea.fragments.Tab3Fragment;
import com.idea.fragments.Tab4Fragment;
import com.idea.idea.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabClickListener;

public class MainActivity extends AppCompatActivity {

	private Toolbar toolbar;
	private TextView title;
	private RelativeLayout topBar;
	private Fragment curFragment; // 代表当前显示的Fragment
	private Tab1Fragment tab1Fragment;
	private Tab2Fragment tab2Fragment;
	private Tab3Fragment tab3Fragment;
	private Tab4Fragment tab4Fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initFragment();
		initToolbar();
		initBottomBar(savedInstanceState);
		initFab();

	}

	/**
	 * 为页面加载初始状态的Fragment
	 */
	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		if (curFragment == null) {
			tab1Fragment = new Tab1Fragment();
		}
		curFragment = tab1Fragment;
		ft.replace(R.id.content_main, tab1Fragment).commit();
	}

	/**
	 * 切换fragment,隐藏之前的Fragment,且可以保存fragment数据，保证切换fragment数据不会清空
	 */
	private void switchFragment(Fragment from, Fragment to) {
		if (curFragment != to) {
			curFragment = to;
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			if (!to.isAdded()) {
				// 如果to没有添加，则先隐藏之前的再add
				ft.hide(from).add(R.id.content_main, to).commit();
			} else {
				ft.hide(from).show(to).commit();
			}
		}
	}

	private void initToolbar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar_main);
		toolbar.setTitle("");
		setSupportActionBar(toolbar);
		title = (TextView) findViewById(R.id.tv_title_main);
		title.setText("灵感广场");
	}

	private void initFab() {
		FloatingActionMenu fam = (FloatingActionMenu) findViewById(R.id.fab_menu_main);

	}

	private void initBottomBar(Bundle savedInstanceState) {
		// final BottomBar bottomBar = BottomBar.attachShy(
		// (CoordinatorLayout) findViewById(R.id.coorLayout_main),
		// findViewById(R.id.nsv_main), savedInstanceState);
		BottomBar bottomBar = BottomBar.attach(this, savedInstanceState);
		// bottomBar.useFixedMode ();
		bottomBar.noNavBarGoodness();
		bottomBar.noTopOffset();
		bottomBar.setItems(new BottomBarTab(R.drawable.ic_bottom_tab1, "广场"),
				new BottomBarTab(R.drawable.ic_bottom_tab2, "想法"),
				new BottomBarTab(R.drawable.ic_bottom_tab3, "消息"),
				new BottomBarTab(R.drawable.ic_bottom_tab4, "我的"));
		bottomBar.setDefaultTabPosition(0);
		bottomBar.mapColorForTab(0, getResources()
				.getColor(R.color.bottom_tab1));
		bottomBar.mapColorForTab(1, getResources()
				.getColor(R.color.bottom_tab2));
		bottomBar.mapColorForTab(2, getResources()
				.getColor(R.color.bottom_tab3));
		bottomBar.mapColorForTab(3, getResources()
				.getColor(R.color.bottom_tab4));
		BottomBarBadge barBadge = bottomBar.makeBadgeForTabAt(2, Color.RED, 10);
		barBadge.setAutoShowAfterUnSelection(true);

		bottomBar.setOnTabClickListener(new OnTabClickListener() {
			@Override
			public void onTabSelected(int position) {
				switch (position) {
					case 0 :
						toolbar.setBackgroundColor(getResources().getColor(
								R.color.bottom_tab1));
						title.setText("灵感广场");
						if (tab1Fragment == null) {
							tab1Fragment = new Tab1Fragment();
						}
						switchFragment(curFragment, tab1Fragment);
						break;
					case 1 :
						toolbar.setBackgroundColor(getResources().getColor(
								R.color.bottom_tab2));
						title.setText("想法");
						if (tab2Fragment == null) {
							tab2Fragment = new Tab2Fragment();
						}
						switchFragment(curFragment, tab2Fragment);
						break;
					case 2 :
						toolbar.setBackgroundColor(getResources().getColor(
								R.color.bottom_tab3));
						title.setText("消息");
						if (tab3Fragment == null) {
							tab3Fragment = new Tab3Fragment();
						}
						switchFragment(curFragment, tab3Fragment);
						break;
					case 3 :
						toolbar.setBackgroundColor(getResources().getColor(
								R.color.bottom_tab4));
						title.setText("我的");
						if (tab4Fragment == null) {
							tab4Fragment = new Tab4Fragment();
						}
						switchFragment(curFragment, tab4Fragment);

						break;
					default :
						break;
				}
			}

			@Override
			public void onTabReSelected(int position) {

			}
		});
	}
}
