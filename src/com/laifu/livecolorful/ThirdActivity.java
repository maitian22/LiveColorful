package com.laifu.livecolorful;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
	private String mheadPicPath = "", mPictureCoverPath = "";
	private ImageView head_portrait;
	private TextView mNickName, mBriefIntro;
	private RelativeLayout mAccoutBg;

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
		Log.i(TAG,"initView ------------->");
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
		mAccoutBg = (RelativeLayout) findViewById(R.id.account_bg);

		mNickName = (TextView) findViewById(R.id.nick_name_disp);
		mBriefIntro = (TextView) findViewById(R.id.brief_intro_disp);
	}
	
	Button.OnClickListener settinglistener = new Button.OnClickListener(){
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent();
			i.setClass(mContext, SettingActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			mContext.startActivity(i);
			
		}
	};
	public void initTitle() {
		left.setBackgroundResource(R.drawable.sz_btn);
		left.setOnClickListener(settinglistener);
		title.setText("我的账号");
		right.setVisibility(View.GONE);
	}

	private void InitHeadPortraitImage(Uri uri) {
		Bitmap bitmap, zoombitmap;
		try {
			bitmap = BitmapFactory.decodeStream(getContentResolver()
					.openInputStream(uri));
			zoombitmap = Constant.zoomImage(bitmap, 132, 132);
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

	void InitMyInfoPicture(View img, String path) {
		int width, height;
		width = img.getLayoutParams().width;
		height = img.getLayoutParams().height;
		if (!path.equals("")) {
			Uri uri = Uri.parse("file://" + path);
			Bitmap bitmap, zoombitmap;
			try {
				bitmap = BitmapFactory.decodeStream(getContentResolver()
						.openInputStream(uri));
				zoombitmap = Constant.zoomImage(bitmap, width, height);
				BitmapDrawable bd = new BitmapDrawable(zoombitmap);
				img.setBackgroundDrawable(bd);
				// img.setImageBitmap(zoombitmap);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG, "onResume ---------->");
		mCurrentPage = mPre.getInt(Constant.CURRENT_THIRD_ACTIVITY_PAGE, 0);
		mheadPicPath = mPre.getString(Constant.HEAD_PORTRAIT_PATH, "");
		mPictureCoverPath = mPre.getString(Constant.PICTURE_COVER_PATH, "");
		// InitMyInfoPicture(head_portrait, mheadPicPath);
		// InitMyInfoPicture(mAccoutBg,mPictureCoverPath);
		initNickNameAndBriefIntro();
		initHighlight();
	}
	
	void InitMyInfoPicture(){
		Uri headPic = Uri.parse("file://" + "/sdcard/LiveColorful/Photo/headPic.png");
		try {
			Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
					.openInputStream(headPic));
			head_portrait.setImageBitmap(bitmap);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Uri CoverPic = Uri.parse("file://" + "/sdcard/LiveColorful/Photo/CoverPic.png");
		try {
			Bitmap Coverbitmap = BitmapFactory.decodeStream(getContentResolver()
					.openInputStream(CoverPic));
			BitmapDrawable bd=new BitmapDrawable(Coverbitmap);
			mAccoutBg.setBackgroundDrawable(bd);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		
		Log.i(TAG,"onCreate --------->");
		InitMyInfoPicture();
		// setContentView(R.layout.activity_my_account);
	}

	private String getPicPath(Uri uri) {
		String path = "";
		String[] pojo = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, pojo, null, null, null);
		if (cursor != null) {
			int colunm_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			path = cursor.getString(colunm_index);
		}
		return path;
	}

	private Bitmap getPicBitmap(Uri uri, int width, int height) {
		Bitmap bitmap, zoombitmap = null;
		ContentResolver cr = this.getContentResolver();
		try {
			bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
			Log.i(TAG, "mPortrait width:" + head_portrait.getWidth()
					+ "mPortrait height:" + head_portrait.getHeight());
			zoombitmap = Constant.zoomImage(bitmap, width, height);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zoombitmap;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	void headPortraitDialogShow(final Bitmap mB) {
		ShowAlbumPicDialog mShowDialog = new ShowAlbumPicDialog(this, mB) {
			@Override
			void onCancelClick() {
				// TODO Auto-generated method stub
				this.dismiss();
			}

			@Override
			void onPassClick() {
				// TODO Auto-generated method stub
				// setHeadPic(uri);
				SaveBitmapToDisk(mB, "headPic");
				head_portrait.setImageBitmap(mB);
				this.dismiss();

			}

		};
		mShowDialog.show();
	}

	void SaveBitmapToDisk(Bitmap mBitmap, String name) {
		String sdcardPath = Environment.getExternalStorageDirectory().getPath();
		String dirctory = sdcardPath+"/LiveColorful/Photo";
		File f = new File(dirctory);
			if(!f.exists()){
			f.mkdirs();
		}
		String filePath = dirctory+"/"+name;
		f = new File(filePath+ ".png");

		try {
			f.createNewFile();
			FileOutputStream fOut = null;
			fOut = new FileOutputStream(f);
			mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
			fOut.flush();
			fOut.close();
		} catch (FileNotFoundException e) {
			Log.i(TAG,"SaveBitmapToDisk FileNotFoundException----------->");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i(TAG,"SaveBitmapToDisk IOException----------->");
		}
	}

	void changeCoverDialogShow(final Bitmap mB) {
		ShowAlbumPicDialog mShowDialog = new ShowAlbumPicDialog(this, mB) {
			@Override
			void onCancelClick() {
				// TODO Auto-generated method stub
				this.dismiss();
			}

			@Override
			void onPassClick() {
				// TODO Auto-generated method stub
				// setCoverPicture(uri);
				SaveBitmapToDisk(mB, "CoverPic");
				BitmapDrawable bd=new BitmapDrawable(mB);
				mAccoutBg.setBackgroundDrawable(bd);
				this.dismiss();
			}

		};
		mShowDialog.show();
	}

	public void startPhotoZoom(Uri uri, int width, int height, int resultCode) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", width);
		intent.putExtra("aspectY", height);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", width);
		intent.putExtra("outputY", height);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, resultCode);
	}

	class PictureChangeTask extends AsyncTask<String, Boolean, Boolean> {
		private Bitmap mBitmap;
		private int doAction;
		private Uri uri;

		public PictureChangeTask(Bitmap photo, int what) {
			mBitmap = photo;
			doAction = what;
		}

		@Override
		protected Boolean doInBackground(String... params) {
			// uri = Uri.parse(MediaStore.Images.Media.insertImage(
			// getContentResolver(), mBitmap, null, null));
			return true;
		}

		@Override
		protected void onPreExecute() {
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(Boolean flag) {
			cancelProgressDialog();
			if (doAction == 1) {
				changeCoverDialogShow(mBitmap);
			} else if (doAction == 2) {
				headPortraitDialogShow(mBitmap);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		int width, height;
		Log.i(TAG, "onActivityResult--------->");
		if (data == null)
			return;
		final Uri uri = data.getData();
		if (requestCode == Constant.RESULT_PICTURE_COVER) {
			Log.i(TAG, "requestCode = RESULT_PICTURE_COVER");
			Bundle extras = data.getExtras();
			if (extras != null) {
				Bitmap photo = extras.getParcelable("data");
				new PictureChangeTask(photo, 1).execute();
			}
		} else if (requestCode == Constant.PICTURE_COVER_CAPTUIE) {
			width = getWindowManager().getDefaultDisplay().getWidth() / 2;
			height = mAccoutBg.getLayoutParams().height / 2;
			Log.i(TAG, "width:" + width + ",height:" + height);
			startPhotoZoom(uri, width, height, Constant.RESULT_PICTURE_COVER);
		} else if (requestCode == Constant.PICTURE_HEAD_CAPTUIE) {
			width = head_portrait.getLayoutParams().width;
			height = head_portrait.getLayoutParams().height;
			Log.i(TAG, "width:" + width + ",height:" + height);
			startPhotoZoom(uri, width, height, Constant.RESULT_PACTURE_HEAD);
		} else if (requestCode == Constant.RESULT_PACTURE_HEAD) {
			Bundle extras = data.getExtras();
			if (extras != null) {
				Bitmap photo = extras.getParcelable("data");
				new PictureChangeTask(photo, 2).execute();
			}
		}
	}

}
