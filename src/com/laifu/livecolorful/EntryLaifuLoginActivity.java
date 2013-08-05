package com.laifu.livecolorful;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EntryLaifuLoginActivity extends LiveBaseActivity{

	EditText mLogin;
	Button mLoginBtn;
	Context mContext;
	ImageView mCloseImg;
	@Override
	public void initPages() {
		// TODO Auto-generated method stub
		mLoginBtn = (Button)findViewById(R.id.mloggin_btn);
		mLoginBtn.setOnClickListener(this);
		
		mCloseImg = (ImageView)findViewById(R.id.close_img);
		mCloseImg.setOnClickListener(this);
	}

	@Override
	protected void onClickListener(int viewId) {
		// TODO Auto-generated method stub
		if(viewId == mLoginBtn.getId()){
			Intent i = new Intent(this,IndexActivity.class);
			startActivity(i);
		}else if(viewId == mCloseImg.getId()){
			finish();
		}
	}

	@Override
	public int setModelId() {
		// TODO Auto-generated method stub
		return R.id.laifu_login_layout;
	}

	@Override
	public int setLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.laifu_login;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = this;
	}
}
