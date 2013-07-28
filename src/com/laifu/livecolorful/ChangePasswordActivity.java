package com.laifu.livecolorful;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ChangePasswordActivity extends Activity implements Button.OnClickListener{
	
	private Button TitleLeft,TitleRight;
	private Button BoundPhoneBtn;
	
	private void InitTitle(){
		TitleLeft = (Button)findViewById(R.id.left);
		TitleRight = (Button)findViewById(R.id.right);
		TitleLeft.setOnClickListener(this);
		TitleRight.setOnClickListener(this);
		
		TitleLeft.setBackgroundResource(R.drawable.shape_button);
		TitleRight.setBackgroundResource(R.drawable.shape_button);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.change_password);
		
		InitTitle();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==TitleLeft){
			finish();
		}else if(v==TitleRight){
		}
	}
	
}
