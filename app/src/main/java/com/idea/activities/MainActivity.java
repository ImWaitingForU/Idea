package com.idea.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
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

public class MainActivity extends AppCompatActivity
		implements
			View.OnClickListener {

	private Toolbar toolbar;
	private TextView title;
	private Fragment curFragment; // 代表当前显示的Fragment
	private Tab1Fragment tab1Fragment;
	private Tab2Fragment tab2Fragment;
	private Tab3Fragment tab3Fragment;
	private Tab4Fragment tab4Fragment;
	private FloatingActionMenu fam;
	private FloatingActionButton fab1;
	private FloatingActionButton fab2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initFab();
		initFragment();
		initToolbar();
		initBottomBar(savedInstanceState);
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

	/**
	 * 切换页面时改变FAB颜色
	 * 
	 * @param color
	 */
	private void switchFABColor(String color) {
		fam.setMenuButtonColorNormal(Color.parseColor(color));
		fab1.setColorNormal(Color.parseColor(color));
		fab2.setColorNormal(Color.parseColor(color));
	}

	/**
	 * 切换页面时修改Toolbar标题
	 */
	private void switchToolBarTitle(int color, String newTitle) {
		toolbar.setBackgroundColor(getResources().getColor(color));
		title.setText(newTitle);
	}

	private void initToolbar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar_main);
		assert toolbar != null;
		toolbar.setTitle("");
		setSupportActionBar(toolbar);
		title = (TextView) findViewById(R.id.tv_title_main);
		assert title != null;
		title.setText("灵感广场");
	}

	private void initFab() {
		fam = (FloatingActionMenu) findViewById(R.id.fam_main);
		assert fam != null;
		fam.setClosedOnTouchOutside(true);
		fab1 = (FloatingActionButton) findViewById(R.id.fab1);
		fab2 = (FloatingActionButton) findViewById(R.id.fab2);
		assert fab1 != null;
		fab1.setOnClickListener(this);
		assert fab2 != null;
		fab2.setOnClickListener(this);
		fam.setOnMenuButtonClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				if (fam.isOpened ()){
//					Toast.makeText (MainActivity.this, "请选择对应的功能", Toast.LENGTH_SHORT).show ();
//				}
				fam.toggle (true);
			}
		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.fab1 :
				// TODO 点击跳转到记录灵感界面
				Toast.makeText(MainActivity.this, "fab1Pressed",
				               Toast.LENGTH_SHORT).show();
				fam.toggle(true);
				break;
			case R.id.fab2 :
				// TODO 点击跳转到记录想法界面
				Toast.makeText(MainActivity.this, "fab2Pressed",
						Toast.LENGTH_SHORT).show();
				fam.toggle(true);
				break;
			default :
				break;
		}
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
						if (tab1Fragment == null) {
							tab1Fragment = new Tab1Fragment();
						}
						switchToolBarTitle(R.color.bottom_tab1, "灵感");
						switchFragment(curFragment, tab1Fragment);
						switchFABColor("#E0572A");
						break;
					case 1 :
						if (tab2Fragment == null) {
							tab2Fragment = new Tab2Fragment();
						}
						switchToolBarTitle(R.color.bottom_tab2, "想法");
						switchFragment(curFragment, tab2Fragment);
						switchFABColor("#468CC9");
						break;
					case 2 :
						if (tab3Fragment == null) {
							tab3Fragment = new Tab3Fragment();
						}
						switchToolBarTitle(R.color.bottom_tab3, "消息");
						switchFragment(curFragment, tab3Fragment);
						switchFABColor("#609E79");
						break;
					case 3 :
						if (tab4Fragment == null) {
							tab4Fragment = new Tab4Fragment();

						}
						switchToolBarTitle(R.color.bottom_tab4, "我的");
						switchFragment(curFragment, tab4Fragment);
						switchFABColor("#9B9B9B");
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
