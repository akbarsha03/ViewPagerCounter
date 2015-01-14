package com.breakandroid.viewpagercounter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity {

	/**
	 * The number of pages (wizard steps) to show in this demo.
	 */
	private static final int NUM_PAGES = 3;

	/**
	 * The pager widget, which handles animation and allows swiping horizontally
	 * to access previous and next wizard steps.
	 */
	private ViewPager mPager;

	/**
	 * The pager adapter, which provides the pages to the view pager widget.
	 */
	private PagerAdapter mPagerAdapter;

	private Button button;

	private LinearLayout ll;

	private int required_size;

	private int[] counters;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);

		ll = (LinearLayout) findViewById(R.id.viewPagerCounter);

		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

		Display display = wm.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();

		display.getMetrics(metrics);

		required_size = metrics.widthPixels / 20;

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				required_size, required_size, 1);
		params.rightMargin = 5;
		params.leftMargin = 5;

		/**
		 * I'm calculating the required_size with the help of screen width. To
		 * handle multiple screen sizes we've to take the screen width and then
		 * calculate the percentage of it
		 */

		for (int i = 0; i < NUM_PAGES; i++) {
			button = new Button(this);
			int id = 2000 + i;
			// D.showLog("button id outer " + id);
			button.setId(id);
			counters[i] = 2000 + i;
			button.setLayoutParams(params);
			button.setBackgroundColor(Color.parseColor("#00ff00"));
			ll.addView(button);
		}

		mPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
			}

			@Override
			public void onPageScrolled(int viewPagerPosition, float arg1, int arg2) {
				for (int i = 0; i < NUM_PAGES; i++) {

					int id = 2000 + i;

					Button b = (Button) findViewById(id);

					if (i == viewPagerPosition) {

						b.setBackgroundColor(Color.parseColor("#00ff00"));
					} else {

						b.setBackgroundColor(Color.parseColor("#0000ff"));
					}

				}

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	@Override
	public void onBackPressed() {
		if (mPager.getCurrentItem() == 0) {
			// If the user is currently looking at the first step, allow the
			// system to handle the
			// Back button. This calls finish() on this activity and pops the
			// back stack.
			super.onBackPressed();
		} else {
			// Otherwise, select the previous step.
			mPager.setCurrentItem(mPager.getCurrentItem() - 1);
		}
	}

	/**
	 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects,
	 * in sequence.
	 */
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:

				return new FragmentOne();
			case 1:

				return new FragmentTwo();
			case 2:

				return new FragmentThree();
			}
			return null;
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}
	}
}
