package com.laifu.livecolorful;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laifu.livecolorful.tool.Constant;

public class MyInfoCtrl implements Button.OnClickListener {
	private RelativeLayout mRel;
	private static Button editInfo, updateheadPic, modifyCover;
	private ThirdActivity mActivity;
	private ArrayList<EditText> mEditArray;
	// private int[] mTextNameId = { R.id.text_id, R.id.nickname, R.id.gender,
	// R.id.area, R.id.tel, R.id.email, R.id.briefIntro };
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
			// mTextArray.add((TextView) mRel.findViewById(mTextNameId[i]));
			EditText mEdit = (EditText) mRel.findViewById(mEditNameId[i]);
			String disText = mActivity.mPre.getString(Constant.MY_INFO_FEATURE[i], "");
			mEdit.setText(disText);
			mEditArray.add(mEdit);
		}
	}

	private void saveEditInfomation() {
		for (int i = 0; i < mEditArray.size(); i++) {
			mActivity.mPre.edit().putString(Constant.MY_INFO_FEATURE[i],
					mEditArray.get(i).getEditableText().toString()).commit();
		}
		mActivity.initNickNameAndBriefIntro();
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
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			mActivity.startActivityForResult(intent, 1);
		} else if (v == modifyCover) {

		}
		for (i = 0; i < mEditArray.size(); i++) {
			if (v.getId() == mEditArray.get(i).getId()) {

				break;
			}
		}
	}
}
