package com.laifu.livecolorful;

import java.util.ArrayList;

import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.laifu.livecolorful.tool.Constant;

public class MyInfoCtrl implements Button.OnClickListener {
	private RelativeLayout mRel;
	private static Button editInfo, updateheadPic, modifyCover;
	private static ThirdActivity mActivity;
	private ArrayList<EditText> mEditArray;
	private int[] mEditNameId = { R.id.edit_id, R.id.edit_nickname,
			R.id.edit_gender, R.id.edit_area, R.id.edit_tel, R.id.edit_email,
			R.id.edit_briefIntro };

	public MyInfoCtrl(ThirdActivity c, RelativeLayout ml) {
		mActivity = c;
		mRel = ml;
		mEditArray = new ArrayList<EditText>();
		initView();
	}

	private void initView() {
		editInfo = (Button) mRel.findViewById(R.id.editInfo);
		updateheadPic = (Button) mRel.findViewById(R.id.updateheadPic);
		modifyCover = (Button) mRel.findViewById(R.id.modifyCover);

		editInfo.setOnClickListener(this);
		updateheadPic.setOnClickListener(this);
		modifyCover.setOnClickListener(this);

		for (int i = 0; i < mEditNameId.length; i++) {
			EditText mEdit = (EditText) mRel.findViewById(mEditNameId[i]);
			String disText = mActivity.mPre.getString(
					Constant.MY_INFO_FEATURE[i], "");
			mEdit.setText(disText);
			mEdit.setCursorVisible(false);
			mEdit.setClickable(false);
			mEdit.setFocusable(false);
			mEditArray.add(mEdit);
		}
	}

	private void saveEditInfomation() {
		for (int i = 0; i < mEditArray.size(); i++) {
			mActivity.mPre
					.edit()
					.putString(Constant.MY_INFO_FEATURE[i],
							mEditArray.get(i).getEditableText().toString())
					.commit();
		}
		mActivity.initNickNameAndBriefIntro();
	}
	private void hideSoftKeypad(){
		InputMethodManager imm = (InputMethodManager)mActivity.getSystemService(mActivity.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(mEditArray.get(0).getWindowToken(), 0);
	}
	@Override
	public void onClick(View v) {
		int i = 0;
		// TODO Auto-generated method stub
		if (v == editInfo) {
			if (editInfo.getText().toString()
					.equals(mActivity.getString(R.string.finish))) {
				for (i = 0; i < mEditArray.size(); i++) {
					mEditArray.get(i).setCursorVisible(false);
					mEditArray.get(i).setClickable(false);
					mEditArray.get(i).setFocusable(false);
				}
				editInfo.setBackgroundDrawable(mActivity.getResources()
						.getDrawable(R.drawable.shape));
				editInfo.setText(R.string.edit_info);
				saveEditInfomation();
				hideSoftKeypad();

			} else {
				for (i = 0; i < mEditArray.size(); i++) {
					mEditArray.get(i).setCursorVisible(true);
					mEditArray.get(i).setClickable(true);
					mEditArray.get(i).setFocusable(true);
					mEditArray.get(i).setFocusableInTouchMode(true);
				}
				editInfo.setBackgroundDrawable(mActivity.getResources()
						.getDrawable(R.drawable.shape_blue));
				editInfo.setText(R.string.finish);
			}

		} else if (v == updateheadPic) {	
			Intent intent = new Intent(Intent.ACTION_PICK, null);  
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");  
            mActivity.startActivityForResult(intent, Constant.PICTURE_HEAD_CAPTUIE);  
			
		} else if (v == modifyCover) {
			Intent intent = new Intent(Intent.ACTION_PICK, null);  
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");  
            mActivity.startActivityForResult(intent, Constant.PICTURE_COVER_CAPTUIE);  
		}
	}
}
