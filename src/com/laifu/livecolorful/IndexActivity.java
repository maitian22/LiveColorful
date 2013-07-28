package com.laifu.livecolorful;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SlidingDrawer;

import com.laifu.livecolorful.view.PullDownView;
import com.laifu.livecolorful.view.PullDownView.OnRefreshListener;

public class IndexActivity extends LiveBaseActivity {
	/***
	 * 下拉刷新的view
	 */
	public PullDownView pullDownView;
	/***
	 * 加载正文的listview
	 */
	public ListView listView;
	/***
	 * 我的日历的view 点击弹出我的日历
	 */
	public View myDiary;
	/***
	 * 右上角编辑点击出现的抽屉
	 */
	public SlidingDrawer sd1;
	/***
	 * 我的日历的view的那个小箭头
	 */
	public ImageView mydiary_image;
	/***
	 * 左上角关于
	 */
	Button btn_left;
	/***
	 * 右上角关于
	 */
	Button btn_right;
	/***
	 * 抽屉1出现的时候页面的半透明背景
	 */
	View silding1_black;
	/***
	 * 抽屉1的title，包括左取消按钮
	 */
	View sliding1_title_view;
	/***
	 * 抽屉1的btn，取消按钮
	 */
	Button sliding1_title_left;
	/***
	 * 抽屉1的创建行程单的view
	 */
	View silding1_add_view;
	/***
	 * 抽屉1的listview
	 */
	ListView silding1_listview;
	
	@Override
	public void initPages() {
		// TODO Auto-generated method stub
		myDiary = findViewById(R.id.mydiary_view);
		mydiary_image = (ImageView) findViewById(R.id.wdrl_image);

		
		
		initTitle();
		initListView();
		initSildingEdit();
	}
	/****
	 * 初始化title 左右两个button
	 */
	public void initTitle(){
		btn_left=(Button)findViewById(R.id.left);
		btn_right=(Button)findViewById(R.id.right);
		btn_left.setOnClickListener(this);
		btn_right.setOnClickListener(this);
	}
	/***
	 * 初始化页面正文的listview
	 */
	public void initListView() {
		pullDownView = (PullDownView) findViewById(R.id.feeds);
		pullDownView.init();
		pullDownView.setFooterView(R.layout.footer_item);

		pullDownView.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				pullDownView.notifyRefreshComplete();
			}
		});
		listView = pullDownView.getListView();

		pullDownView.showFooterView(false);
	}
	/***
	 * 初始化编辑按钮出发的抽屉
	 */
	public void initSildingEdit(){
		
		silding1_black=findViewById(R.id.silding1_black);
		sliding1_title_view=findViewById(R.id.sliding_title_view);
		sliding1_title_left=(Button)findViewById(R.id.wdrl_cjxc_left);
		silding1_add_view=findViewById(R.id.cjxc_add_view);
		silding1_listview=(ListView)findViewById(R.id.sliding1_listview);
		
		sd1 = (SlidingDrawer) findViewById(R.id.sliding_add);
		
		sd1.setVisibility(View.GONE);
		silding1_black.setOnClickListener(this);
		sliding1_title_left.setOnClickListener(this);
		silding1_add_view.setOnClickListener(this);
		sd1.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener()// 开抽屉
		{
			@Override
			public void onDrawerOpened() {
				mydiary_image.setImageResource(R.drawable.wdrl_zk_btn);// 响应开抽屉事件
				// ，把图片设为向下的
			}
		});
		sd1.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
			@Override
			public void onDrawerClosed() {
				mydiary_image.setImageResource(R.drawable.wdrl_sq_btn);// 响应关抽屉事件
				silding1_black.setVisibility(View.GONE);
				sd1.setVisibility(View.GONE);
			}
		});
		
	}
	
	
	
	
	
	
	
	
	
	@Override
	protected void onClickListener(int viewId) {
		// TODO Auto-generated method stub
		switch(viewId){
		case R.id.left:
			break;
		case R.id.right:
			silding1_black.setVisibility(View.VISIBLE);
			sd1.setVisibility(View.VISIBLE);
			sd1.animateOpen();
			break;
		case R.id.silding1_black:
		case R.id.wdrl_cjxc_left:
			cancleSliding1();
			break;
		case R.id.cjxc_add_view:
			// 点击创建行程单
			break;
		}
	}
	/***
	 * 取消编辑的抽屉
	 */
	public void cancleSliding1(){
		silding1_black.setVisibility(View.GONE);
		sd1.setVisibility(View.GONE);
		sd1.animateClose();
	}
	
	@Override
	public int setModelId() {
		// TODO Auto-generated method stub
		return R.layout.index;
	}

	@Override
	public int setLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.index;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

}
