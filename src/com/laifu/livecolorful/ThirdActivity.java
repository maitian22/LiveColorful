package com.laifu.livecolorful;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
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
	private ImageView head_portrait;
	private TextView mNickName, mBriefIntro;
	private RelativeLayout mAccoutBg;
	private TextView mRountListNum, mAttationNum, mFansNum;
	private ImageView mBoundPhoneImg, mBoundSinaImg, mBoundQQImg;

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

	private void InitRountListView() {
		SimpleAdapter adapter = new SimpleAdapter(this,
				GlobaleData.getMyRouontListData(), R.layout.my_rount_list,
				new String[] { "title", "info", "img" }, new int[] {
						R.id.title, R.id.info, R.id.img });
		my_rount_list.setAdapter(adapter);
	}

	private void initView() {
		Log.i(TAG, "initView ------------->");
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

	Button.OnClickListener settinglistener = new Button.OnClickListener() {
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

	public void initNickNameAndBriefIntro() {
		mNickName.setText(GlobaleData.getCurrentAccountMsg(this)[1]);
		mBriefIntro.setText(GlobaleData.getCurrentAccountMsg(this)[6]);
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
				zoombitmap = GlobaleData.zoomImage(bitmap, width, height);
				BitmapDrawable bd = new BitmapDrawable(zoombitmap);
				img.setBackgroundDrawable(bd);
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
		initNickNameAndBriefIntro();
		initHighlight();
	}

	void InitMyInfoPicture() {
		Bitmap mBitmapHead = GlobaleData.getMyheadPicture(this);
		head_portrait.setImageBitmap(mBitmapHead);

		Bitmap mBitmapCover = GlobaleData.getMyCoverPicture(this);
		BitmapDrawable bd = new BitmapDrawable(mBitmapCover);
		mAccoutBg.setBackgroundDrawable(bd);
	}

	void InitCountNumbers() {
		mRountListNum = (TextView) findViewById(R.id.rount_list_number);
		mRountListNum.setText("" + GlobaleData.getCurrentRountListNumber());

		mAttationNum = (TextView) findViewById(R.id.attation_number);
		mAttationNum.setText("" + GlobaleData.getCurrentAttationNumber());

		mFansNum = (TextView) findViewById(R.id.fans_number);
		mFansNum.setText("" + GlobaleData.getCurrentFanNumber());
	}

	public void InitBoundAccount() {
		Map<String, Object> map = GlobaleData.getCurrentBountAccount();
		boolean IsPhoneBound = Boolean
				.parseBoolean(map.get("phone").toString());
		boolean IsSinaBound = Boolean.parseBoolean(map.get("sina").toString());
		boolean IsQQBound = Boolean.parseBoolean(map.get("qq").toString());

		mBoundPhoneImg = (ImageView) findViewById(R.id.mPhone);
		mBoundPhoneImg
				.setBackgroundResource(IsPhoneBound ? R.drawable.iphone_on_btn
						: R.drawable.iphone_0ff_btn);
		mBoundSinaImg = (ImageView) findViewById(R.id.sina);
		mBoundSinaImg
				.setBackgroundResource(IsSinaBound ? R.drawable.sina_on_btn
						: R.drawable.sina_off_btn);
		mBoundQQImg = (ImageView) findViewById(R.id.qq);
		mBoundQQImg.setBackgroundResource(IsQQBound ? R.drawable.qq_on_btn
				: R.drawable.qq_off_btn);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;

		Log.i(TAG, "onCreate --------->");
		InitMyInfoPicture();
		InitCountNumbers();
		InitBoundAccount();
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
		String dirctory = sdcardPath + "/LiveColorful/Photo";
		File f = new File(dirctory);
		if (!f.exists()) {
			f.mkdirs();
		}
		String filePath = dirctory + "/" + name;
		f = new File(filePath + ".png");

		try {
			f.createNewFile();
			FileOutputStream fOut = null;
			fOut = new FileOutputStream(f);
			mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
			fOut.flush();
			fOut.close();
		} catch (FileNotFoundException e) {
			Log.i(TAG, "SaveBitmapToDisk FileNotFoundException----------->");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i(TAG, "SaveBitmapToDisk IOException----------->");
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
				BitmapDrawable bd = new BitmapDrawable(mB);
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

		public PictureChangeTask(Bitmap photo, int what) {
			mBitmap = photo;
			doAction = what;
		}

		@Override
		protected Boolean doInBackground(String... params) {
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
