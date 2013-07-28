package com.laifu.livecolorful;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SettingActivity extends Activity implements ImageView.OnClickListener{
	private ImageView mNotificationImg;
	private Button mLeftBtn,mRightBtn;
	private TextView mTitle;
	private RelativeLayout mChangePassword;
	private Button BoundPhoneBtn;
	private void InitTitle(){
		mRightBtn = (Button)findViewById(R.id.right);
		mRightBtn.setBackgroundResource(R.drawable.gb_btn);
		mRightBtn.setOnClickListener(this);
		mTitle = (TextView)findViewById(R.id.title);
		mTitle.setText("设置");
		
		mLeftBtn = (Button)findViewById(R.id.left);
		mLeftBtn.setVisibility(android.view.View.INVISIBLE);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting);
		
		InitTitle();
		
		mNotificationImg = (ImageView)findViewById(R.id.notification_switch);
		mNotificationImg.setOnClickListener(this);
		
		mChangePassword = (RelativeLayout)findViewById(R.id.change_password);
		mChangePassword.setOnClickListener(this);
		
		BoundPhoneBtn = (Button)findViewById(R.id.bound_phone_btn);
		BoundPhoneBtn.setOnClickListener(this);
	}
	private void StartTheChooseActivity(Class<?> mActivity){
		Intent i = new Intent(this,mActivity);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(i);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(mNotificationImg == v){
			
		}else if(mRightBtn==v){
			finish();
		}else if(mChangePassword == v){
			StartTheChooseActivity(ChangePasswordActivity.class);
		}else if(BoundPhoneBtn == v){
			StartTheChooseActivity(BoundPhoneActivity.class);
		}
	}

}
