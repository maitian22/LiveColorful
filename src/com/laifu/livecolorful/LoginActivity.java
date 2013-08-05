package com.laifu.livecolorful;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends LiveBaseActivity {
	TextView mTextDes;
	SpannableString msp;
	ImageView mSinaImg, mQQImg, mlaifuImg;
	Context mContext;

	private void InitTextdes() {
		mTextDes = (TextView) findViewById(R.id.text_des);
		msp = new SpannableString("嗨，欢迎来到来福大家庭！\n刚下载的朋友可能不知道这个App是干什么的，"
				+ "没关系！熟悉之后你会发现：真是太好用了！");
		msp.setSpan(
				new ForegroundColorSpan(getResources().getColor(
						R.color.login_laifu)), 6, 8,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		msp.setSpan(
				new ForegroundColorSpan(getResources().getColor(R.color.white)),
				0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		msp.setSpan(
				new ForegroundColorSpan(getResources().getColor(R.color.white)),
				8, 55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		msp.setSpan(new AbsoluteSizeSpan(35), 0, 12,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		mTextDes.setText(msp);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initPages() {
		// TODO Auto-generated method stub
		mContext = this;
		InitTextdes();
		mSinaImg = (ImageView) findViewById(R.id.sina_icon);
		mSinaImg.setOnClickListener(this);
		mQQImg = (ImageView) findViewById(R.id.qq_icon);
		mQQImg.setOnClickListener(this);
		mlaifuImg = (ImageView) findViewById(R.id.laifu_icon);
		mlaifuImg.setOnClickListener(this);
	}

	@Override
	protected void onClickListener(int viewId) {
		// TODO Auto-generated method stub
		if(viewId==mSinaImg.getId()){
			
		}else if(viewId==mQQImg.getId()){
			
		}else if(viewId==mlaifuImg.getId()){
			Intent i = new Intent(mContext,EntryLaifuLoginActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(i);
		}
	}

	@Override
	public int setModelId() {
		// TODO Auto-generated method stub
		return R.layout.index;
	}

	@Override
	public int setLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.login;
	}
}
