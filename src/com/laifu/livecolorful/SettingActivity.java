package com.laifu.livecolorful;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class SettingActivity extends Activity implements ImageView.OnClickListener{
	ImageView mNotificationImg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting);
		
		mNotificationImg = (ImageView)findViewById(R.id.notification_switch);
		mNotificationImg.setOnClickListener(this);

	}
	int i=0;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(mNotificationImg == v){
			i++;
			if(i%2==0)
				mNotificationImg.setBackgroundResource(R.drawable.ic_checkbox_unchecked1);
			
			else mNotificationImg.setBackgroundResource(R.drawable.ic_checkbox_checked1);
		}
	}

}
