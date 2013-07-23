package com.laifu.livecolorful;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MyInfoCtrl implements Button.OnClickListener{
	private Context mContext;
	private RelativeLayout mRel;
	private Button editInfo,updateheadPic,modifyCover;
	private ThirdActivity mActivity;
	public MyInfoCtrl(ThirdActivity c,RelativeLayout ml){
		mActivity = c;
		mRel = ml;
		
		initView();
	}
	
	private void initView(){
		editInfo = (Button)mRel.findViewById(R.id.editInfo);
		updateheadPic = (Button)mRel.findViewById(R.id.updateheadPic);
		modifyCover = (Button)mRel.findViewById(R.id.modifyCover);
		
		editInfo.setOnClickListener(this);
		updateheadPic.setOnClickListener(this);
		modifyCover.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==editInfo){
			
		}else if(v==updateheadPic){
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			mActivity.startActivityForResult(intent, 1);
		}else if(v==modifyCover){
			
		}
	}
}
