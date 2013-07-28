package com.laifu.livecolorful;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.preference.PreferenceManager;

public class GlobaleData {
	/************************我的帐号******************************/
	
	/*获取我的行程单*/
	public static List<Map<String, Object>> getMyRouontListData(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "张根硕全球电视通告-中国歌迷会北京分会朝阳区分会");
		map.put("info", "23580266个成员     67556个行程");
		map.put("img", R.drawable.sina_on_btn);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "建筑系12级3班 同路人");
		map.put("info", "52个成员     47个行程");
		map.put("img", R.drawable.sina_on_btn);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "建筑系12级3班 同路人");
		map.put("info", "52个成员     47个行程");
		map.put("img", R.drawable.sina_on_btn);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "建筑系12级3班 同路人");
		map.put("info", "52个成员     47个行程");
		map.put("img", R.drawable.sina_on_btn);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "建筑系12级3班 同路人");
		map.put("info", "52个成员     47个行程");
		map.put("img", R.drawable.sina_on_btn);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "建筑系12级3班 同路人");
		map.put("info", "52个成员     47个行程");
		map.put("img", R.drawable.sina_on_btn);
		list.add(map);
		return list;
	}
	/*获取我的头像图片*/
	public static Bitmap getMyheadPicture(Context mContext){
		Bitmap bitmap=null;
		Uri headPic = Uri.parse("file://" + "/sdcard/LiveColorful/Photo/headPic.png");
		try {
			 bitmap = BitmapFactory.decodeStream(mContext.getContentResolver()
					.openInputStream(headPic));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bitmap;
	}
	/*获取我的封面图片*/
	public static Bitmap getMyCoverPicture(Context mContext){
		Bitmap bitmap=null;
		Uri headPic = Uri.parse("file://" + "/sdcard/LiveColorful/Photo/CoverPic.png");
		try {
			 bitmap = BitmapFactory.decodeStream(mContext.getContentResolver()
					.openInputStream(headPic));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bitmap;
	}
	
	public static final String[] MY_INFO_FEATURE = { "ID", "nick_name",
		"gender", "area", "tel", "email", "brief_intro"
	};
	/*获取当前账户信息*/
	public static String[] getCurrentAccountMsg(Context mContext){
		SharedPreferences mPre;
		mPre = PreferenceManager.getDefaultSharedPreferences(mContext);
		String[] mContent = new String[7];
		for(int i=0;i<MY_INFO_FEATURE.length;i++){
			mContent[i] = mPre.getString(MY_INFO_FEATURE[i], "");
		}
		return mContent;
	}
	/*保存当前用户信息*/
	public static void SaveCurrentAccountMsg(Context mContext,String[] mContent){
		SharedPreferences mPre;
		mPre = PreferenceManager.getDefaultSharedPreferences(mContext);
		
		for(int i=0;i<MY_INFO_FEATURE.length;i++){
			 mPre.edit().putString(MY_INFO_FEATURE[i], mContent[i]).commit();
		}
	}
	/*压缩图片*/
	public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
			double newHeight) {
		// 获取这个图片的宽和高
		float width = bgimage.getWidth();
		float height = bgimage.getHeight();
		// 创建操作图片用的matrix对象
		Matrix matrix = new Matrix();
		// 计算宽高缩放率
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 缩放图片动作
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
				(int) height, matrix, true);
		return bitmap;
	}

	/************************我的帐号******************************/
}