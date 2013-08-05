package com.laifu.livecolorful;

import java.io.File;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Environment;
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
	private static final int TAKE_PICTURE = 0;
	private static final int CHOOSE_PICTURE = 1;

	public MyInfoCtrl(ThirdActivity c, RelativeLayout ml) {
		mActivity = c;
		mRel = ml;
		mEditArray = new ArrayList<EditText>();
		initView();
	}

	private void InitEditTextView() {
		mEditArray.clear();
		String[] mAccount = GlobaleData.getCurrentAccountMsg(mActivity);
		for (int i = 0; i < mEditNameId.length; i++) {
			EditText mEdit = (EditText) mRel.findViewById(mEditNameId[i]);
			String disText = mAccount[i];
			mEdit.setText(disText);
			mEdit.setCursorVisible(false);
			mEdit.setClickable(false);
			mEdit.setFocusable(false);
			mEditArray.add(mEdit);
		}
	}

	private void initView() {
		editInfo = (Button) mRel.findViewById(R.id.editInfo);
		updateheadPic = (Button) mRel.findViewById(R.id.updateheadPic);
		modifyCover = (Button) mRel.findViewById(R.id.modifyCover);

		editInfo.setOnClickListener(this);
		updateheadPic.setOnClickListener(this);
		modifyCover.setOnClickListener(this);

		InitEditTextView();
	}

	private void saveEditInfomation() {
		String[] mContent = new String[mEditArray.size()];
		for (int i = 0; i < mEditArray.size(); i++) {
			mContent[i] = mEditArray.get(i).getEditableText().toString();
		}
		GlobaleData.SaveCurrentAccountMsg(mActivity, mContent);
		mActivity.initNickNameAndBriefIntro();
	}

	private void hideSoftKeypad() {
		InputMethodManager imm = (InputMethodManager) mActivity
				.getSystemService(mActivity.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(mEditArray.get(0).getWindowToken(), 0);
	}

	private void freezeEditText() {
		for (int i = 0; i < mEditArray.size(); i++) {
			mEditArray.get(i).setCursorVisible(false);
			mEditArray.get(i).setClickable(false);
			mEditArray.get(i).setFocusable(false);
		}
		editInfo.setBackgroundDrawable(mActivity.getResources().getDrawable(
				R.drawable.shape));
		editInfo.setText(R.string.edit_info);

	}

	public void showPicturePicker(Context context,final int requestCode) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("图片来源");
		builder.setNegativeButton("取消", null);
		builder.setItems(new String[] { "拍照", "相册" },
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case TAKE_PICTURE:
							//保存本次截图临时文件名字
							String dictoryPath = Constant.PitcturPath;
							String fileName = String.valueOf(System.currentTimeMillis()) + ".jpg";
							Editor editor = mActivity.mPre.edit();
							editor.putString(Constant.PICTURE_CAPTURE_IMAGE_NAME, fileName);
							editor.commit();
							Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
							Uri imageUri = Uri.fromFile(new File(dictoryPath,fileName));
							openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
							//openCameraIntent.putExtra("return-data", true);
							mActivity.startActivityForResult(openCameraIntent, requestCode);
							break;

						case CHOOSE_PICTURE:
							Intent intent = new Intent(Intent.ACTION_PICK, null);
							intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
									"image/*");
							mActivity.startActivityForResult(intent,
									requestCode);
							break;

						default:
							break;
						}
					}
				});
		builder.create().show();
	}

	@Override
	public void onClick(View v) {
		int i = 0;
		// TODO Auto-generated method stub
		if (v == editInfo) {
			if (editInfo.getText().toString()
					.equals(mActivity.getString(R.string.finish))) {
				freezeEditText();
				hideSoftKeypad();
				saveEditInfomation();
				updateheadPic.setVisibility(android.view.View.VISIBLE);
				modifyCover.setText(R.string.modify_cover);

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

				updateheadPic.setVisibility(android.view.View.INVISIBLE);
				modifyCover.setText(R.string.cancel);

			}

		} else if (v == updateheadPic) {
		/*	Intent intent = new Intent(Intent.ACTION_PICK, null);
			intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
					"image/*");
			mActivity.startActivityForResult(intent,
					Constant.PICTURE_HEAD_CAPTUIE);*/
			
			showPicturePicker(mActivity,Constant.PICTURE_HEAD_CAPTUIE);

		} else if (v == modifyCover) {
			if (editInfo.getText().toString()
					.equals(mActivity.getString(R.string.finish))) {
				freezeEditText();
				hideSoftKeypad();
				updateheadPic.setVisibility(android.view.View.VISIBLE);
				modifyCover.setText(R.string.modify_cover);
				InitEditTextView();
			} else {
				showPicturePicker(mActivity,Constant.PICTURE_COVER_CAPTUIE);
			}
		}
	}
}
