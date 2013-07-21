package com.laifu.livecolorful;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ThirdActivity extends LiveBaseActivity {

	@Override
	public void initPages() {
		// TODO Auto-generated method stub
		initView();
		initTitle();
	}

	@Override
	protected void onClickListener(int viewId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int setModelId() {
		// TODO Auto-generated method stub
		return R.layout.third;
	}

	@Override
	public int setLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.third;
	}
	
	private static String TAG = "MyAccouontActivity";
	private LinearLayout my_rount_linear, my_fans_linear, my_attation_linear,
			my_info_linear;
	private ImageView my_rount_arrow, my_fans_arrow, my_attation_arrow, my_info_arrow;
	private SharedPreferences mPre;
	private int mCurrentPage;
	private static String CURRENT_PAGE = "current_page";

	private OnClickListener mylinearListner = new LinearLayout.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.my_rount_linear:
				mCurrentPage = 0;
				break;
			case R.id.my_attation_linear:
				mCurrentPage = 1;
				break;
			case R.id.my_fans_linear:
				mCurrentPage = 2;
				break;
			case R.id.my_info_linear:
				mCurrentPage = 3;
				break;
			}
			mPre.edit().putInt(CURRENT_PAGE, mCurrentPage).commit();
			initHighlightArrow();
		}

	};

	private void initHighlightArrow() {
		Log.i(TAG,"initHighlightArrow,mCurrentPage:"+mCurrentPage);
		my_rount_arrow.setVisibility(android.view.View.INVISIBLE);
		my_fans_arrow.setVisibility(android.view.View.INVISIBLE);
		my_attation_arrow.setVisibility(android.view.View.INVISIBLE);
		my_info_arrow.setVisibility(android.view.View.INVISIBLE);
		
		if (mCurrentPage == 0) {
			my_rount_arrow.setVisibility(android.view.View.VISIBLE);
		} else if (mCurrentPage == 1) {
			my_attation_arrow.setVisibility(android.view.View.VISIBLE);
		} else if (mCurrentPage == 2) {
			my_fans_arrow.setVisibility(android.view.View.VISIBLE);
		} else if (mCurrentPage == 3) {
			my_info_arrow.setVisibility(android.view.View.VISIBLE);
		}
	}

	private void initView() {
		mPre = PreferenceManager.getDefaultSharedPreferences(this);
		my_rount_linear = (LinearLayout) findViewById(R.id.my_rount_linear);
		my_rount_linear.setOnClickListener(mylinearListner);
		my_rount_arrow = (ImageView)findViewById(R.id.my_rount_arrow);

		my_fans_linear = (LinearLayout) findViewById(R.id.my_fans_linear);
		my_fans_linear.setOnClickListener(mylinearListner);
		my_fans_arrow = (ImageView)findViewById(R.id.my_fans_arrow);

		my_attation_linear = (LinearLayout) findViewById(R.id.my_attation_linear);
		my_attation_linear.setOnClickListener(mylinearListner);
		my_attation_arrow = (ImageView)findViewById(R.id.my_attation_arrow);

		my_info_linear = (LinearLayout) findViewById(R.id.my_info_linear);
		my_info_linear.setOnClickListener(mylinearListner);
		my_info_arrow = (ImageView)findViewById(R.id.my_info_arrow);
	}
	
	public void initTitle(){
		left.setBackgroundResource(R.drawable.sz_btn);
		title.setText("我的账号");
		right.setVisibility(View.GONE);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mCurrentPage = mPre.getInt(CURRENT_PAGE, 0);
		initHighlightArrow();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_my_account);

		
	}
}
