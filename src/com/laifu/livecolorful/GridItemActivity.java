package com.laifu.livecolorful;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laifu.livecolorful.adapter.MyRountListAdapter;

public class GridItemActivity extends Activity implements View.OnClickListener {
	private int clickNumber;
	private String TAG = "GridItemActivity";
	private String mtext = "";
	private Button mLeftBtn, mRightBtn;
	private TextView mTextTitle;
	private RelativeLayout mLatest, mHotest, mNearBy;
	private TextView mLatestText, mHotestText, mNearByText;
	private ListView mlistView;
	private MyRountListAdapter mAdapter;
	private Context mContext;

	private void InitTitle() {
		mTextTitle = (TextView) findViewById(R.id.title);
		mTextTitle.setText(mtext);

		mLeftBtn = (Button) findViewById(R.id.left);
		mLeftBtn.setVisibility(android.view.View.INVISIBLE);

		mRightBtn = (Button) findViewById(R.id.right);
		mRightBtn.setBackgroundResource(R.drawable.gb_btn);
		mRightBtn.setOnClickListener(this);
	}

	View.OnClickListener mButtonClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mLatestText.setBackgroundResource(R.color.arrow_bg);
			mHotestText.setBackgroundResource(R.color.arrow_bg);
			mNearByText.setBackgroundResource(R.color.arrow_bg);
			if (v == mLatest) {
				mLatestText.setBackgroundResource(R.drawable.grid_item_shape);
				mAdapter = new MyRountListAdapter(mContext,
						GlobaleData.getLatestData(), new String[] { "leftimg",
								"lines", "title", "text1" }, new int[] {
								R.id.left_img, R.id.mlines, R.id.title,
								R.id.text1 });
			} else if (v == mHotest) {
				mHotestText.setBackgroundResource(R.drawable.grid_item_shape);
				mAdapter = new MyRountListAdapter(mContext,
						GlobaleData.getHotestData(), new String[] { "leftimg",
								"lines", "title", "text1", "text2", "text3" },
						new int[] { R.id.left_img, R.id.mlines, R.id.title,
								R.id.text1, R.id.text2, R.id.text3 });
			} else if (v == mNearBy) {
				mNearByText.setBackgroundResource(R.drawable.grid_item_shape);
				mAdapter = new MyRountListAdapter(mContext,
						GlobaleData.getMyNearByData(), new String[] {
								"leftimg", "lines", "title", "img1", "text1" },
						new int[] { R.id.left_img, R.id.mlines, R.id.title,
								R.id.img1, R.id.text1 });
			}
			mlistView.setAdapter(mAdapter);
		}

	};

	private void InitPageView() {
		mLatest = (RelativeLayout) findViewById(R.id.latest);
		mLatestText = (TextView) findViewById(R.id.latest_text);
		mLatest.setOnClickListener(mButtonClickListener);

		mHotest = (RelativeLayout) findViewById(R.id.hotest);
		mHotest.setOnClickListener(mButtonClickListener);
		mHotestText = (TextView) findViewById(R.id.hot_text);

		mNearBy = (RelativeLayout) findViewById(R.id.nearby);
		mNearBy.setOnClickListener(mButtonClickListener);
		mNearByText = (TextView) findViewById(R.id.nearby_text);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

	}

	private void InitListView() {
		mLatestText.setBackgroundResource(R.color.arrow_bg);
		mHotestText.setBackgroundResource(R.drawable.grid_item_shape);
		mNearByText.setBackgroundResource(R.color.arrow_bg);
		mHotestText.setBackgroundResource(R.drawable.grid_item_shape);
		mAdapter = new MyRountListAdapter(mContext,
				GlobaleData.getHotestData(), new String[] { "leftimg", "lines",
						"title", "text1", "text2", "text3" }, new int[] {
						R.id.left_img, R.id.mlines, R.id.title, R.id.text1,
						R.id.text2, R.id.text3 });
		mlistView.setAdapter(mAdapter);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.grid_item_activity);
		mContext = this;
		Intent i = this.getIntent();
		clickNumber = i.getIntExtra("clickNumber", 0);
		mtext = i.getStringExtra("titleName");
		Log.i(TAG, "clickNumber:" + clickNumber + ",title:" + mtext);
		InitTitle();
		InitPageView();

		mlistView = (ListView) findViewById(R.id.my_list);
		InitListView();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == mRightBtn) {
			finish();
		}
	}
}
