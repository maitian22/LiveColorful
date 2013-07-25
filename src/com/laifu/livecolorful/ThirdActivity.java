package com.laifu.livecolorful;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.laifu.livecolorful.tool.Constant;

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
	private ImageView my_rount_arrow, my_fans_arrow, my_attation_arrow,
			my_info_arrow;
	public SharedPreferences mPre;
	private int mCurrentPage;
	private ListView my_rount_list;
	private RelativeLayout my_rount, my_attation, my_fans, my_info;
	private Context mContext;
	private MyInfoCtrl mMyInfoCtrl;
	private String mheadPicPath = "";
	private ImageView head_portrait;
	private TextView mNickName, mBriefIntro;

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
			mPre.edit()
					.putInt(Constant.CURRENT_THIRD_ACTIVITY_PAGE, mCurrentPage)
					.commit();
			initHighlight();
		}

	};

	private void initHighlight() {
		Log.i(TAG, "initHighlightArrow,mCurrentPage:" + mCurrentPage);
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

	private void InitRountListView() {
		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				R.layout.my_rount_list,
				new String[] { "title", "info", "img" }, new int[] {
						R.id.title, R.id.info, R.id.img });
		my_rount_list.setAdapter(adapter);
	}

	private void initView() {
		mPre = PreferenceManager.getDefaultSharedPreferences(this);
		my_rount_linear = (LinearLayout) findViewById(R.id.my_rount_linear);
		my_rount_linear.setOnClickListener(mylinearListner);
		my_rount_arrow = (ImageView) findViewById(R.id.my_rount_arrow);
		my_rount_list = (ListView) findViewById(R.id.my_rount_list);
		my_rount = (RelativeLayout) findViewById(R.id.my_rount);
		InitRountListView();

		my_fans_linear = (LinearLayout) findViewById(R.id.my_fans_linear);
		my_fans_linear.setOnClickListener(mylinearListner);
		my_fans_arrow = (ImageView) findViewById(R.id.my_fans_arrow);
		my_fans = (RelativeLayout) findViewById(R.id.my_fans);

		my_attation_linear = (LinearLayout) findViewById(R.id.my_attation_linear);
		my_attation_linear.setOnClickListener(mylinearListner);
		my_attation_arrow = (ImageView) findViewById(R.id.my_attation_arrow);
		my_attation = (RelativeLayout) findViewById(R.id.my_attation);

		my_info_linear = (LinearLayout) findViewById(R.id.my_info_linear);
		my_info_linear.setOnClickListener(mylinearListner);
		my_info_arrow = (ImageView) findViewById(R.id.my_info_arrow);
		my_info = (RelativeLayout) findViewById(R.id.my_info);
		mMyInfoCtrl = new MyInfoCtrl(this, my_info);

		head_portrait = (ImageView) findViewById(R.id.head_portrait);

		mNickName = (TextView) findViewById(R.id.nick_name_disp);
		mBriefIntro = (TextView) findViewById(R.id.brief_intro_disp);
	}

	public void initTitle() {
		left.setBackgroundResource(R.drawable.sz_btn);
		title.setText("我的账号");
		right.setVisibility(View.GONE);
	}

	private void InitHeadPortraitImage(Uri uri) {
		Bitmap bitmap, zoombitmap;
		try {
			bitmap = BitmapFactory.decodeStream(getContentResolver()
					.openInputStream(uri));
			zoombitmap = zoomImage(bitmap, 132, 132);
			head_portrait.setImageBitmap(zoombitmap);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initNickNameAndBriefIntro() {
		mNickName.setText(mPre.getString(Constant.MY_INFO_FEATURE[1],
				Constant.DEFAULT_NICK_NAME));
		mBriefIntro.setText(mPre.getString(Constant.MY_INFO_FEATURE[6],
				Constant.DEFAULT_BRIEF_INTRO));
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mCurrentPage = mPre.getInt(Constant.CURRENT_THIRD_ACTIVITY_PAGE, 0);
		mheadPicPath = mPre.getString(Constant.HEAD_PORTRAIT_PATH, "");
		if (!mheadPicPath.equals("")) {
			Log.i(TAG, "----->>>>>>>mheadPicPath:" + mheadPicPath);
			if (head_portrait != null) {
				Log.i(TAG, "------>>>>>>head_portrait!=null");
				Uri uri = Uri.parse("file://" + mheadPicPath);
				InitHeadPortraitImage(uri);
			}
		}
		initNickNameAndBriefIntro();
		initHighlight();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		// setContentView(R.layout.activity_my_account);
	}

	public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
			double newHeight) {
		// 获取这个图片的宽和高
		float width = bgimage.getWidth();
		float height = bgimage.getHeight();
		// 创建操作图片用的matrix对象
		Matrix matrix = new Matrix();
		// 计算宽高缩放率
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 缩放图片动作
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
				(int) height, matrix, true);
		return bitmap;
	}

	private void getHeadPic(Uri uri) {
		String[] pojo = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, pojo, null, null, null);
		if (cursor != null) {
			ContentResolver cr = this.getContentResolver();
			int colunm_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			String path = cursor.getString(colunm_index);
			Log.i(TAG, "head portrait path:" + path);
			mPre.edit().putString(Constant.HEAD_PORTRAIT_PATH, path).commit();
			if (path.endsWith("jpg") || path.endsWith("png")) {
				mheadPicPath = path;
				Bitmap bitmap, zoombitmap;
				try {
					bitmap = BitmapFactory
							.decodeStream(cr.openInputStream(uri));
					Log.i(TAG, "mPortrait width:" + head_portrait.getWidth()
							+ "mPortrait height:" + head_portrait.getHeight());
					zoombitmap = zoomImage(bitmap, 132, 132);
					head_portrait.setImageBitmap(zoombitmap);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	private void setParams(android.view.WindowManager.LayoutParams lay) {  
		  DisplayMetrics dm = new DisplayMetrics();  
		  getWindowManager().getDefaultDisplay().getMetrics(dm);  
		  Rect rect = new Rect();  
		  View view = getWindow().getDecorView();  
		  view.getWindowVisibleDisplayFrame(rect);  
		  lay.height = dm.heightPixels - rect.top;  
		  lay.width = dm.widthPixels;     
	}
	
	private void showPicDialog(){
	    Dialog dialog = new Dialog(this, R.style.showpicdialog);  
	    dialog.setContentView(R.layout.my_info);  
	    android.view.WindowManager.LayoutParams lay = dialog.getWindow().getAttributes();  
	    setParams(lay);  
	    dialog.show();  
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			if (data != null) {
				showPicDialog();
				Uri uri = data.getData();
				getHeadPic(uri);
			}
		}
	}

}
