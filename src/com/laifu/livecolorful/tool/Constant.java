package com.laifu.livecolorful.tool;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * 
 * @author 类名：Constant.java 备注：常量类
 */
public class Constant {

	public static String URL_PREFIX_NormalValue = "http://mobile.jumei.com/msapi/";

	public static String URL_PREFIX_TestValue = "http://mobile.jumeird.com/msapi/";

	/** 请求的路径 */
	public static String URL_PREFIX = URL_PREFIX_NormalValue;

	/** 是否为开发阶段 */
	public final static boolean IS_DEBUG = true;

	/** 版本号 */
	public final static String VER = "ANDROID V1.0";
	/** 服务器接口协议版本号,一般与客户端版本号一致 */
	public static final String CLIENTVER = "1.0";

	// 传回服务器HTTP头名字 开始
	public static final String CLIENT_OS = "platform_v"; // 手机操作系统版本

	public static final String PLATFORM = "platform";
	public static final String CLIENT = "client_v";
	public static final String MODEL = "model";
	public static final String IMSI = "imsi";
	public static final String IMEI = "imei";
	public static final String SOURCE = "source";
	public static final String CARRIER = "operator";
	public static final String CONTENT_TYPE = "content_type";
	public static final String LANGUAGE = "language";;
	public static final String SMSCENTER = "smscenter";
	public static final String HTTPHEAD = "httphead";
	public static final String JUMEI_PRODUCT = "product";

	/** 用户 hash 串 */
	public static final String ACCOUNT = "account";
	/** 用户手机屏幕分辨率 */
	public static final String RESOLUTION = "resolution";
	/** 用户昵称 */
	public static final String NICKNAME = "nickname";
	/** 从上次的请求中获得，需回传给server */
	public static final String PHPSESSID = "PHPSESSID";
	/** 服务器分发所需变量 */
	public static final String JUMEI_JHC = "JHC";

	public static final String TK = "tk";
	// 传回服务器HTTP头名字 结束

	public static final String COOKIE_VER = "cookie_ver";

	public static final String COOKIE = "Cookie";
	public static final String UID = "uid";

	public static final String CLIENT_OS_VALUE = android.os.Build.VERSION.RELEASE;
	public static final String MODEL_VALUE = android.os.Build.MODEL;
	public static String SOURCE_VALUE = "androiddefault";

	public static final boolean isUseGZIP = true;

	// Http请求头信息字段
	public static final String UDID = "udid";

	// 缓存文件的最大值
	public static final long CACHEMAXSIZE = 10 * 1024 * 1024;

	// 用户信息
	public static final String USER = "user";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";

	public static final String Last_address_id = "Last_address_id";
	public static final String Last_address_uid = "Last_address_uid";
	public static final String Last_address_receiver_name = "Last_address_receiver_name";
	public static final String Last_address_addr = "Last_address_addr";
	public static final String Last_address_mobile = "Last_address_mobile";
	public static final String Last_address_phone = "Last_address_phone";

	// 地址数据库
	public static final String JUMEI_ADDRESS = "jumei_address";
	public static final int UPDATE_ADDRESS_OK = 1111;

	// 开关类设置配置文件名
	public static final String ALARM = "alarm";

	// 分享设置
	public static final String SharedSet_Flag = "SharedSet_flag";

	// 图片设置
	public static final String SHOWPIC_FLAG = "showpic_flag";
	public static boolean SHOWPIC_Value = true;

	// push相关
	public static final String PUSH_TITLE = "push_title";
	public static final String PUSH_MESSAGE = "push_message";
	public static final String PUSH_FLAG = "push_flag";

	public static final String IMG = "img";
	public static final String IMGURL = "imgurl";
	
	// sharePreference name
	public static final String HEAD_PORTRAIT_PATH = "head_protrait_path";
	public static final String PICTURE_COVER_PATH = "picture_cover_path";
	public static final String CURRENT_THIRD_ACTIVITY_PAGE = "current_third_activity_page";
	public static final String DEFAULT_NICK_NAME = "利莎-GAO";
	public static final String DEFAULT_BRIEF_INTRO = "农夫山泉有点田  农夫山泉有点田";
	public static final String[] MY_INFO_FEATURE = { "ID", "nick_name",
			"gender", "area", "tel", "email", "brief_intro"
	};
	public static int HEAD_PIC_CAPTURE = 1;
	public static int PICTURE_COVER_CAPTUIE  = 2;
	public static int RESULT_PICTURE_COVER = 3;
	
	
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

}
