package com.laifu.livecolorful;

import java.io.FileNotFoundException;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ShowAlbumPicDialog extends Dialog implements
		Button.OnClickListener {
	Uri mUri;
	Button cancel_btn, pass_btn;
	ImageView mShowPic;
	Context mContext;

	public ShowAlbumPicDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
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
	

	void ShowCertainPic() {
		WindowManager wm = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(mContext.getContentResolver()
					.openInputStream(mUri));
			mShowPic.setScaleType(ScaleType.CENTER_CROP);
			mShowPic.setImageBitmap(zoomImage(bitmap, wm.getDefaultDisplay()
					.getWidth(), wm.getDefaultDisplay().getWidth()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ShowAlbumPicDialog(Context context, Uri uri) {
		super(context, R.style.showpicdialog);
		mUri = uri;
		setContentView(R.layout.show_pic_dialog);

		cancel_btn = (Button) findViewById(R.id.canel_btn);
		cancel_btn.setOnClickListener(this);
		pass_btn = (Button) findViewById(R.id.pass_btn);
		pass_btn.setOnClickListener(this);

		mShowPic = (ImageView) findViewById(R.id.show_pic);
		ShowCertainPic();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == pass_btn) {

		} else {

		}
	}

}
