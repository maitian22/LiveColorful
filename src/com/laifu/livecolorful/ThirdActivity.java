package com.laifu.livecolorful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

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
	private ListView my_rount_list;
	private RelativeLayout my_rount,my_attation,my_fans,my_info;
	private Context mContext;

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
			initHighlight();
		}

	};

	private void initHighlight() {
		Log.i(TAG,"initHighlightArrow,mCurrentPage:"+mCurrentPage);
		my_rount_arrow.setVisibility(android.view.View.INVISIBLE);
		my_fans_arrow.setVisibility(android.view.View.INVISIBLE);
		my_attation_arrow.setVisibility(android.view.View.INVISIBLE);
		my_info_arrow.setVisibility(android.view.View.INVISIBLE);
		
		my_rount.setVisibility(android.view.View.INVISIBLE);
		my_fans.setVisibility(android.view.View.INVISIBLE);
		my_attation.setVisibility(android.view.View.INVISIBLE);
		my_info.setVisibility(android.view.View.INVISIBLE);
		
		if (mCurrentPage == 0) {
			my_rount_arrow.setVisibility(android.view.View.VISIBLE);
			my_rount.setVisibility(android.view.View.VISIBLE);
		} else if (mCurrentPage == 1) {
			my_attation_arrow.setVisibility(android.view.View.VISIBLE);
			my_attation.setVisibility(android.view.View.VISIBLE);
		} else if (mCurrentPage == 2) {
			my_fans_arrow.setVisibility(android.view.View.VISIBLE);
			my_fans.setVisibility(android.view.View.VISIBLE);
		} else if (mCurrentPage == 3) {
			my_info_arrow.setVisibility(android.view.View.VISIBLE);
			my_info.setVisibility(android.view.View.VISIBLE);
		}
	}
	private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "张根硕全球电视通告-中国歌迷会北京分会朝阳区分会");
        map.put("info", "23580266个成员     67556个行程");
        map.put("img", R.drawable.progressbar);
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("title", "建筑系12级3班 同路人");
        map.put("info", "52个成员     47个行程");
        map.put("img", R.drawable.progressbar);
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("title", "G3");
        map.put("info", "google 3");
        map.put("img", R.drawable.progressbar);
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("title", "G3");
        map.put("info", "google 3");
        map.put("img", R.drawable.progressbar);
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("title", "G3");
        map.put("info", "google 3");
        map.put("img", R.drawable.progressbar);
        list.add(map);
        
        map = new HashMap<String, Object>();
        map.put("title", "G3");
        map.put("info", "google 3");
        map.put("img", R.drawable.progressbar);
        list.add(map);
        return list;
    }
	
	private void InitRountListView(){
		SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.my_rount_list,
                new String[]{"title","info","img"},
                new int[]{R.id.title,R.id.info,R.id.img});
		my_rount_list.setAdapter(adapter);
	}
	private void initView() {
		mPre = PreferenceManager.getDefaultSharedPreferences(this);
		my_rount_linear = (LinearLayout) findViewById(R.id.my_rount_linear);
		my_rount_linear.setOnClickListener(mylinearListner);
		my_rount_arrow = (ImageView)findViewById(R.id.my_rount_arrow);
		my_rount_list = (ListView)findViewById(R.id.my_rount_list);
		my_rount = (RelativeLayout)findViewById(R.id.my_rount);
		InitRountListView();

		my_fans_linear = (LinearLayout) findViewById(R.id.my_fans_linear);
		my_fans_linear.setOnClickListener(mylinearListner);
		my_fans_arrow = (ImageView)findViewById(R.id.my_fans_arrow);
		my_fans = (RelativeLayout)findViewById(R.id.my_fans);

		my_attation_linear = (LinearLayout) findViewById(R.id.my_attation_linear);
		my_attation_linear.setOnClickListener(mylinearListner);
		my_attation_arrow = (ImageView)findViewById(R.id.my_attation_arrow);
		my_attation = (RelativeLayout)findViewById(R.id.my_attation);

		my_info_linear = (LinearLayout) findViewById(R.id.my_info_linear);
		my_info_linear.setOnClickListener(mylinearListner);
		my_info_arrow = (ImageView)findViewById(R.id.my_info_arrow);
		my_info = (RelativeLayout)findViewById(R.id.my_info);
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
		initHighlight();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
//		setContentView(R.layout.activity_my_account);

		
	}
}
